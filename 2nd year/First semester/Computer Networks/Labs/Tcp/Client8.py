import pickle
import socket

dim = int(input('Give dim of array 1: '))
arr1 = []
for i in range(dim):
    elem = input('elem = ')
    arr1.append(elem)

dim = int(input('Give dim of array 2: '))
arr2 = []
for i in range(dim):
    elem = input('elem = ')
    arr2.append(elem)
sorted(arr1)
sorted(arr2)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.connect(('192.168.1.235', 5555))
    data_string1 = pickle.dumps(arr1)
    data_string2 = pickle.dumps(arr2)
    sock.send(data_string1)
    sock.send(data_string2)
    result = sock.recv(4096)
    result_arr = pickle.loads(result)
    print('Common elements are: ' + str(result_arr))
