import random
import socket
import sys
import threading
import time
import pickle

mylock = threading.Lock()
done = False
e = threading.Event()
e.clear()
threads = []
client_count = 0
sr = 1
er = 2 ** 17 - 1
random.seed()
my_num = random.randint(sr, er)
print('Server number: ', my_num)
all_values = []
winner = 0
diff = sys.maxsize


def best_aprox(values):
    if values[0][0] > my_num:
        diff = values[0][0] - my_num
    else:
        diff = my_num - values[0][0]
    best = values[0][1]
    for x in values:
        if x[0] > my_num:
            aux = x[0] - my_num
        else:
            aux = my_num - x[0]
        if aux < diff:
            diff = x[0]
            best = x[1]
    return best, diff


def worker(cs):
    global mylock, done, client_count, e, all_values, winner, diff

    my_idcount = client_count
    print('client #', client_count, 'from: ', cs.getpeername(), cs)
    message = 'Hello client #' + str(client_count) + ' ! Give your number !'
    cs.sendall(bytes(message, 'ascii'))

    while not done:
        try:
            data = cs.recv(100)
            num = pickle.loads(data)
            all_values.append((num, threading.get_ident()))
            mylock.acquire()
            done = True
            mylock.release()

        except socket.error as msg:
            print('Error:', msg.strerror)
            break

    if done:
        time.sleep(10)
        winner, diff = best_aprox(all_values)
        if threading.get_ident() == winner:
            cs.sendall(pickle.dumps("You have the best guess with an error of " + str(diff)))
        else:
            msg = "You lost"
            cs.sendall(pickle.dumps(msg))

    time.sleep(5)
    cs.close()
    print("Worker Thread ", my_idcount, " end")


def resetSrv():
    global mylock, threads, e, done, client_count, all_values, winner, diff
    while True:
        e.wait()
        for t in threads:
            t.join()
        print("all threads are finished now")
        e.clear()
        mylock.acquire()
        threads = []
        done = False
        all_values = [0] * 6
        winner = 0
        diff = sys.maxsize
        client_count = 0
        mylock.release()


if __name__ == '__main__':
    try:
        rs = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        rs.bind(('0.0.0.0', 5555))
        rs.listen(5)
    except socket.error as msg:
        print(msg.strerror)
        exit(-1)
    t = threading.Thread(target=resetSrv, daemon=True)
    t.start()
    while True:
        client_socket, addrc = rs.accept()
        t = threading.Thread(target=worker, args=(client_socket,))
        threads.append(t)
        client_count += 1
        t.start()
