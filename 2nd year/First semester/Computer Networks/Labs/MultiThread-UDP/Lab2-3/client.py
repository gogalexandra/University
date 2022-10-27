import random
import socket
import struct
import time
import pickle
if __name__ == '__main__':
        try:
            s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        except socket.error as msg:
            print("Error: ", msg.strerror)
            exit(-1)

        random.seed()
        start = 1
        stop = 2 * 300 - 1
        my_num = random.randint(start, stop)
        print(f"The chosen number from this client is {my_num}")
        s.sendto(pickle.dumps(my_num), ("192.168.1.17", 5555))

        ans, adr = s.recvfrom(100)
        answer = pickle.loads(ans)

        print(f"{answer}")