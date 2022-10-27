import socket
import pickle

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
string = input("Give string: ")
I = int(input("Give index: "))
l = int(input("Give length: "))

s.sendto(pickle.dumps(string), ("192.168.1.17", 5555))
s.sendto(pickle.dumps(I), ("192.168.1.17", 5555))
s.sendto(pickle.dumps(l), ("192.168.1.17", 5555))

msg, adr = s.recvfrom(1000)
print(pickle.loads(msg), "received from ", str(adr))
