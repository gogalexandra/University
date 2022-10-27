from queue import LifoQueue, Queue


def DFTraversal(self, vertex, visited, processed):
    for out in self.getOutboundNeighbours(vertex):
        if out not in visited:
            visited.append(out)
            self.DFTraversal(vertex, visited, processed)

    processed.put(vertex)


def KosarajuAlgorithm(self):
    # a map that associates, to each vertex, the ID of its strongly connected component
    components = {}

    processed = LifoQueue(maxsize=self.getNrOfVertices)
    visited = []

    for v in self.getAllVertices:
        if v not in visited:
            visited.append(v)
            self.DFTraversal(v, visited, processed)

    visited.clear()
    queue = Queue(maxsize=self.getNrOfVertices)
    counter = 0

    while not processed.empty():
        v = processed.get()
        if v not in visited:
            counter += 1
            components[v] = counter
            queue.put(v)
            visited.append(v)
            while not queue.empty():
                x = queue.get()
                for in_neighbour in self.getInboundNeighbours(x):
                    if in_neighbour not in visited:
                        visited.append(in_neighbour)
                        queue.put(in_neighbour)
                        components[in_neighbour] = counter