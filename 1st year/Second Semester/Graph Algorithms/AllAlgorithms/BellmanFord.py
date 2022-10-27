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

    path = self.getPath(prev, 0, 1) #(this is not ok must chage to call nicely)
    return dist, path