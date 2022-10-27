#include "MenuBar.h"
#include "Menu.h"
#include "MenuItem.h"
#include "CreateAction.h"
#include "ExitAction.h"

int main() {
	MenuBar* MB = new MenuBar;
	Menu* File = new Menu;
	Menu* About = new Menu;

	Action* E = new ExitAction;
	MenuItem* Exit = new MenuItem(E);

	Action* T = new CreateAction;
	Action* C = new CreateAction;

	MenuItem* Text = new MenuItem(T);
	MenuItem* Cplusplus = new MenuItem(C);

	Menu* New = new Menu;

	New->add(Text); New->add(Cplusplus);
	
	File->add(New); File->add(Exit);

	MB->add(File);
	MB->add(About);
	MB->print();
	File->clicked();
	New->clicked();
	Cplusplus->clicked();
	return 0;
}