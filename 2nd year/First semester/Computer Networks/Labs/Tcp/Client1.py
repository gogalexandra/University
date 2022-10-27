import socket
import struct

a = int(input('a='))
b = int(input('b='))

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.connect(('192.168.1.17', 5555))
    sock.send(struct.pack("!H", a))
    sock.send(struct.pack("!H", b))
    c = sock.recv(2)
    c = struct.unpack('!H', c)
    print('a+b=' + c[0].__format__('d'))
