import copy
import heapq


class Graph:

    def __init__(self):
        self.DIn = {}
        self.DOut = {}
        self.DCost = {}
        self.NrVertices = 0

    def AllVertices(self):
        return self.DIn.keys()

    def SearchVertex(self, v):
        if v not in self.AllVertices():
            return False
        else:
            return True

    def SearchEdge(self, x, y):
        if x not in self.AllVertices() or y not in self.AllVertices():
            return False
        else:
            for i in self.DOut[x]:
                if i == y:
                    return True
            return False

    def AddVertex(self, v):
        if self.SearchVertex(v):
            return False
        else:
            self.DIn[v] = []
            self.DOut[v] = []
            return True

    def AddEdge(self, x, y, c):
        if self.SearchEdge(x, y):
            return False
        else:
            self.DIn[y].append(x)
            self.DOut[x].append(y)
            self.DCost[(x, y)] = c
            return True

    def RemoveEdge(self, x, y):
        if not self.SearchEdge(x, y):
            return False
        else:
            self.DIn[y].remove(x)
            self.DOut[x].remove(y)
            del self.DCost[(x, y)]
            return True

    def RemoveVertex(self, v):
        if not self.SearchVertex(v):
            return False
        else:

            for i in self.ParseInboundEdge(v):
                del self.DCost[(i, v)]
                self.DOut[i].remove(v)

            for i in self.ParseOutboundEdge(v):
                del self.DCost[(v, i)]
                self.DIn[i].remove(v)

            del self.DIn[v]
            del self.DOut[v]
            return True

    def ModifyCost(self, x, y, c):
        if not self.SearchEdge(x, y):
            return False
        else:
            self.DCost[(x, y)] = c

    def GetNrVertices(self):
        return len(self.DIn.keys())

    def GetNrEdges(self):
        nr_edges = 0
        for i in self.AllVertices():
            if len(self.DOut[i]) != 0:
                nr_edges += len(self.DOut[i])
        return nr_edges

    def GetDegree(self, v):
        if not self.SearchVertex(v):
            return False
        else:
            return len(self.DIn[v]), len(self.DOut[v])

    def ParseVertices(self):
        return self.DIn.keys()

    def ParseInboundEdge(self, v):
        if not self.SearchVertex(v):
            return False
        else:
            if self.DIn[v] == []:
                return []
            else:
                return self.DIn[v]

    def ParseOutboundEdge(self, v):
        if not self.SearchVertex(v):
            return False
        else:
            if self.DOut[v] == []:
                return []
            else:
                return self.DOut[v]

    def SaveGraph(self, file_name):
        f = open(file_name, "w")
        for i in self.AllVertices():
            if len(self.DOut[i]) == 0:
                if len(self.DIn[i]) == 0:
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

    def getPath(self, prev, final, start):
        path = []
        if final not in prev:
            return None  # the path could not be found
        while final != start:
            path.append(final)
            final = prev[final]
        return path + [start]

    def DijkstraBackwards(self, start, final):
        dist = {}
        prev = {final: None}
        q = []
        dist[final] = 0
        heapq.heappush(q, (dist[final], final))
        while len(q) > 0:
            var, x = heapq.heappop(q)
            if (var == dist[x]):
                for y in self.DIn[x]:
                    if y not in prev or dist[y] > dist[x] + self.DCost[(y, x)]:
                        prev[y] = x
                        dist[y] = dist[x] + self.DCost[(y, x)]
                        heapq.heappush(q, (dist[y], y))
        if self.getPath(prev, start) is None:
            return None
        return self.getPath(prev, start), dist[start]

    def Dijkstra(self, start, final):
        dist = {}
        prev = {}
        q = []
        dist[start] = 0
        heapq.heappush(q, (dist[start], start))
        while len(q) > 0:
            var, x = heapq.heappop(q)
            if (var == dist[x]):
                for y in self.DOut[x]:
                    if y not in prev or dist[y] > dist[x] + self.DCost[(x, y)]:
                        prev[y] = x
                        dist[y] = dist[x] + self.DCost[(x, y)]
                        heapq.heappush(q, (dist[y], y))
        if self.getPath(prev, final, start) is None:
            return None
        return self.getPath(prev, final, start), dist[final]

    def BellmanFord(self, src):
        dist = [float("Inf")] * self.NrVertices
        dist[src] = 0
        prev = {}
        for i in range(self.NrVertices - 1):
            for x in self.ParseVertices():
                for y in self.DOut[x]:
                    if dist[x] != float("Inf") and dist[y] > dist[x] + self.DCost[(x, y)]:
                        dist[y] = dist[x] + self.DCost[(x, y)]
                        prev[y] = x

        for x in self.ParseVertices():
            for y in self.DOut[x]:
                if dist[x] != float("Inf") and dist[y] > dist[x] + self.DCost[(x, y)]:
                    print("Graph contains negative weight cycle")
                    return

        path = self.getPath(prev, 2, 0)
        return dist, path
