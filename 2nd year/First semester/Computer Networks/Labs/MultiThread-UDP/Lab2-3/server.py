import pickle
import random
import socket
import time


def best_aprox(values):
    if values[0][0] > my_num:
        diff = values[0][0] - my_num
    else:
        diff = my_num - values[0][0]
    best = values[0][1]
    for x in values:
        if x[0] > my_num:
            aux = x[0] - my_num
        else:
            aux = my_num - x[0]
        if aux < diff:
            diff = x[0]
            best = x[1]
    return best, diff


if __name__ == '__main__':
    while True:
        random.seed()
        start = 1
        stop = 2 * 300 - 1
        my_num = random.randint(start, stop)
        clients = []
        values = []
        done = False
        client_count = 0
        winner = None
        print(f"The chosen number from server is {my_num}")

        try:
            s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
            s.bind(('192.168.1.17', 5555))
        except socket.error as msg:
            print(f"{msg.strerror}")
            exit(-1)

        while not done:
            data, adr = s.recvfrom(2000)
            nr = pickle.loads(data)

            client_count += 1
            print(f"Client {client_count} said {nr}")

            clients.append(adr)
            values.append((nr, adr))
            if client_count == 3:
                done = True

        if done:
            winner, diff = best_aprox(values)
            for i in clients:
                if i == winner:
                    s.sendto(pickle.dumps(f"You have the best guess with an error of {diff}"), winner)
                else:
                    if i != None:
                        s.sendto(pickle.dumps("You lost!"), i)
