import socket

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.bind(('0.0.0.0', 5555))
    sock.listen()
    conn, addr = sock.accept()
    with conn:
        print("Waiting...")
        while True:
            data = conn.recv(10)
            if not data:
                break
            print(f"got {data}")
