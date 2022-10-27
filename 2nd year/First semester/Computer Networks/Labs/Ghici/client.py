import pickle
import random
import socket
import struct
import time

if __name__ == '__main__':
    try:
        s = socket.create_connection(('localhost', 5555))
    except socket.error as msg:
        print("Error: ", msg.strerror)
        exit(-1)
    data = s.recv(1024)
    print(data.decode('ascii'))

    finished = False
    count = 0
    cuv = input("Give word: ")
    s.sendall(pickle.dumps(cuv))
    p = s.recv(100)
    pos = pickle.loads(p)
    print("I have to guess: ", pos)
    while not finished:
        sum = int(input("Give your guess: "))
        try:
            s.sendall(pickle.dumps(sum))
            ans = s.recv(100)
            if pickle.loads(ans) == "wrong":
                count += 1
                print("wrong guess")
            else:
                if pickle.loads(ans) == "winner" or pickle.loads(ans) == "loser":
                    finished = True
                    break
            if count == 5:
                cf = "stop"
            else:
                cf = input("Stop/go on: ")
            if cf == "stop":
                finished = True
            s.sendall(pickle.dumps(cf))
        except socket.error as msg:
            print('Error: ', msg.strerror)
            s.close()
            exit(-2)

    s.close()
    if pickle.loads(ans) == "winner":
        print("I am a winner")
        print("i have ", count, "wrong answers")
    else:
        print("I am a loser")
        print("i have ", count, "wrong answers")
