import pickle
import socket
import struct

arr1 = input('Give string : ')
arr2 = input('Give string : ')

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.connect(('192.168.1.235', 5555))
    arr1 = pickle.dumps(arr1)
    arr2 = pickle.dumps(arr2)
    sock.send(arr1)
    sock.send(arr2)

    result = sock.recv(2)
    result_arr = pickle.loads(result)
    count = sock.recv(2)
    count = pickle.loads(count)
    print(result_arr + " " + str(count))

