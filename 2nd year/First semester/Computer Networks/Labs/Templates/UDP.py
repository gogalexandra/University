import socket
import pickle

# socket for server
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
# bind it
s.bind(("0.0.0.0", 5555))
# buff: info, addr: address of the cient MUST SAVE TO KNOW WHO TO SEND INFO
buff, addr = s.recvfrom(1000)
print("Got from client the array")
suma = sum(pickle.loads(buff))
# send info to addr: here is the address of client
s.sendto(pickle.dumps(suma), addr)
