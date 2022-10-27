import random
import socket
import time
import pickle

if __name__ == '__main__':
    try:
        s = socket.create_connection(('localhost', 5555))
        #udp_s.bind(('0.0.0.0', 5555))
    except socket.error as msg:
        print("Error: ", msg.strerror)
        exit(-1)

    finished = False

    data = s.recv(1024)
    print(data.decode('ascii'))

    data = s.recv(100)
    total = pickle.loads(data)
    guesses = 0
    while not finished:
        try:
            guess = random.randint(0, 9)
            print(f"My guess is {guess}")
            s.sendall(pickle.dumps(guess))
            answer = s.recv(1000)
            pos = pickle.loads(answer)
            if pos != 0:
                guesses += 1
                if guesses == total:
                    finished = True
                    print("i won")
                    s.sendall(pickle.dumps("i won"))
            time.sleep(1)
        except socket.error as msg:
            print('Error: ', msg.strerror)
            s.close()
            exit(-2)

    s.close()
    udp_s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    win, adr = udp_s.recvfrom(1000)
    w = pickle.loads(win)
    print(f"Client from {w}")
    s.close()
    print("I am done bye")