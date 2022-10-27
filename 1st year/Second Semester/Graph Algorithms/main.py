import random


class Graph:
    def __init__(self, nr_vertices, pairs_list):
        self._nrVertices = nr_vertices
        self._nOut = [list() for i in range(nr_vertices)]
        for p in pairs_list:
            self._nOut[p[0]].append(p[1])

    def parseX(self):
        return [i for i in range(self._nrVertices)]

    def parseNOut(self, x):
        return list(self._nOut[x])

    def parseNIn(self, x):
        ret = []
        for y in range(self._nrVertices):
            if x in self._nOut[y]:
                ret.append(y)
        return ret


def generateRandom(n, m):
    g = Graph(n, [])
    while m > 0:
        x = random.randrange(n)
        y = random.randrange(n)
        if y not in g.parseNOut(x):
            g._nOut[x].append(y)
            m = m - 1
    return g


g = generateRandom(10, 20)
for x in g.parseX():
    line = str(x) + ": "
    for y in g.parseNOut(x):
        line = line + " " + str(y)
    print(line)

print(" ")
# See PyCharm help at https://www.jetbrains.com/help/pycharm/
