#include "Issue.h"
#include <iostream>
#include <sstream>
#include <cstring>
#include <string>
#include <vector>

Issue::Issue()
{
}

Issue::Issue(std::string description, std::string status, std::string reporter, std::string solver)
{
	this->description = description;
	this->status = status;
	this->reporter = reporter;
	this->solver = solver;
}

Issue::~Issue()
{
}

const std::string& Issue::getDescription() const
{
	return this->description;
}

const std::string& Issue::getStatus() const
{
	return this->status;
}

const std::string& Issue::getReporter() const
{
	return this->reporter;
}

const std::string& Issue::getSolver() const
{
	return this->solver;
}

std::string Issue::toString()
{
	return "Description: " + this->description + "Reporter: " + this->reporter + "Solver:" + this->solver + "Status: " + this->status;
}

void Issue::setDescription(std::string given_description)
{
    this->description = given_description;
}

void Issue::setStatus(std::string given_status)
{
	this->status = given_status;
}

void Issue::setReporter(std::string given_reporter)
{
    this->reporter = given_reporter;
}

void Issue::setSolver(std::string given_solver)
{
	this->solver = given_solver;
}

std::istream& operator>>(std::istream& input, Issue& i) {
    std::vector<std::string> tokens;
    std::string token;

    std::string line;
    getline(input, line);
    std::stringstream tokenStream(line);

    while (getline(tokenStream, token, ',')) {
        tokens.push_back(token);
    }

    if (tokens.size() == 4) {
        i.setDescription(tokens[0]);
        i.setStatus(tokens[1]);
        i.setReporter(tokens[2]);
        i.setSolver(tokens[3]);
    }
    else if (tokens.size() == 3) {
        i.setDescription(tokens[0]);
        i.setStatus(tokens[1]);
        i.setReporter(tokens[2]);
        i.setSolver("");
    }
    return input;
}

std::ostream& operator<<(std::ostream& os, const Issue& issue)
{
    os << issue.description << "," << issue.status << "," << issue.reporter
        << "," << issue.solver << std::endl;
    return os;
}
