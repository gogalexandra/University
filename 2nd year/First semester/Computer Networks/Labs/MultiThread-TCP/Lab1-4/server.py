import socket
import pickle
import threading

e = threading.Event()
e.clear()
threads = []
client_count = 0


def worker(cs):
    global e, client_count
    done = False
    my_idcount = client_count
    print('client #', client_count, 'from: ', cs.getpeername(), cs)
    message = 'Hello client #' + str(client_count) + ' ! Give your array !'
    cs.sendall(message.encode('ascii'))
    while not done:
        try:
            arr1 = cs.recv(2000)
            a1 = pickle.loads(arr1)
            arr2 = cs.recv(2000)
            a2 = pickle.loads(arr2)
            a1 += a2
            cs.sendall(pickle.dumps(sorted(a1)))
            done = True

        except socket.error as msg:
            print('Error:', msg.strerror)
            break
    if done:
        print(f"Thread {my_idcount} is done")
        e.set()
    cs.close()


def reset():
    global e, threads, client_count
    while True:
        e.wait()
        for t in threads:
            t.join()
        e.clear()
        threads = []
        client_count = 0


if __name__ == '__main__':
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind(('0.0.0.0', 5555))
        s.listen(5)
    except socket.error as msg:
        print(msg.strerror)
        exit(-1)
    t = threading.Thread(target=reset)
    t.start()
    while True:
        cs, addr = s.accept()
        t = threading.Thread(target=worker, args=(cs,))
        threads.append(t)
        client_count += 1
        t.start()
