#include "Contact.h"
#include "Message.h"
#include "MessageWithAttachment.h"
#include "NormalMessage.h"
#include "ShortMessage.h"
#include "Person.h"
#include "Group.h"

int main() {

	Group* roommates = new Group;
	Contact* c1 = new Person("John");
	Contact* c2 = new Person("Alice");
	roommates->addContact(c1); roommates->addContact(c2);

	Group* universityfriends = new Group;
	Contact* c3 = new Person("Lilian");
	Contact* c4 = new Person("Michael");
	universityfriends->addContact(c1); universityfriends->addContact(c2); universityfriends->addContact(c3); universityfriends->addContact(c4);

	Group* parents = new Group;
	Contact* c5 = new Person("Mom");
	Contact* c6 = new Person("Dad");
	parents->addContact(c5); parents->addContact(c6);

	Contact* c7 = new Person("Charles");
	parents->addContact(c7);
	Message m("Home safe");
	parents->sendMessage(m);



	return 0;
}