import socket
import pickle

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.bind(('0.0.0.0', 5555))
    sock.listen()
    conn, addr =  sock.accept()
    with conn:
        print("Waiting...")
        arr = conn.recv(4096)
        data = pickle.loads(arr)
        ch = conn.recv(2).decode()
        list_of_pos = []
        pos = 0
        for i in data:
            if i == ch:
                list_of_pos.append(pos)
            pos += 1
        res = pickle.dumps(list_of_pos)
        print(f"server recieved aray")
        conn.send(res)
        #conn.sendall(data)
