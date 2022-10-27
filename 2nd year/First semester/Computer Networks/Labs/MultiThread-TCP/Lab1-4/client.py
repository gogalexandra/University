import socket
import pickle

if __name__ == '__main__':
    try:
        cs = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        cs.connect(('192.168.1.17', 5555))
    except socket.error as msg:
        print("Error: ", msg.strerror)
        exit(-1)

    msg = cs.recv(100)
    print(f"{msg}")

    dim1 = int(input("Give dim: "))
    arr1 = []
    for i in range(dim1):
        c = input("Give char: ")
        arr1.append(c)

    dim2 = int(input("Give dim: "))
    arr2 = []
    for i in range(dim2):
        c = input("Give char: ")
        arr2.append(c)

    cs.send(pickle.dumps(sorted(arr1)))
    cs.send(pickle.dumps(sorted(arr2)))

    data = cs.recv(100)
    final = pickle.loads(data)
    print(f"Got from server the result: {final}")
    print("I am done ciao")
