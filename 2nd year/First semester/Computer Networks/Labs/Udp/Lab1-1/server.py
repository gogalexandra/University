import socket
import pickle

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.bind(("0.0.0.0", 5555))
buff, addr = s.recvfrom(1000)
print("Got from client the array")
suma = sum(pickle.loads(buff))
s.sendto(pickle.dumps(suma), addr)
