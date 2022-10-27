import socket
import struct
import pickle

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.bind(('0.0.0.0', 5555))
    sock.listen()
    conn, addr =  sock.accept()
    with conn:
        print("Waiting...")        
        data = conn.recv(2)
        data = struct.unpack('!H', data)
        list_of_div = []
        for i in range(1, data[0] + 1):
            if data[0] % i == 0:
                list_of_div.append(i)
        res = pickle.dumps(list_of_div)
        print(f"server recieved aray")
        conn.send(res)
        #conn.sendall(data)
