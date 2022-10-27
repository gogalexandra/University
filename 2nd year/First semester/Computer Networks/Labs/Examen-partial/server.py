# Implement a server application that generates a random integer P and communicates to the clients the number of
# digits of that number – when the client connects.
#
# Implement a client application that receives from a server the number of digits of P. The client generates a random
# digit every N seconds and sends the digit to the server. The server answers with the positions where the digit is
# found on the server’s number.
#
# Spawn multiple clients. The server will announce all clients when it has a winner using UDP. You should design a
# method for the server to infer the IP and port where is should communicate with each peer over UDP.


import socket
import pickle
import random
import threading

myLock = threading.Lock()
client_count = 0
threads = []
myNum = random.randint(1, 10000)
e = threading.Event()
e.clear()
done = False
winner = None
nr_of_digits = 0
list_of_digits = []
all = []

def nrDigits(nr):
    count = 0
    digits = []
    while nr != 0:
        digits.append(nr % 10)
        nr //= 10
        count += 1

    return count, digits


def worker(cs):
    global mylock, threads, e, done, client_count,  winner, nr_of_digits, list_of_digits
    print(f"{all}")
    print('client #', client_count, 'from: ', cs.getpeername(), cs)
    message = 'Hello client #' + str(client_count) + ' ! Let s begin!'
    cs.sendall(bytes(message, 'ascii'))

    cs.sendall(pickle.dumps(nr_of_digits))
    while not done:
        try:
            data = cs.recv(1000)
            d = pickle.loads(data)
            if d in list_of_digits:
                ind = list_of_digits.index(d) + 1
                cs.sendall(pickle.dumps(ind))
            else:
                if d == "i won":
                    myLock.acquire()
                    winner = client_socket.getpeername()
                    done = True
                    myLock.release()
                else:
                    cs.sendall(pickle.dumps(0))
        except socket.error as msg:
            print(msg.strerror)
            exit(-1)
    if done:
        msg = f"winner is {winner}"
        udp_server = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        udp_server.bind(('0.0.0.0', 1234))
        print(msg)
        for adr in all:
            udp_server.sendto(pickle.dumps(msg), adr)
        udp_server.close()



def reset():
    global mylock, threads, e, done, client_count,  winner, nr_of_digits, list_of_digits, all
    while True:
        e.wait()
        for t in threads:
            t.join()
        print("all threads are finished now")
        e.clear()
        mylock.acquire()
        threads = []
        done = False
        winner = 0
        nr_of_digits = 0
        all = []
        client_count = 0
        list_of_digits = []
        mylock.release()


if __name__ == '__main__':
    try:
        rs = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        rs.bind(('0.0.0.0', 5555))
        rs.listen(5)
    except socket.error as msg:
        print(msg.strerror)
        exit(-1)
    t = threading.Thread(target=reset, daemon=True)
    t.start()
    print(f"The chosen nr is: {myNum}")
    nr_of_digits, list_of_digits = nrDigits(myNum)
    list_of_digits.reverse()
    print(f"{list_of_digits}")
    while True:
        client_socket, addrc = rs.accept()
        all.append(addrc)
        t = threading.Thread(target=worker, args=(client_socket,))
        threads.append(t)
        client_count += 1
        t.start()
