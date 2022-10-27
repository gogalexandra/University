import pickle
import select
import socket
import threading

done = False
connections = []
check_lock = threading.Lock()
connections_lock = threading.Lock()

def reader(udp_sock):
    global done
    while not done:
        msg = input()
        if msg == "quit":
            check_lock.acquire()
            done = True
            check_lock.release()
        else:
            connections_lock.acquire()
            for connection in connections:
                udp_sock.sendto(msg.encode("ascii"), connection)
            connections_lock.release()


tcp_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
tcp_sock.connect(("192.168.1.17", 5555))
udp_sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
udp_sock.bind(tcp_sock.getsockname())
connections_count = int.from_bytes(tcp_sock.recv(4), "big")
for _ in range(connections_count):
    size = int.from_bytes(tcp_sock.recv(4), "big")
    addr = pickle.loads(tcp_sock.recv(size))
    connections.append(addr)
udp_sock.setblocking(False)
tcp_sock.setblocking(False)
reader_thread = threading.Thread(target=reader, args=(udp_sock,))
reader_thread.start()
inputs = [tcp_sock, udp_sock]
check_lock.acquire()
while not done:
    check_lock.release()
    r, w, e = select.select(inputs, [], [], 1)
    for sock in r:
        if sock == tcp_sock:
            size = int.from_bytes(tcp_sock.recv(4), "big")
            addr = pickle.loads(tcp_sock.recv(size))
            if addr in connections:
                print(addr, "disconnected")
                connections_lock.acquire()
                connections.remove(addr)
                connections_lock.release()
            else:
                print(addr, "connected")
                connections_lock.acquire()
                connections.append(addr)
                connections_lock.release()
        elif sock == udp_sock:
            msg, addr = udp_sock.recvfrom(1024)
            print(addr, ": ", msg.decode("ascii"))
    check_lock.acquire()
check_lock.release()
tcp_sock.close()
udp_sock.close()

