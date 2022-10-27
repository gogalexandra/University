#pragma once
#include "Message.h"

class MessageWithAttachment : public Message
{
public:
	MessageWithAttachment ();
	MessageWithAttachment(Message* msg, std::string s);
	~MessageWithAttachment ();
	std::string getMessage();

private:
	Message* m;
	std::string attachmentLocation;
};

MessageWithAttachment ::MessageWithAttachment ()
{
}

inline MessageWithAttachment::MessageWithAttachment(Message* msg, std::string s)
{
	this->m = msg;
	this->attachmentLocation = s;
}

MessageWithAttachment ::~MessageWithAttachment ()
{
}

inline std::string MessageWithAttachment::getMessage()
{
	return m->getMessage() + this->attachmentLocation;
}
