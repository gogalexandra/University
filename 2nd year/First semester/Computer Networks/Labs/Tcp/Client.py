import socket

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:

    #for windows
    sock.connect(('192.168.56.1', 5555))

    #for linux
    #sock.connect(('192.168.1.235', 5555))
    while True:
        sock.sendall(str.encode(input()))
