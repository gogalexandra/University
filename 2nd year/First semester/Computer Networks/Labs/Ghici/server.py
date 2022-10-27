import socket
import pickle
import threading
import time
import random

myLock = threading.Lock()
done = False
e = threading.Event()
e.clear()
threads = []
client_count = 0
winner = 0
cuvinte = []
loser = 0

def worker(cs):
    global myLock, threads, e, done, client_count, threads, winner, loser

    my_idcount = client_count
    print('client #', client_count, 'from: ', cs.getpeername(), cs)
    message = 'Hello client #' + str(client_count) + ' ! Let s start !'
    cs.sendall(bytes(message, 'ascii'))

    c = cs.recv(100)
    cuv = pickle.loads(c)

    start = 0
    stop = len(cuv)
    p1 = random.randint(start, stop-1)
    p2 = random.randint(start, stop-1)
    p3 = random.randint(start, stop-1)
    poz = [p1, p2, p3]

    cuvinte.append((cuv, ord(cuv[p1]) + ord(cuv[p2]) + ord(cuv[p3])))
    cs.sendall(pickle.dumps(poz))
    if len(cuvinte) != 2:
        time.sleep(10)
    print("The correct answers are: ")
    print(cuvinte[0][0], " - ", cuvinte[0][1])
    print(cuvinte[1][0], " - ", cuvinte[1][1])
    while not done:
        try:
            # receive suma
            data = cs.recv(100)
            sum = pickle.loads(data)

            # give feedback
            if cuvinte[0][0] == cuv:
                raspuns = cuvinte[1][1]
            elif cuvinte[1][0] == cuv:
                raspuns = cuvinte[0][1]
            if sum != raspuns:
                cs.sendall(pickle.dumps("wrong"))
            else:
                myLock.acquire()
                done = True
                winner = threading.get_ident()
                myLock.release()
                break

            # continua /stop
            data = cs.recv(100)
            op = pickle.loads(data)

            # if stop then stop else let it keep going
            if op == "stop":
                print("got here")
                myLock.acquire()
                done = True
                myLock.release()

        except socket.error as msg:
            print('Error:', msg.strerror)
            break

    if done:
        if threading.get_ident() == winner:
            cs.sendall(pickle.dumps("winner"))
            e.set()
        else:
            cs.sendall(pickle.dumps("loser"))


def reset():
    global myLock, threads, e, done, client_count, threads, winner, cuvinte
    while True:
        e.wait()
        for t in threads:
            t.join()
        print("All threads are finished now")
        e.clear()
        myLock.acquire()
        threads = []
        done = False
        e = threading.Event()
        e.clear()
        client_count = 0
        cuvinte = []
        winner = 0
        myLock.release()


if __name__ == '__main__':
    try:
        rs = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        rs.bind(('0.0.0.0', 5555))
        rs.listen(2)
    except socket.error as msg:
        print(msg.strerror)
        exit(-1)
    t = threading.Thread(target=reset, daemon=True)
    t.start()
    while True:
        cs, addr = rs.accept()
        t = threading.Thread(target=worker, args=(cs,))
        threads.append(t)
        client_count += 1
        t.start()