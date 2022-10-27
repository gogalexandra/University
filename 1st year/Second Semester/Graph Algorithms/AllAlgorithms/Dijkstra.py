import heapq


def getPath(self, prev, final, start):
    path = []
    if final not in prev:
        return None  # the path could not be found
    while final != start:
        path.append(final)
        final = prev[final]
    return path + [start]


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