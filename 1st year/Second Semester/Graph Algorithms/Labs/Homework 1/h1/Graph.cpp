#include "Graph.h"
#include <iterator>
#include <fstream>


Graph::Graph()
{
	this->DIn = {};
	this->DOut = {};
	this->DCost = {};
	this->nrVertices = 0;
}

Graph::Graph(const Graph& g)
{
	this->DIn = g.DIn;
	this->DOut = g.DOut;
	this->DCost = g.DCost;
	this->nrVertices = g.nrVertices;
}

map<int, vector<int>> Graph::din()
{
	return this->DIn;
}

map<int, vector<int>> Graph::dout()
{
	return this->DOut;
}

map<Key, int> Graph::dcost()
{
	return this->DCost;
}

int Graph::nrverticies()
{
	return this->nrVertices;
}

vector<int> Graph::AllVertices()
{
	vector<int> all_vertices;
	map<int, vector<int>>::iterator it = this->DIn.begin();

	while (it != this->DIn.end()) {
		int v = it->first;
		all_vertices.push_back(v);
		it++;
	}
	return all_vertices;
}

bool Graph::SearchVertex(int v)
{
	vector<int> all_vertices = this->AllVertices();
	vector<int>::iterator it;
	it = find(all_vertices.begin(), all_vertices.end(), v);
	if (it != all_vertices.end())
		return true;
	else
		return false;
}

bool Graph::SearchEdge(int source, int target)
{
	if (SearchVertex(source) == false && SearchVertex(target) == false)
		return false;
	else {
		vector<int> inbound = ParseOutBoundEdge(source);
		vector<int>::iterator it;
		it = find(inbound.begin(), inbound.end(), target);
		if (it != inbound.end())
			return true;
		else
			return false;
	}
}

bool Graph::AddVertex(int v)
{
	if (SearchVertex(v) )
		return false;
	else {
		vector<int> targets = {};
		this->DIn.insert(pair<int, vector<int>>(v, targets));
		this->DOut.insert(pair<int, vector<int>>(v, targets));
		return true;
	}
}

bool Graph::AddEdge(int source, int target, int cost)
{
	if (SearchEdge(source, target))
		return false;
	else {
		this->DIn[target].push_back(source);
		this->DOut[source].push_back(target);
		//Key/*(source, target);*/
		this->DCost[Key(source, target)] = cost;
		return true;
	}
}

bool Graph::RemoveVertex(int v)
{
	if (not SearchVertex(v))
		return false;
	else {

		vector<int> inbound = ParseInBoundEdge(v);
		vector<int>::iterator it = inbound.begin();
		for (auto& it : inbound) {
			this->DOut[it].erase(remove(this->DOut[it].begin(), this->DOut[it].end(), v), this->DOut[it].end());
		}

		vector<int> outbound = ParseOutBoundEdge(v);
		vector<int>::iterator it2 = outbound.begin();
		for (auto& it2 : outbound) {
			this->DIn[it2].erase(remove(this->DIn[it2].begin(), this->DIn[it2].end(), v), this->DIn[it2].end());
			Key edge(it2, v);
			this->DCost.erase(edge);
		}

		this->DIn.erase(v);
		this->DOut.erase(v);

		return true;
	}
}

bool Graph::RemoveEdge(int source, int target)
{
	if (not SearchEdge(source, target))
		return false;
	else {
		this->DIn[target].erase(remove(this->DIn[target].begin(), this->DIn[target].end(), source), this->DIn[target].end());
	

		this->DOut[source].erase(remove(this->DOut[source].begin(), this->DOut[source].end(), target), this->DOut[source].end());
		
		this->DCost.erase(Key(source, target));

		return true;
	}
}

bool Graph::ModifyCost(int source, int target, int cost)
{
	if (not SearchEdge(source, target))
		return false;
	else {
		Key edge(source, target);
		this->DCost[edge] = cost;
		return true;
	}
}

int Graph::GetNrVertices()
{
	return ParseVertices().size();
}

int Graph::GetNrEdges()
{
	return this->DCost.size();
}

int Graph::GetInDegree(int v)
{
	if (not SearchVertex(v))
		return -1;
	else
		return ParseInBoundEdge(v).size();
}

int Graph::GetOutDegree(int v)
{
	if (not SearchVertex(v))
		return -1;
	else
		return ParseOutBoundEdge(v).size();
}


vector<int> Graph::ParseVertices()
{
	vector<int> all_vertices;
	map<int, vector<int>>::iterator it = this->DIn.begin();

	while (it != this->DIn.end()) {
		int v = it->first;
		all_vertices.push_back(v);
		it++;
	}
	return all_vertices;
}


vector<int> Graph::ParseInBoundEdge(int v)
{
	if (not SearchVertex(v))
		return { -1 };
	else
		if (this->DIn[v].size() == 0)
			return {};
		else
			return this->DIn[v];
}


vector<int> Graph::ParseOutBoundEdge(int v)
{
	if (not SearchVertex(v))
		return { -1 };
	else
		if (this->DOut[v].size() == 0)
			return {};
		else
			return this->DOut[v];
}

void Graph::SaveGraph(string file_name)
{
	ofstream output(file_name);
	vector<int> all_vertices = AllVertices();
	vector<int> ::iterator it = all_vertices.begin();
	for (auto& it : all_vertices) {
		if (this->DOut[it].size() == 0) {
			if (this->DIn[it].size() == 0) {
				output << to_string(it) + "\n";
			}
		}
		else {
			vector<int> outbound = ParseOutBoundEdge(it);
			for (auto& it2 : outbound)
			{
				output << to_string(it) + " " + to_string(it2) + " " + to_string(this->DCost[Key(it, it2)]) + "\n";
			}
		}
	}

	output.close();
}

map<Key, int> Graph::GetCosts()
{
	return this->DCost;
}

Graph Graph::CopyGraph()
{
	Graph copy_graph;
	copy_graph.DIn = this->DIn;
	copy_graph.DOut = this->DOut;
	copy_graph.DCost = this->DCost;
	return copy_graph;
}
