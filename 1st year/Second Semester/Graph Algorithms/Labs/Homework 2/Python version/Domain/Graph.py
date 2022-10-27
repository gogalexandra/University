import copy
from queue import Queue, LifoQueue


class Graph:

    def __init__(self):
        self.DOut = {}
        self.DCost = {}
        self.NrVertices = 0

    def AllVertices(self):
        all = []
        for i in self.DOut.keys():
            all.append(i)
        return all

    def SearchVertex(self, v):
        if v not in self.AllVertices():
            return False
        else:
            return True

    def SearchEdge(self, x, y):
        if x not in self.AllVertices() or y not in self.AllVertices():
            return False
        else:
            if x in self.DOut[y]:
                return True
        return False

    def AddVertex(self, v):
        if self.SearchVertex(v):
            return False
        else:
            self.DOut[v] = []
            return True

    def AddEdge(self, x, y, c):
        if self.SearchEdge(x, y):
            return False
        else:
            self.DOut[x].append(y)
            self.DOut[y].append(x)
            self.DCost[(x, y)] = c
            self.DCost[(y, x)] = c
            return True

    def RemoveEdge(self, x, y):
        if not self.SearchEdge(x, y):
            return False
        else:
            self.DOut[y].remove(x)
            self.DOut[x].remove(y)
            del self.DCost[(x, y)]
            return True

    def RemoveVertex(self, v):
        if not self.SearchVertex(v):
            return False
        else:
            for i in self.DOut[v]:
                del self.DCost[(i, v)]
                self.DOut[i].remove(v)

            del self.DOut[v]
            return True

    def ModifyCost(self, x, y, c):
        if not self.SearchEdge(x, y):
            return False
        else:
            self.DCost[(x, y)] = c

    def GetNrVertices(self):
        return len(self.AllVertices())

    def GetNrEdges(self):
        return len(self.DCost.keys()) /2

    def GetDegree(self, v):
        if not self.SearchVertex(v):
            return -1
        else:
            return len(self.DOut[v])

    def ParseVertices(self):
        return self.AllVertices()

    def ParseInboundEdge(self, v):
        if not self.SearchVertex(v):
            return False
        else:
            if self.GetDegree(v) == 0:
                return []
            else:
                return self.DOut[v]

    def SaveGraph(self, file_name):
        f = open(file_name, "w")
        for i in self.AllVertices():
            if len(self.DOut[i]) == 0:
                line = str(i)
                f.write(line)
                f.write("\n")
            else:
                for j in self.DOut[i]:
                    line = str(i) + ' ' + str(j) + ' ' + str(self.DCost[(i, j)])
                    f.write(line)
                    f.write("\n")

    def CopyGraph(self):
        return copy.deepcopy(self)

    def BFT(self, v):
        # Breadth first traversal function to see all vertices that can be access from vertex v given as a parameter
        q = Queue(maxsize=self.GetNrVertices())
        prev = {}
        dist = {}
        visited = []
        q.put(v)
        dist[v] = 0
        visited.append(v)
        while not q.empty():
            x = q.get()
            for i in self.DOut[x]:
                if i not in visited:
                    q.put(i)
                    visited.append(i)
                    dist[i] = dist[x] + 1
                    prev[i] = x
        return visited

    def DFT(self, v):
        q = LifoQueue(maxsize=self.GetNrVertices())
        prev = {}
        dist = {}
        visited = []
        q.put(v)
        dist[v] = 0
        visited.append(v)
        while not q.empty():
            x = q.get()
            for i in self.DOut[x]:
                if i not in visited:
                    q.put(i)
                    visited.append(i)
                    dist[i] = dist[x] + 1
                    prev[i] = x
        return visited




