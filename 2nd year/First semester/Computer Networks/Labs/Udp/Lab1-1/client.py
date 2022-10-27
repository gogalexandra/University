import socket
import pickle

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
dim = int(input('Give dim of array: '))
arr = []
for i in range(dim):
    elem = int(input('elem = '))
    arr.append(elem)

s.sendto(pickle.dumps(arr), ("192.168.1.17", 5555))
msg, adr = s.recvfrom(1000)
print(pickle.loads(msg), "received from ", str(adr))
