import pickle
import socket
import struct

a = int(input('nr = '))

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.connect(('192.168.1.235', 5555))
    sock.send(struct.pack("!H", a))

    result = sock.recv(4096)
    result_arr = pickle.loads(result)
    print('List of divisors are ' + str(result_arr))