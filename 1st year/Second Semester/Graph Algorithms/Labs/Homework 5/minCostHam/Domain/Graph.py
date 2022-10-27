import copy
import heapq
from itertools import permutations
from math import inf
from queue import Queue


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

    def getPath(self, prev, final):
        path = []
        if final not in prev:
            return None  # the path could not be found
        while final != None:
            path.append(final)
            final = prev[final]
        return path

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

    def TopologicalSort(self):
        sorted = []
        count = {}
        q = Queue()
        for x in self.ParseVertices():
            degree_in, degree_out = self.GetDegree(x)
            count[x] = degree_in
            if count[x] == 0:
                q.put(x)
        while not q.empty():
            x = q.get()
            sorted.append(x)
            for y in self.ParseOutboundEdge(x):
                count[y] = count[y] - 1
                if count[y] == 0:
                    q.put(y)
        if len(sorted) < self.GetNrVertices():
            sorted = None
        return sorted

    def HighestCostPath(self, start, end):
        sorted_list = self.TopologicalSort()
        if sorted_list == None:
            return 0, []
        else:
            dist = [-inf for i in range(self.GetNrVertices())]
            dist[start] = 0
            prev = [None for i in range(self.GetNrVertices())]
            for u in sorted_list:
                if u == end:
                    break
                for v in self.DOut[u]:
                    if dist[v] < dist[u] + self.DCost[(u, v)]:
                        dist[v] = dist[u] + self.DCost[(u, v)]
                        prev[v] = u
            l = []
            if (dist[end] != -inf):
                u = end
                while u != start:
                    l.append(u)
                    u = prev[u]
                l.append(start)

            return dist[end], l

    def MinimumCostHamiltonianCycle(self, start_vertex):
        # mark every node as unvisited an initialise the result array
        visited = {i: 0 for i in self.ParseVertices()}
        result = []
        # mark the starting vertex as visited
        visited[start_vertex] = 1
        path = [start_vertex]
        self.buildPath(start_vertex, start_vertex, path, 0, visited, 1, result)
        # the lowest cost path is searched in the result vector
        lowest_cost = min(result, key=lambda t: t[1]) if len(result) else None
        # if the min() function returns None it means there are no result
        if lowest_cost == None:
            return None
        else:
            return lowest_cost

    def buildPath(self, start_vertex, current_vertex, path, cost, visited, count, solutions):
        # if we have all the vertices and the current vertex is the starting vertex => we have a possible solution
        if count == len(visited) and self.SearchEdge(current_vertex, start_vertex) == True:
            solutions.append((path.copy() + [start_vertex], cost + self.DCost[(current_vertex, start_vertex)]))
            return

        # we go through the outbound edges of the current vertex
        for vertex in self.DOut[current_vertex]:
            #if it is not visited, we add it to the path and call the recursive function again
            if not visited[vertex]:
                visited[vertex] = 1
                path.append(vertex)
                self.buildPath(start_vertex, vertex, path, cost + self.DCost[(current_vertex, vertex)], visited, count + 1, solutions)
                path.remove(vertex)
                visited[vertex] = 0

    def getPath(self, prev, final, start):
        path = []
        if final not in prev:
            return None  # the path could not be found
        while final != start:
            path.append(final)
            final = prev[final]
        return path + [start]

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

        path = self.getPath(prev, 3, 0)  # (this is not ok must chage to call nicely)
        return dist, path


