import socket
import pickle

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.bind(("0.0.0.0", 5555))
buff, addr = s.recvfrom(1000)
buff1, addr1 = s.recvfrom(1000)
buff2, addr2 = s.recvfrom(1000)

print("Got from client the info", str(addr))

string = pickle.loads(buff)
i = pickle.loads(buff1)
l = pickle.loads(buff2)

s.sendto(pickle.dumps(string[i:i+l]), addr)
