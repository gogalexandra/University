import socket
import time
import pickle

if __name__ == '__main__':
    try:
        s = socket.create_connection(('10.178.197.171', 5555))
    except socket.error as msg:
        print("Error: ", msg.strerror)
        exit(-1)

    finished = False

    data = s.recv(1024)
    print(data.decode('ascii'))

    while not finished:
        try:
            p = input('Give path: ')
            path = pickle.dumps(p)
            s.sendall(path)
            answer = s.recv(100)
        except socket.error as msg:
            print('Error: ', msg.strerror)
            s.close()
            exit(-2)
        ans = pickle.loads(answer)
        if ans == -1:
            print(' File does not exists ')
        else:
            c = s.recv(1024)
            content = pickle.loads(c)
            name = p.split("/")[-1]
            name += "-copy"
            f = open(name, 'w')
            f.write(content)
            print("Created the file")
        finished = True
        time.sleep(0.25)

    s.close()
    print("I am done bye")