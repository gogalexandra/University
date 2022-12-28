#pragma once
#include <map>
#include <vector>
#include <string>
#include <utility>

using namespace std;
typedef pair<int, int> Key;

class Graph{
private:

	map<int, vector<int>> DIn;
	map<int, vector<int>> DOut;
	map<Key, int> DCost;
    int nrVertices;

public:
    explicit Graph();
    Graph(const Graph& g);

    /*map<int, vector<int>> DIn;
    map<int, vector<int>> DOut;
    map<Key, int> DCost;
    int nrVertices;*/

    map<int, vector<int>> din();
    map<int, vector<int>> dout();
    map<Key, int> dcost();
    int nrverticies();

    vector<int> AllVertices();
    bool SearchVertex(int v);
    bool SearchEdge(int source, int target);
    bool AddVertex(int v);
    bool AddEdge(int source, int target, int cost);
    bool RemoveVertex(int v);
    bool RemoveEdge(int source, int target);
    bool ModifyCost(int source, int target, int cost);
    int GetNrVertices();
    int GetNrEdges();
    int GetInDegree(int v);
    int GetOutDegree(int v);
    vector<int> ParseVertices();
    vector<int> ParseInBoundEdge(int v);
    vector<int> ParseOutBoundEdge(int v);
    void SaveGraph(string file_name);
    map<Key, int> GetCosts();
    Graph CopyGraph();
};