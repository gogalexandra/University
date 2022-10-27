import socket
import time
import pickle

if __name__ == '__main__':
    while True:
        try:
            s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        except socket.error as msg:
            print("Error: ", msg.strerror)
            exit(-1)
        try:
            dim = int(input("give dim of array"))
            nums = []
            for i in range(dim):
                n = int(input("Number: "))
                nums.append(n)
            arr = pickle.dumps(nums)
            s.sendto(arr, ("192.168.1.17", 1234))
            answer, adr = s.recvfrom(2000)
        except socket.error as msg:
            print('Error: ', msg.strerror)
            s.close()
            exit(-2)
        ans = pickle.loads(answer)
        print(' Answer ', str(ans))
        finished = True
        time.sleep(0.25)
    s.close()
    print("i am done")
