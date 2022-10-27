import pickle
import socket
import struct

arr = input('Give string : ')
a = int(input('index = '))
b = int(input('length = '))

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.connect(('192.168.1.235', 5555))

    arr1 = pickle.dumps(arr)
    sock.send(arr1)
    sock.send(struct.pack("!H", a))
    sock.send(struct.pack("!H", b))

    result = sock.recv(4096)
    result_arr = pickle.loads(result)
    print('Substring from pos ' + str(a) + ' with length ' + str(a) + ' is: ' + str(result_arr))