#pragma once
#include <vector>

template<typename T1, typename T2>
class List
{
public:
	List();
	~List();
	void add(T1 c, T2 v);
	void sortByFirstComponent();
	void sortBySecondComponent();
	void printReverse(std::ostream& stream);

	bool compare(T1 c, T1 c2);
	bool compare2(T2 x, T2 y);

private:
	std::vector<std::pair<T1, T2>> elems;
};

template<typename T1, typename T2>
List::List()
{
}

template<typename T1, typename T2>
List::~List()
{
}

template<typename T1, typename T2>
inline void List<T1, T2>::add(T1 c, T2 v)
{
	this->elems.push_back(std::pair<T1, T2>(c, v));
}

template<typename T1, typename T2>
inline void List<T1, T2>::sortByFirstComponent()
{
	if (this->elems.size() == 0)
		throw std::runtime_error("List is empty");
	else {
		std::sort(this->elems.begin(), this->elems.end(), compare);
	}
}

template<typename T1, typename T2>
inline void List<T1, T2>::sortBySecondComponent()
{
	if (this->elems.size() == 0)
		throw std::runtime_error("List is empty");
	else {
		std::sort(this->elems.begin(), this->elems.end(), compare2);
	}
}

template<typename T1, typename T2>
inline void List<T1, T2>::printReverse(std::ostream& stream)
{
	std::vector<T> database = this->elems;
	std::for_each(database.rbegin(), database.rend(), [&stream](auto& it) {
		stream << str(it.first.getType()) << " " << str(it.first.getNr()) << " -> " << str(it.second) <<".\n";
		});
}

template<typename T1, typename T2>
inline bool List<T1, T2>::compare(T1 c, T1 c2)
{
	return c.getNr() < c2.getNr();
}

template<typename T1, typename T2>
inline bool List<T1, T2>::compare2(T2 x, T2 y)
{
	return x < y;
}
