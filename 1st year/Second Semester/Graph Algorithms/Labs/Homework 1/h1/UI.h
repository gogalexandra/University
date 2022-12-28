#pragma once
#include "Graph.h"

class UI
{
public:
	
	Graph g;
	Graph g_copy;
	void start();

private:
	static void printMenu();
	void printGraph();
	void resetGraph();
	void readGraphData(string file_name);
	void readGraphData2(string file_name);
	void GenerateRandom(int i, int j);
};