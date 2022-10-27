from queue import Queue


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