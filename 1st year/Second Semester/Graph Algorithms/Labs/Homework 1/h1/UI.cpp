#include <iostream>
#include "UI.h"
#include <string>
#include <sstream>
#include <fstream>

using namespace std;


void UI::printMenu()
{
	cout << endl;
	cout << "\n 0. Exit." << endl;
	cout << "1. Read graph from file" << endl;
	cout << "2. Print graph" << endl;
    cout << "3. Search for vertex" << endl;
    cout << "4.Search for edge" << endl;
    cout << "5.Add vertex/ edge" << endl;
    cout << "6.Remove vertex/ edge" << endl;
    cout << "7.Modify cost" << endl;
    cout << "8.Get number of vertices" << endl;
    cout << "9.Get in degree/ out degree" << endl;
    cout << "10.Parse the set of vertices" << endl;
    cout << "11.Parse the set of inbound edges" << endl;
    cout << "12.Parse the set of outbound edges" << endl;
    cout << "13.Save graph" << endl;
    cout << "14.Copy graph" << endl;
    cout << "15.Generate random graph" << endl;
}

void UI::printGraph()
{
    vector<int> vertices = this->g.AllVertices();
    for (auto& it : vertices) {
        if (this->g.ParseOutBoundEdge(it).size() == 0)
        {
            if (this->g.ParseInBoundEdge(it).size() == 0)
                cout << to_string(it) + ": isolated vertex \n";

        }
        else {
            vector<int> outbounds = this->g.ParseOutBoundEdge(it);
            for (auto& it2 : outbounds) {
                Key edge(it, it2);
                cout << "Edge: " + to_string(it) + " to " + to_string(it2) + " with the cost of: " + to_string(this->g.GetCosts()[edge]) + "\n";
            }
        }
    }

}

void UI::resetGraph()
{
    Graph new_g;
    this->g = new_g;

}

void UI::readGraphData(string file_name)
{
    string line;
    ifstream input(file_name, 0);
    string a, b;

    input >> a >> b;
    int nr_edges = stoi(b);
    for (int i = 0; i < stoi(a); i++) {
        g.AddVertex(i);
    }
    while (nr_edges != 0)
    {
        int source, target, cost;
        input >> source >> target >> cost;
        this->g.AddEdge(source, target, cost);
        nr_edges--;
    }
}

void UI::readGraphData2(string file_name)
{
    ifstream input(file_name, 0);
    while (input) {
        string source, target, cost;
        input >> source >> target >> cost;
        if (target == "") {
            int x = stoi(source);
            this->g.AddVertex(x);
        }
        else {
            int x = stoi(source);
            int y = stoi(target);
            int c = stoi(cost);
            this->g.AddVertex(x);
            this->g.AddVertex(y);
            this->g.AddEdge(x, y, c);
        }
    }
}

void UI::GenerateRandom(int i, int j)
{
    if (j > i * i) {
        cout << " Imposible to generate";
        return;
    }
    else {
        this->resetGraph();
        /*this->g.nrVertices = i;*/
        for (int index = 0; index < i; index++) {
            this->g.AddVertex(index);
        }
        while (j != 0) {
            int source = 0 + (rand() % (i - 1));
            int target = 0 + (rand() % (i - 1));
            int cost = 0 + (rand() % (i - 1));
            this->g.AddVertex(source);
            this->g.AddVertex(target);
            if (this->g.AddEdge(source, target, cost)) {
                j--;
            }
        }
    
    }

}


