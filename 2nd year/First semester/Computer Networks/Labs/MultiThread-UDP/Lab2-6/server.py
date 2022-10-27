import pickle
import socket
import random
import time


if __name__ == '__main__':
    while True:
        random.seed()
        start = 1
        stop = 2 * 300 - 1
        my_num = random.randint(start, stop)
        all = [None] * 5
        print('Server number: ', my_num)
        try:
            rs = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
            rs.bind(('0.0.0.0', 5555))
            time.sleep(10)
        except socket.error as msg:
            print(msg.strerror)
            exit(-1)
        guessed = False
        while not guessed:
            nr1, adr = rs.recvfrom(1000)
            all.append(adr)
            nr = pickle.loads(nr1)
            if nr == my_num:
                guessed = True
                winner = adr
                for i in all:
                    if i == winner:
                        rs.sendto(pickle.dumps("You won"), winner)
                    else:
                        if i != None:
                            rs.sendto(pickle.dumps("You lost"), i)
            else:
                if nr < my_num:
                    rs.sendto(pickle.dumps("G"), adr)
                else:
                    rs.sendto(pickle.dumps("S"), adr)

        if guessed:
            for i in all:
                if i == winner:
                    rs.sendto(pickle.dumps("You won"), winner)
                else:
                    if i != None:
                        rs.sendto(pickle.dumps("You lost"), i)
