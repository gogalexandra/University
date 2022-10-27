from queue import Queue
nrVertices = 10
DOut = {}
s = 0

def BFT(s):
    q = Queue(maxsize=nrVertices)
    prev = {}
    dist = {}
    visited = []
    q.put(s)
    visited.append(s)
    dist[s] = 0
    while not q.isEmpty():
        x = q.get()
        for y in DOut[x]:
            if y not in visited:
                q.put(y)
                visited.append(y)
                dist[y] = dist[x] + 1
                prev[y] = x

    return visited
