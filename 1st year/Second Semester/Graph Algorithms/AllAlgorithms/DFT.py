from queue import LifoQueue

visited = None
DOut = {}

def DFTRecursive(v, visited):
    if visited is None:
        visited = []
        # prev = {}
        # dist = {}
    visited.append(v)
    for i in DOut[v]:
        if i not in visited:
            # prev[y] = x
            # dist[y] = dist[x] + 1
            DFT(i, visited)
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