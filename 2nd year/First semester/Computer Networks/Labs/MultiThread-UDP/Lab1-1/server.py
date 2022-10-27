import socket
import time
import pickle


client_count = 0

if __name__ == '__main__':
    try:
        rs = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        rs.bind(('0.0.0.0', 1234))
    except socket.error as msg:
        print(msg.strerror)
        exit(-1)
    while True:
        arr, adr = rs.recvfrom(2000)
        a = pickle.loads(arr)
        try:
            print("client #", client_count, "from: ", adr)
            rs.sendto(pickle.dumps(sum(a)), adr)

        except socket.error as msg:
            print('Error:', msg.strerror)
            break
        print("Client ", client_count, " done")
        client_count += 1