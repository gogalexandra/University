import math


def minimumCostWalkUsingDynamicProgramming(self, source_vertex, target_vertex, ):
        # initializing the cost matrix
        cost_matrix = [[-1 for x in range(self.getNrOfVertices)] for y in range(self.getNrOfVertices)]

        before = {}
        for x in range(self.getNrOfVertices):
            before[x] = -1

        for x in self.getAllVertices:
            if x == source_vertex:
                cost_matrix[0][x] = 0
            else:
                cost_matrix[0][x] = math.inf

        # going through the matrix and setting the cost for any walk worth taking into consideration
        for k in range(1, self.getNrOfVertices):
            for x in self.getAllVertices:
                minimum_path = math.inf
                minimum_node = -1

                for in_neighbour in self.getInboundNeighbours(x):
                    current_minimum = min(minimum_path, cost_matrix[k-1][in_neighbour] + self.getCost(in_neighbour, x))
                    if minimum_path > current_minimum:
                        minimum_node = in_neighbour
                    minimum_path = current_minimum

                if cost_matrix[k-1][x] > minimum_path:
                    before[x] = minimum_node
                cost_matrix[k][x] = min(cost_matrix[k-1][x], minimum_path)

        if cost_matrix[self.getNrOfVertices - 1][target_vertex] == math.inf:
            raise ValueError(f'There is no walk between vertex {source_vertex} and vertex {target_vertex}')

        return cost_matrix, before

def reconstructWalk(self, end_vertex, cost_matrix, before_vertices):

        walk = []
        current_vertex = end_vertex
        walk.append(current_vertex)

        while before_vertices[current_vertex] != -1:
            walk.append(before_vertices[current_vertex])
            current_vertex = before_vertices[current_vertex]

            if len(walk) > self.getNrOfVertices * self.getNrOfVertices:
                raise ValueError('Negative cycle!')

        walk.reverse()
        return cost_matrix[len(walk) - 1][end_vertex], walk