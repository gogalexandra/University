import os
import socket
import threading

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(("127.168.1.17", 5555))
s.send(b"Hello")

while True:
    str = input("give string: ")
    s.send(str.encode('utf-8'))
    while True:
        print(s.recv(100))