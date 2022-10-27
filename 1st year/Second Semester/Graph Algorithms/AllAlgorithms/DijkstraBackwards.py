import heapq


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