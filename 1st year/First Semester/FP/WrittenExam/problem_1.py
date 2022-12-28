# SparseMatrix
from itertools import chain


class SparseMatrix:
    def __init__(self, x, y):
        self._rows = x
        self._columns = y
        self._data = []

    def get(self, x, y):
        for i in self._data:
            if i[0] == x and i[1] == y:
                return i[2]

    def set(self, x, y, value):
        ok = False
        if x < self._rows - 1 and y < self._columns - 1:
            for i in self._data:
                if i[0] == x and i[1] == y:
                    i[2] = value
                    ok = True
            if not ok:
                self._data.append([x, y, value])
        else:
            raise ValueError()

    def __str__(self):
        string = ''
        for i in range(self._rows):
            for j in range(self._columns):
                ok = False
                for elem in self._data:
                    if elem[0] == i and elem[1] == j:
                        string = string + ' ' + str(elem[2])
                        ok = True
                if not ok:
                    string = string + ' ' + '0'
            string += '\n'
        return string


def next_prime_nr(nr):
    done = False
    while not done:
        nr += 1
        if is_prime(nr):
            done = True
            return nr


def is_prime(nr):
    d = 2
    if nr == 0 or nr == 1:
        return False
    while d <= nr // 2:
        if nr % d == 0:
            return False
        d += 1
    if d >= nr // 2:
        return True


class PrimeList:
    def __init__(self):
        self._data = 0

    def __getitem__(self, i):
        number = 2
        index = 0
        while index <= i:
            if is_prime(number) == True and index == i:
                self._data = number
                return self._data
            elif is_prime(number):
                index += 1
                number += 1
            else:
                number += 1

    def __setitem__(self, key, value):
        raise ValueError

data = PrimeList()
for i in range(10):
    print(data[i])

print(data[4])
data[4] = 7
# m1 = SparseMatrix(3, 3)
# print(m1)
# m1.set(1, 1, 2)
# print(m1)
# a = m1.get(1, 1)
# m1.set(1, 1, m1.get(1, 1) + 1)
# print(m1)
# try:
#     m1.set(4, 4, 5)
# except:
#     print(ValueError)


# Sparse list

class SpareList:
    def __init__(self):
        self._data = []

    def __iter__(self):
        # Returns an iterator over this data structure
        self._poz = 0
        return self

    def __next__(self):
        # Stop iteration when other elements are not available
        if self._poz == len(self._data):
            raise StopIteration()
        # Move to the next element
        self._poz += 1
        return self._data[self._poz - 1]

    def __add__(self, elem):
        rezultat = []
        l = 0
        for i in self._data:
            if i[0] > l:
                l = i[0]

        for i in elem:
            self._data.append([i[0] + l + 1, i[1]])
        return self._data

    def __setitem__(self, key, value):
        ok = False
        for i in self._data:
            if i[0] == key:
                i[1] = value
                ok = True
        if not ok:
            self._data.append([key, value])

    def __getitem__(self, id_):
        for i in self._data:
            if i[0] == id_:
                return i[1]
        return '0'

    def __len__(self):
        l = 0
        for i in self._data:
            if i[0] > l:
                l = i[0]
        return l + 1
#
#
# data1 = SpareList()
# data1[3] = "a"
# data2 = SpareList()
# data2[2] = "b"
# data = data1 + data2
# for i in range(0, len(data) + 1):
#     print(data[i])
