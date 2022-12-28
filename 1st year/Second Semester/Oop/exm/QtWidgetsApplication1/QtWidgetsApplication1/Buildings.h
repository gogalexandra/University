#pragma once
#include <string>
#include <vector>

class Buildings
{
public:
	Buildings();
	Buildings(int id, std::string description, std::string thematicArea, std::vector<std::string> location);
	~Buildings();

	const int& getId() const;
	const std::string& getDescription() const;
	const std::string& getThematicArea() const;
	const std::vector<std::string>& getLocation() const;
	

	void setId(int given_id);
	void setDescription(std::string given_description);
	void setThematicArea(std::string given_thematicArea);
	void setLocation(std::vector<std::string> given_location);
	

	friend std::istream& operator>>(std::istream& input, Buildings& b);
	friend std::ostream& operator<<(std::ostream& os, const Buildings& b);

private:
	int id;
	std::string description;
	std::string thematicArea;
	std::vector<std::string> location;

};