void UI::start()
{

    bool done = true;
    int cmd;

    while (done) {
        UI::printMenu();
        cout << "\n Choose a command: ";
        cin >> cmd;
        cin.ignore();
        if (cmd == 0) {
            cout << "Bye-Bye! \n";
            done = false;
        }
        else
            if (cmd == 1) {
                string format, file_name;
                cout << "a.File with number of vertices and edges \n";
                cout << "b.File with only data about egdes \n";
                cin >> format;
                cout << "Give file name: ";
                cin >> file_name;
                if (format == "a")
                    readGraphData(file_name);
                else
                    readGraphData2(file_name);
            }
            else
                if (cmd == 2)
                    printGraph();
                else
                    if (cmd == 3) {
                        int vertex;
                        cout << "Give vertex: ";
                        cin >> vertex;
                        if (g.SearchVertex(vertex) == false)
                            cout << "Vertex does not exist";
                        else
                            cout << "Vertex exists";
                    }
                    else
                        if (cmd == 4) {
                            int source, target;
                            cout << "Give source of the edge: ";
                            cin >> source;
                            cout << "Give target of the edge: ";
                            cin >> target;
                            if (g.SearchEdge(source, target))
                                cout << "Edge exists \n";
                            else
                                cout << "Edge does not exist \n";
                        }
                        else
                            if (cmd == 5) {
                                string cmmd;
                                cout << "Add vertex or edge? ";
                                cin >> cmmd;
                                if (cmmd == "vertex") {
                                    int vertex;
                                    cout << "Give vertex: ";
                                    cin >> vertex;
                                    if (g.AddVertex(vertex) == false)
                                        cout << "Vertex already exists \n";
                                    else
                                        cout << "Vertex added successfully \n";
                                }
                                else {
                                    int source, target, cost;
                                    cout << "Give source of the edge: ";
                                    cin >> source;
                                    cout << "Give target of the edge: ";
                                    cin >> target;
                                    cout << "Give cost of the edge: ";
                                    cin >> cost;

                                    if (g.AddEdge(source, target, cost))
                                        cout << "Edge added successfully \n";
                                    else
                                        cout << "Edge already exists \n";
                                }
                            }
                            else
                                if (cmd == 6) {
                                    string cmmd;
                                    cout << "Remove vertex or edge? ";
                                    cin >> cmmd;
                                    if (cmmd == "vertex") {
                                        int vertex;
                                        cout << "Give vertex: ";
                                        cin >> vertex;
                                        if (g.RemoveVertex(vertex) == false)
                                            cout << "Vertex does not exist \n";
                                        else
                                            cout << "Vertex removed successfully \n";
                                    }
                                    else {
                                        int source, target, cost;
                                        cout << "Give source of the edge: ";
                                        cin >> source;
                                        cout << "Give target of the edge: ";
                                        cin >> target;

                                        if (g.RemoveEdge(source, target))
                                            cout << "Edge removed successfully \n";
                                        else
                                            cout << "Edge does not exist \n";
                                    }
                                }
                                else
                                    if (cmd == 7) {
                                        int source, target, cost;
                                        cout << "Give source of the edge: ";
                                        cin >> source;
                                        cout << "Give target of the edge: ";
                                        cin >> target;
                                        cout << "Give cost of the edge: ";
                                        cin >> cost;

                                        if (g.ModifyCost(source, target, cost))
                                            cout << "Cost modified successfully \n";
                                        else
                                            cout << "Edge does not exist \n";
                                    }
                                    else
                                        if (cmd == 8) {
                                            cout << "Number of vertices is: " + to_string(g.GetNrVertices());
                                        }
                                        else
                                            if (cmd == 9) {
                                                string cmmd;
                                                cout << "Search for indegree or outdegree? ";
                                                cin >> cmmd;
                                                if (cmmd == "in") {
                                                    int vertex;
                                                    cout << "Give vertex: ";
                                                    cin >> vertex;
                                                    if (g.GetInDegree(vertex) == -1)
                                                        cout << "Vertex does not exist \n";
                                                    else
                                                        cout << "InDegree is: " + to_string(g.GetInDegree(vertex)) + "\n";
                                                }
                                                else {
                                                    int vertex;
                                                    cout << "Give vertex: ";
                                                    cin >> vertex;
                                                    if (g.GetOutDegree(vertex) == -1)
                                                        cout << "Vertex does not exist \n";
                                                    else
                                                        cout << "OutDegree is: " + to_string(g.GetOutDegree(vertex)) + "\n";
                                                }
                                            }
                                            else
                                                if (cmd == 10) {
                                                    vector<int> verticies = g.ParseVertices();
                                                    for (auto& it : verticies)
                                                        cout << to_string(it);
                                                }
                                                else
                                                    if (cmd == 11) {
                                                        int vertex;
                                                        cout << "Give vertex: ";
                                                        cin >> vertex;
                                                        vector<int> aux = { -1 };
                                                        vector<int> aux2 = {};
                                                        if (aux == g.ParseInBoundEdge(vertex))
                                                            cout << "No such vertex \n";
                                                        else
                                                            if (aux2 == g.ParseInBoundEdge(vertex))
                                                                cout << "No inbounds \n";
                                                            else {
                                                                vector<int> inbound = g.ParseInBoundEdge(vertex);
                                                                for (auto& it : inbound)
                                                                    cout << to_string(it) + "\n";
                                                            }
                                                    }
                                                    else
                                                        if (cmd == 12) {
                                                            int vertex;
                                                            cout << "Give vertex: ";
                                                            cin >> vertex;
                                                            vector<int> aux = { -1 };
                                                            vector<int> aux2 = {};
                                                            if (aux == g.ParseOutBoundEdge(vertex))
                                                                cout << "No such vertex \n";
                                                            else
                                                                if (aux2 == g.ParseOutBoundEdge(vertex))
                                                                    cout << "No inbounds \n";
                                                                else {
                                                                    vector<int> outbound = g.ParseOutBoundEdge(vertex);
                                                                    for (auto& it : outbound)
                                                                        cout << to_string(it) + "\n";
                                                                }
                                                        }
                                                        else
                                                            if (cmd == 13) {
                                                                string file_name;
                                                                cout << "Give file name: ";
                                                                cin >> file_name;
                                                                g.SaveGraph(file_name);
                                                            }
                                                            else
                                                                if (cmd == 14) {
                                                                    string cmmd;
                                                                    cout << "Create a copy or replace: ";
                                                                    cin >> cmmd;
                                                                    if (cmmd == "create")
                                                                        this->g_copy = g.CopyGraph();
                                                                    else
                                                                        this->g = this->g_copy;
                                                                }
                                                                else
                                                                    if (cmd == 15) {
                                                                        int nr_vertices, nr_edges;
                                                                        cout << "Give number of vertices: ";
                                                                        cin >> nr_vertices;
                                                                        cout << "Give number of edges: ";
                                                                        cin >> nr_edges;
                                                                        GenerateRandom(nr_vertices, nr_edges);
                                                                        printGraph();
                                                                    }
                                                                    else
                                                                        cout << "Wrong command \n";
    }
}