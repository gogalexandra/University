
import socket
import time

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
            str1 = input('your string is: ')
            s.sendall(str.encode(str1))
            answer = s.recv(20)
        except socket.error as msg:
            print('Error: ', msg.strerror)
            s.close()
            exit(-2)
        print(' Answer ', answer.decode('ascii'))
        finished = True
        time.sleep(0.25)

    s.close()
    print("I am done bye")
