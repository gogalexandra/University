import socket

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.bind(('0.0.0.0', 5555))
    sock.listen()
    conn, addr =  sock.accept()
    with conn:
        print("Waiting...")
        while True:
            data = conn.recv(100)
            if not data:
                break
            print(f"string recieved is {data} - reverse string {data[::-1]}")
            conn.sendall(data)
