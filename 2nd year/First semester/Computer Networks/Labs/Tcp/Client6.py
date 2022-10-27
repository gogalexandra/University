import pickle
import socket

c = input('Give character: ')
arr = input('Give string ')

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.connect(('192.168.1.235', 5555))
    data_string = pickle.dumps(arr)
    sock.send(data_string)
    sock.send(str.encode(c))

    result = sock.recv(4096)
    result_arr = pickle.loads(result)
    print('List of positions of char in given array ' + str(result_arr))
