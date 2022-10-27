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

        finished = False
        sr = 1
        er = 2 * 300 - 1
        random.seed()
        count = 0
        while not finished:
            my_num = random.randint(sr, er)
            count += 1
            print("My guess is: ", my_num)
            s.sendto(pickle.dumps(my_num), ("192.168.1.17", 5555))
            answer, adr = s.recvfrom(100)
            ans = pickle.loads(answer)
            if ans == "S":
                er = my_num
            elif ans == "G":
                sr = my_num
            else:
                finished = True
                if ans == "You won":
                    print("I won after ", count, " tries")
                else:
                    print("I lost after ", count, " tries")
                s.close()