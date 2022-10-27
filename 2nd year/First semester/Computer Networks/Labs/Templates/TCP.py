# SERVER
import socket

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    # binds pair to socket
    sock.bind(('0.0.0.0', 5555))
    # listens for connections
    sock.listen()
    # accepts a connection: conn= new socket obj for sending and receiving data | addr = addr bound to the socket
    # from client
    conn, addr = sock.accept()
    with conn:
        print("Waiting...")
        while True:
            # TODO
            # receive info
            data = conn.recv(100)

            # send info
            sock.sendall()

            break
# CLIENT

# here maybe some input

# method 2 for creating and connecting in one line
s = socket.create_connection(('192.168.1.17', 5555))

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as c_sock:
    # create socket for client, connect to the server socket
    c_sock.connect(('192.168.1.17', 5555))

    # send data using pickle/struct/encode
    c_sock.send()
    # receive data and translate it
    result = c_sock.recv(4096)
