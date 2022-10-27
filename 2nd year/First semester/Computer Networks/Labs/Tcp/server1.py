import socket
import struct

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.bind(('0.0.0.0', 5555))
    sock.listen()
    conn, addr = sock.accept()
    with conn:
        print("Waiting...")        
        a = conn.recv(2)
        a = struct.unpack('!H', a)
        b = conn.recv(2)
        b = struct.unpack('!H', b)
        c = a[0] + b[0]
        print(f"server recieved {a[0]}, {b[0]}")
        conn.send(struct.pack("!H", c))
        #conn.sendall(data)
