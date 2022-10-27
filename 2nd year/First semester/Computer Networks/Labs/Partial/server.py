import pickle
import select
import socket
import sys


def send_all_clients(server, sockets, addrs):
    for client in sockets:
        if client != server:
            sending = pickle.dumps(addrs)
            client.send(sys.getsizeof(sending).to_bytes(4, "big"))
            client.send(sending)


def send_client_all_addresses(client, addrs):
    client.send(len(addrs).to_bytes(4, byteorder="big"))
    for addr in addrs:
        sending = pickle.dumps(addr)
        client.send(sys.getsizeof(sending).to_bytes(4, "big"))
        client.send(sending)

ss = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
ss.bind(("192.168.1.16", 5555))
ss.setblocking(False)
ss.listen(5)
clients = []
inputs = [ss]
while True:
    r, w, e = select.select(inputs, [], [])
    for sock in r:
        if sock == ss:
            new_cs, addr = ss.accept()
            print("New client: ", addr)
            send_all_clients(ss, inputs, addr)
            send_client_all_addresses(new_cs, clients)
            inputs.append(new_cs)
            clients.append(addr)
        else:
            read = sock.recv(4)
            addr = sock.getpeername()
            print("client left: ", addr)
            inputs.remove(sock)
            clients.remove(addr)
            send_all_clients(ss, inputs, addr)
            sock.close()

