import socket
import time
import pickle

if __name__ == '__main__':
    try:
        s = socket.create_connection(('localhost', 5555))
    except socket.error as msg:
        print("Error: ", msg.strerror)
        exit(-1)

    finished = False

    data = s.recv(1024)
    print(data.decode('ascii'))

    while not finished:
        try:
            guess = int(input("Pick your guess: "))
            s.sendall(pickle.dumps(guess))
            answer = s.recv(1024)
        except socket.error as msg:
            print('Error: ', msg.strerror)
            s.close()
            exit(-2)
        ans = pickle.loads(answer)
        print(ans)
        finished = True
        time.sleep(0.25)

    s.close()
    print("I am done bye")