#pragma once
#include <string>

class Message
{
public:
	Message();
	Message(std::string m);
	~Message();
	std::string getMessage();

private:
	std::string text;
};

Message::Message()
{
}

inline Message::Message(std::string m)
{
	this->text = m;
}

Message::~Message()
{
}

inline std::string Message::getMessage()
{
	return this->text;
}
