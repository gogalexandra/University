import random
from Domain.Graph import Graph


class UI:

    def __init__(self):
        self.Graph = Graph()
        self.GraphCopy = Graph()

    @staticmethod
    def print_menu():
        print("\n0.Exit")
        print("1.Read graph from file")
        print("2.Print graph ")
        print("3.Search for vertex")
        print("4.Search for edge")
        print("5.Add vertex/ edge")
        print("6.Remove vertex/ edge")
        print("7.Modify cost")
        print("8.Get number of vertices")
        print("9.Get in degree")
        print("10.Parse the set of vertices")
        print("11.Parse the set of inbound edges")
        print("12.Save graph")
        print("13.Copy graph")
        print("14.Generate random graph")
        print("15.Get connected components")

    def PrintGraph(self, g):
        printed = {}
        for i in g.AllVertices():
            if g.GetDegree(i) == 0:
                print(i, ": isolated vertex \n")
            else:
                for j in g.DOut[i]:
                    if (i, j) not in printed:
                        print("Edge: ", i, " to ", j, " with the cost: ", g.DCost[(i, j)], "\n")
                        printed[(i, j)] = 0
                        printed[(j, i)] = 0

    def ResetGraph(self):
        self.Graph.DIn = {}
        self.Graph.DOut = {}
        self.Graph.DCost = {}
        self.Graph.NrVertices = 0

    def ReadGraphData(self, file):
        self.ResetGraph()
        f = open(file, "r")
        lines = f.read().split("\n")
        params = lines[0].split(" ")
        self.Graph.NrVertices = int(params[0])
        for i in range(self.Graph.NrVertices):
            self.Graph.DOut[i] = []
        i = 1

        while i < len(lines) and lines[i] != "":
            line = lines[i].split(" ")
            x = int(line[0])
            y = int(line[1])
            c = int(line[2])
            self.Graph.AddEdge(x, y, c)
            i += 1

    def ReadGraphData2(self, file):
        self.ResetGraph()
        f = open(file, "r")
        lines = f.read().split("\n")
        list_of_vertices = []
        i = 0

        while i < len(lines) and lines[i] != "":
            line = lines[i].split(" ")
            if len(line) > 1:
                x = int(line[0])
                y = int(line[1])
                c = int(line[2])
                if x not in list_of_vertices:
                    list_of_vertices.append(x)
                    self.Graph.AddVertex(x)
                if y not in list_of_vertices:
                    list_of_vertices.append(y)
                    self.Graph.AddVertex(y)
                    self.Graph.NrVertices += 1
                self.Graph.AddEdge(x, y, c)
                i += 1
            else:
                x = int(line[0])
                if x not in list_of_vertices:
                    list_of_vertices.append(x)
                    self.Graph.AddVertex(x)
                i += 1

    def GenerateRandomGraph(self, nr_vertices, nr_edges, file_name):
        f = open(file_name, "w")
        if nr_edges > nr_vertices * nr_vertices:
            f.write("!!!Impossible to generate")
            return
        self.ResetGraph()
        self.Graph.NrVertices = nr_vertices
        for i in range(self.Graph.NrVertices):
            self.Graph.DOut[i] = []
        while nr_edges != 0:
            source_edge = random.randint(0, self.Graph.NrVertices - 1)
            target_edge = random.randint(0, self.Graph.NrVertices - 1)
            cost = random.randint(-10, 10)
            if not self.Graph.SearchEdge(source_edge, target_edge):
                if not self.Graph.SearchVertex(source_edge):
                    self.Graph.AddVertex(self)
                if not self.Graph.SearchVertex(target_edge):
                    self.Graph.AddVertex(self)
                self.Graph.AddEdge(source_edge, target_edge, cost)
                nr_edges -= 1
        self.Graph.SaveGraph(file_name)

    def FromCompToGraph(self, list):
        # Transforms the list with accessible vertices into a graph
        g = Graph()
        for i in self.Graph.DOut.keys():
            if i in list:
                g.AddVertex(i)
                for j in self.Graph.DOut[i]:
                    g.AddVertex(j)
                    g.AddEdge(i, j, self.Graph.DCost[(i, j)])
        return g

    def GetConnectedComponents(self):
        # Starts with all vertices and call BFT function to get a connected component, removes those vertices from the
        # list with all of them and continues to check for another component until there are no vertices left
        list_with_components = []
        possible_vertex = self.Graph.AllVertices()
        while possible_vertex != []:
            i = possible_vertex[0]
            forms_a_component = self.Graph.BFT(i)
            for x in forms_a_component:
                possible_vertex.remove(x)
            list_with_components.append(self.FromCompToGraph(forms_a_component))
        return list_with_components

    def start(self):
        Done = False

        while not Done:
            self.print_menu()
            command = input("Give a command: ")

            if command == "0":
                print("Goodbye!!!")
                Done = True
            elif command == "1":
                print("a.File with number of vertices and edges")
                print("b.File with only data about egdes")
                format = input('Give a format: ')
                file_name = input("Give file name: ")
                if format == "a":
                    self.ReadGraphData(file_name)
                else:
                    self.ReadGraphData2(file_name)

            elif command == "2":
                self.PrintGraph(self.Graph)

            elif command == "3":
                v = int(input("Give vertex: "))
                if self.Graph.SearchVertex(v):
                    print("Vertex exists! \n")
                else:
                    print("No such vertex! \n")

            elif command == "4":
                x = int(input("Give the source of the edge: "))
                y = int(input("Give the target of the edge: "))

                if self.Graph.SearchEdge(x, y):
                    print("Edge exists! \n")
                else:
                    print("No such edge! \n")

            elif command == "5":
                param = input("Add vertex or edge? ")
                if param == "vertex":
                    v = int(input("Give vertex: "))
                    if not self.Graph.AddVertex(v):
                        print("Vertex already exists! \n")
                    else:
                        print("Vertex added successfully! \n")
                else:
                    edge_s = int(input("Give source of the edge: "))
                    edge_t = int(input("Give target of the edge: "))
                    edge_c = int(input("Give cost of the edge: "))
                    if not self.Graph.AddEdge(edge_s, edge_t, edge_c):
                        print("Edge already exists! \n")
                    else:
                        print("Edge added successfully! \n")

            elif command == "6":
                param = input("Remove vertex or edge? ")
                if param == "vertex":
                    v = int(input("Give vertex: "))
                    if not self.Graph.RemoveVertex(v):
                        print("Vertex does not exists! \n")
                    else:
                        print("Vertex deleted successfully! \n")
                else:
                    edge_s = int(input("Give source of the edge: "))
                    edge_t = int(input("Give target of the edge: "))
                    if not self.Graph.RemoveEdge(edge_s, edge_t):
                        print("Edge does not exists! \n")
                    else:
                        print("Edge deleted successfully! \n")

            elif command == "7":
                edge_s = int(input("Give source of the edge: "))
                edge_t = int(input("Give target of the edge: "))
                edge_c = int(input("Give cost of the edge: "))
                if not self.Graph.ModifyCost(edge_s, edge_t, edge_c):
                    print("Edge does not exists! \n")
                else:
                    print("Cost updated successfully \n")

            elif command == "8":
                print("Number of vertices is: ", self.Graph.GetNrVertices(), "\n")

            elif command == "9":
                v = int(input("Give vertex: "))

                if self.Graph.GetDegree(v) == -1:
                    print("No such vertex! \n")
                else:
                    degree = self.Graph.GetDegree(v)
                    print("The degree of " + str(v) + " is :", degree, "\n")

            elif command == "10":
                print(self.Graph.ParseVertices(), "\n")

            elif command == "11":
                v = int(input("Give vertex: "))

                if not self.Graph.ParseInboundEdge(v):
                    print("No such vertex! \n")
                else:
                    if self.Graph.ParseInboundEdge(v) == []:
                        print("No inbounds for this vertex")
                    else:
                        print(self.Graph.ParseInboundEdge(v), "\n")

            elif command == "12":
                file_name = input("Give file name to save the graph: ")
                self.Graph.SaveGraph(file_name)

            elif command == "13":
                cmd = input("Create copy or replace with the made copy? ")
                if cmd == "create":
                    self.GraphCopy = self.Graph.CopyGraph()
                else:
                    self.Graph = self.GraphCopy

            elif command == "14":
                nr_vertices = int(input("Give number of vertices: "))
                nr_edges = int(input("Give number of edges: "))
                file_name = input("Give file name where to save: ")
                self.GenerateRandomGraph(nr_vertices, nr_edges, file_name)
                self.PrintGraph(self.Graph)

            elif command == "15":
                for g in self.GetConnectedComponents():
                    print("Connected component: \n")
                    self.PrintGraph(g)
            elif command == "16":
                all, prev, dist = self.Graph.DFT(2, None, {}, {})
                print("All accesible vertices are: ", all)
                print("Shortest path between 2 and 4 is: ", dist[4])
                print("Path is: 4, ")
                x = 4
                while prev[x] != 2:
                    print(prev[x])
                    x = prev[x]
                print("2")

            else:
                print("Wrong command! \n")
