import socket
import select
import pickle
import sys


# send to all clients the new client address
def send_to_all(clients, adr):
    for x in clients:
        if x != s:
            x.send(sys.getsizeof(pickle.dumps(adr)).to_bytes(4, "big"))
            x.send(pickle.dumps(adr))


# send to new client all existing addresses
def send_all_adr(new_cs, clients_addresses):
    new_cs.send(len(clients_addresses).to_bytes(4, "big"))
    for adr in clients_addresses:
        new_cs.send(sys.getsizeof(pickle.dumps(adr)).to_bytes(4, "big"))
        new_cs.send(pickle.dumps(adr))


# creating the server socket for connection
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind(('0.0.0.0', 5555))
s.setblocking(False)
s.listen(5)

# list of all addresses from connected clients
clients_addresses = []

ins = [s]

while True:
    r, w, e = select.select(ins, [], [])
    for cs in r:
        if cs == s:
            # new socket (wanna add it to the clients list)
            new_cs, adr = s.accept()
            print(f"New connection from {adr}")

            # send to all clients the adr of the new one arrived client
            send_to_all(ins, adr)
            # send to the new client all addresses of the one already existing
            send_all_adr(new_cs, clients_addresses)
            ins.append(new_cs)
            clients_addresses.append(adr)
        else:
            read = cs.recv(4)
            adr = cs.getpeername()
            print(f"Lost connection from {adr}")
            ins.remove(cs)
            clients_addresses.remove(adr)
            send_to_all(ins, adr)
            cs.close()
