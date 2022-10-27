import socket
import struct
import pickle

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.bind(('0.0.0.0', 5555))
    sock.listen()
    conn, addr =  sock.accept()
    with conn:
        print("Waiting...")
        arr = conn.recv(4096)
        data = pickle.loads(arr)
        idx = conn.recv(2)
        idx = struct.unpack('!H', idx)

        len = conn.recv(2)
        len = struct.unpack('!H', len)
        res = pickle.dumps(data[idx[0]:idx[0] + len[0]])
        print(f"server recieved aray")
        conn.send(res)
        #conn.sendall(data)
