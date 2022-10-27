import socket
import threading
import time
import pickle


mylock = threading.Lock()
done = False
e = threading.Event()
e.clear()
threads = []
client_count = 0


def worker(cs):
    global mylock, done, client_count, e

    my_idcount = client_count
    print('client #', client_count, 'from: ', cs.getpeername(), cs)
    message = 'Hello client #' + str(client_count) + ' ! Give your string !'
    cs.sendall(bytes(message, 'ascii'))

    while not done:
        try:
            data = cs.recv(20)
            str1 = data.decode('ascii')
            rs = str1.count(" ")
            r = pickle.dumps(rs)
            cs.sendall(r)
            mylock.acquire()
            done = True
            mylock.release()

        except socket.error as msg:
            print('Error:', msg.strerror)
            break

    if done:
        print("Thread ", my_idcount, " done")
        e.set()
    time.sleep(5)
    cs.close()


def resetSrv():
    global mylock, threads, e, done, client_count
    while True:
        e.wait()
        for t in threads:
            t.join()
        print("all threads are finished now")
        e.clear()
        mylock.acquire()
        threads = []
        done = False
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
