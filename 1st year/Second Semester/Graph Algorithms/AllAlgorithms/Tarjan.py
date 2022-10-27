def findHighestCostPath(self, source_vertex, target_vertex):
    sorted_v = self.topologicalSort()
    if sorted_v is None:
        raise ValueError('The graph is not a DAG')

    costs_list = [-math.inf for i in range(self.getNrOfVertices)]
    costs_list[source_vertex] = 0

    previous_list = [None for i in range(self.getNrOfVertices)]

    for vertex in sorted_v:
        # if the vertex is the end one we break, meaning we found our walk
        if vertex == target_vertex:
            break

        for out_vertex in self.getOutboundNeighbours(vertex):
            if costs_list[out_vertex] < costs_list[vertex] + self.getCost(vertex, out_vertex):
                costs_list[out_vertex] = costs_list[vertex] + self.getCost(vertex, out_vertex)
                previous_list[out_vertex] = vertex

    if costs_list[target_vertex] == -math.inf:
        raise ValueError(f'There is no walk between vertex {source_vertex} and vertex {target_vertex}')

    # reconstructing the final path
    path = []

    vertex = target_vertex
    while costs_list[vertex] != -math.inf:
        path.append(vertex)
        vertex = previous_list[vertex]
        if vertex == source_vertex:
            break
    path.append(source_vertex)

    path.reverse()

    return costs_list[target_vertex], path


# TOPOLOGICAL SORTING USING DEPTH FIRST SEARCH TRAVERSAL

def topologicalSort(self):
    sorted_v = []
    fully_processed_v = set()
    in_process_v = set()

    for vertex in self.getAllVertices:
        if vertex not in fully_processed_v:
            ok = self.tSDepthFirstSearch(vertex, sorted_v, fully_processed_v, in_process_v)
            if not ok:
                sorted_v = None
                break

    return sorted_v


def tSDepthFirstSearch(self, vertex, sorted_v, fully_processed_v, in_process_v):
    in_process_v.add(vertex)
    for in_vertex in self._din[vertex]:
        if in_vertex in in_process_v:
            return False
        elif in_vertex not in fully_processed_v:
            ok = self.tSDepthFirstSearch(in_vertex, sorted_v, fully_processed_v, in_process_v)
            if not ok:
                return False

    in_process_v.remove(vertex)
    sorted_v.append(vertex)
    fully_processed_v.add(vertex)

    return True