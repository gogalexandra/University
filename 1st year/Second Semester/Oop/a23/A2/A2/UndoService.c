#define _CRT_SECURE_NO_WARNINGS
#include "UndoService.h"
#include "Service.h"
#include <stddef.h>
#include <cassert>


UndoService* createUndoRepo(){
	UndoService* ur = (UndoService*)malloc(sizeof(UndoService));
	if (ur == NULL) {
		return NULL;
	}
	ur->capacity = 10;
	ur->nrElements = 0;
	ur->index = -1;
	ur->history = (MaterialRepository**)malloc(ur->capacity * sizeof(MaterialRepository*));

	for (int i = 0; i < ur->capacity; i++) {
		ur->history[i] = createRepo();

	}

	if (ur->history == NULL) {
		return NULL;
	}
	return ur;
}

void destroyUR(UndoService* ur) {
	if (ur == NULL)
		return NULL;
	for (int i = 0; i < ur->capacity; i++) {

		destroy(ur->history[i]);
	}

	free(ur->history);
	ur->history = NULL;
	free(ur);
}

void resizeUR(UndoService* ur) {
	if (ur == NULL)
		return;
	MaterialRepository** new_ur = (MaterialRepository**)malloc(2 * ur->capacity * sizeof(MaterialRepository*));
	if (new_ur == NULL)
		return;
	ur->capacity = 2 * ur->capacity;

	for (int i = 0; i < ur->capacity; i++) {
		new_ur[i] = createRepo();
	}

	for (int i = 0; i < ur->nrElements; i++) {
		for (int j = 0; j < ur->history[i]->nrMaterials; j++) {
			strcpy(new_ur[i]->all_materials[j].name, ur->history[i]->all_materials[j].name);
			strcpy(new_ur[i]->all_materials[j].supplier, ur->history[i]->all_materials[j].supplier);
			new_ur[i]->all_materials[j].quantity = ur->history[i]->all_materials[j].quantity;
			strcpy(new_ur[i]->all_materials[j].date, ur->history[i]->all_materials[j].date);
		}
		new_ur[i]->capacity = ur->history[i]->capacity;
		new_ur[i]->nrMaterials = ur->history[i]->nrMaterials;
	}
	for (int i = 0; i < ur->capacity; i++) {

		destroy(ur->history[i]);
	}

	free(ur->history);
	ur->history = new_ur;
}

void SaveData(UndoService* ur, MaterialRepository* mr) {
	/// <summary>
	/// This function saves the date from the current repo as a new element in the history list
	/// </summary>
	/// <param name="ur"></param>
	/// <param name="mr"></param>
	if (ur->nrElements == ur->capacity) {
		resize(ur);
	}
	while (ur->index + 1 < ur->nrElements)
	{
		ur->nrElements -= 1;
	}
	for (int i = 0; i < mr->nrMaterials; i++) {
		/*ur->history[ur->nrElements]->all_materials[i] = mr->all_materials[i];*/
		strcpy(ur->history[ur->nrElements]->all_materials[i].name, mr->all_materials[i].name);
		strcpy(ur->history[ur->nrElements]->all_materials[i].supplier, mr->all_materials[i].supplier);
		ur->history[ur->nrElements]->all_materials[i].quantity = mr->all_materials[i].quantity;
		strcpy(ur->history[ur->nrElements]->all_materials[i].date, mr->all_materials[i].date);
	}

	ur->history[ur->nrElements]->capacity = mr->capacity;
	ur->history[ur->nrElements]->nrMaterials = mr->nrMaterials;
	ur->index += 1;
	ur->nrElements += 1;
	
}

bool Undo(UndoService* ur, MaterialRepository* mr) {
	/// <summary>
	/// The current repo date is recieving info from the last 
	/// repo saved into the history list
	/// </summary>
	/// <param name="ur">undo servies with history list</param>
	/// <param name="mr">current repo</param>
	/// <returns>true if redo posible, false otherwise</returns>
	if (ur->index <= 0)
		return false;
	
	ur->index--;
	for (int i = 0; i < ur->history[ur->index]->nrMaterials; i++) {
		strcpy(mr->all_materials[i].name, ur->history[ur->index]->all_materials[i].name);
		strcpy(mr->all_materials[i].supplier, ur->history[ur->index]->all_materials[i].supplier);
		mr->all_materials[i].quantity = ur->history[ur->index]->all_materials[i].quantity;
		strcpy(mr->all_materials[i].date, ur->history[ur->index]->all_materials[i].date);
	}
	mr->nrMaterials = ur->history[ur->index]->nrMaterials;
	
	return true;

}

bool Redo(UndoService* ur, MaterialRepository* mr) {
	/// <summary>
	/// The curent repo to the history list, and then the current repo is recieving info from the last 
	/// repo saved into the history list
	/// </summary>
	/// <param name="ur">undo servies with history list</param>
	/// <param name="mr">current repo</param>
	/// <returns>true if redo posible, false otherwise</returns>

	if (ur->index == ur->nrElements - 1)
		return false;

	ur->index++;
	for (int i = 0; i < ur->history[ur->index]->nrMaterials; i++) {
		strcpy(mr->all_materials[i].name, ur->history[ur->index]->all_materials[i].name);
		strcpy(mr->all_materials[i].supplier, ur->history[ur->index]->all_materials[i].supplier);
		mr->all_materials[i].quantity = ur->history[ur->index]->all_materials[i].quantity;
		strcpy(mr->all_materials[i].date, ur->history[ur->index]->all_materials[i].date);
	}

	mr->nrMaterials = ur->history[ur->index]->nrMaterials;
	return true;

}

void testsUndo()
{
	UndoService* u = createUndoRepo();
	MaterialService* s = createService();

	if (u == NULL)
		assert(0);

	assert(s->repo->nrMaterials == 0);

	Material m = createMaterial("Choc", "sup1", 100, "2021.04.12");
	addMaterial(s, m);
	assert(s->repo->nrMaterials == 1);
	
	SaveData(u, s->repo);
	Undo(u, s->repo);
	assert(u->nrElements == 1);

	Redo(u, s->repo);
	assert(u->nrElements == 1);

	destroyUR(u);
	destroyService(s);
}





