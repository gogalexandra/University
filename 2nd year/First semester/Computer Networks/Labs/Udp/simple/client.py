import socket

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
msg = input("give msg for server")
s.sendto(str.encode(msg), ("192.168.2.74", 5555))
msg, adr = s.recvfrom(1000)
print(msg.decode())
