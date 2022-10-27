import socket

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.connect(('192.168.1.235', 5555))
    while True:
        sock.sendall(str.encode(input()))
