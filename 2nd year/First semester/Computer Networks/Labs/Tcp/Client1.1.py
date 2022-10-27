import pickle
import socket
import struct


dim = int(input('Give dim of array: '))
arr = []
for i in range(dim):
    elem = int(input('elem = '))
    arr.append(elem)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.connect(('192.168.1.235', 5555))
    data_string = pickle.dumps(arr)
    sock.send(data_string)
    sum = sock.recv(2)
    sum = struct.unpack('!H', sum)
    print('Sum of array is ' + sum[0].__format__('d'))