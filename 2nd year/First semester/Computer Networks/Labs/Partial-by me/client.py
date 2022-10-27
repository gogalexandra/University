import socket
import sys
import threading
import select
import pickle

done = False
chatLock = threading.Lock()
connectionLock =threading.Lock()

def chat(sock):
    global done
    while not done:
        msg = input()
        # signal that a client left by making done true
        if msg == "quit":
            chatLock.acquire()
            done = True
            chatLock.release()
        else:
            # send the message to the others
            connectionLock.acquire()
            for adr in all_clients:
                sock.sendto(pickle.dumps(msg), adr)
            connectionLock.release()


# TCP socket to connect to server
cs = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
cs.connect(('192.168.1.17', 5555))

# UDP socket

udp_cs = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
udp_cs.bind(cs.getsockname())

nr_of_clients = int.from_bytes(cs.recv(4), "big")
all_clients = []

# getting the addreses of the connected clients from tcp socket
for _ in range(nr_of_clients):
    size = int.from_bytes(cs.recv(4), "big")
    adr = pickle.loads(cs.recv(size))
    all_clients.append(adr)

cs.setblocking(False)
udp_cs.setblocking(False)

chat_thread = threading.Thread(target=chat, args=(udp_cs,))
chat_thread.start()

ins = [cs, udp_cs]
chatLock.acquire()
while not done:
    chatLock.release()
    r, w, e = select.select(ins, [], [], 1)
    for sock in r:
        if sock == cs:
            size = int.from_bytes(cs.recv(4), "big")
            adr = pickle.loads(cs.recv(size))
            if adr in all_clients:
                print(f"Client {adr} has diconnected")
                connectionLock.acquire()
                all_clients.remove(adr)
                connectionLock.release()
            else:
                print(f"Client {adr} has connected")
                connectionLock.acquire()
                all_clients.append(adr)
                connectionLock.release()
        elif sock == udp_cs:
            msg, adr = udp_cs.recvfrom(1024)
            print(f"Client {adr}: {pickle.loads(msg)}")
    chatLock.acquire()
chatLock.release()
cs.close()
udp_cs.close()