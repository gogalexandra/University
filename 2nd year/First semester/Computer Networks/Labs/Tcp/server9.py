import socket
import struct
import pickle

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.bind(('0.0.0.0', 5555))
    sock.listen()
    conn, addr =  sock.accept()
    with conn:
        print("Waiting...")        
        data1 = conn.recv(4096)
        data2 = conn.recv(4096)
        data_arr1 = pickle.loads(data1)
        data_arr2 = pickle.loads(data2)
        common = []
        for elem in data_arr1:
            if elem not in data_arr2:
                common.append(elem)
        print(f"server recieved " + str(data_arr1) + " and " + str(data_arr2))
        res_arr = pickle.dumps(common)
        conn.send(res_arr)
        #conn.sendall(data)
