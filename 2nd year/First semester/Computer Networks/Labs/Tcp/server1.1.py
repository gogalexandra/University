import socket
import struct
import pickle

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.bind(('0.0.0.0', 5555))
    sock.listen()
    conn, addr = sock.accept()
    with conn:
        print("Waiting...")
        data = conn.recv(4096)
        data_arr = pickle.loads(data)
        sum = 0
        for elem in data_arr:
            print(elem)
            sum += elem
        print(f"server received array")
        conn.send(struct.pack("!H", sum))
        # conn.sendall(data)
