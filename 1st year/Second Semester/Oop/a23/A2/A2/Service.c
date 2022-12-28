#define _CRT_SECURE_NO_WARNINGS
#include "Service.h"
#include <string.h>
#include <cassert>

MaterialService* createService() {
	MaterialService* ms = (MaterialService*)malloc(sizeof(MaterialService));
	if (ms == NULL) {
		return NULL;
	}
	ms->repo = createRepo();
	return ms;
}

void addMaterial(MaterialService* ms, TElem m) {
	/// <summary>
	/// Calls function from the repo
	/// </summary>
	/// <param name="ms"></param>
	/// <param name="m"></param>
	add_material(ms->repo, m);
}

bool deleteMaterial(MaterialService* ms, char* name) {
	/// <summary>
	/// Calls function from the repo
	/// </summary>
	/// <param name="ms"></param>
	/// <param name="name"></param>
	/// <returns></returns>
	if (delete_material(ms->repo, name) == false) {
		return false;
	}
	else{
		return true;
	}
}

bool updateMaterial(MaterialService* ms, TElem m) {
	/// <summary>
	/// Calls function from the repo
	/// </summary>
	/// <param name="ms"></param>
	/// <param name="name"></param>
	/// <returns></returns>
	if (update_material(ms->repo, m) == false) {
		return false;
	}
	else{
		return true;
	}
}

void searchMaterial(MaterialService* ms, TElem* new_array, int* dim, char string[], char string2[]){
	/// <summary>
	/// Calls function from the repo
	/// </summary>
	/// <param name="ms"></param>
	/// <param name="name"></param>
	/// <returns></returns>
	search_material(ms->repo, new_array, dim, string, string2);
}

void sortedDescending(MaterialService* ms, TElem* new_array, int* dim, char string[]) {
	/// <summary>
	/// Calls function from the repo
	/// </summary>
	/// <param name="ms"></param>
	/// <param name="name"></param>
	/// <returns></returns>
	sorted_descending(ms->repo, new_array, dim, string);
}

void sortedSupplier(MaterialService* ms, TElem* new_array, int* dim, char string[], int quantity) {
	/// <summary>
	/// Calls function from the repo
	/// </summary>
	/// <param name="ms"></param>
	/// <param name="name"></param>
	/// <returns></returns>
	sorted_supplier(ms->repo, new_array, dim, string, quantity);
}

void destroyService(MaterialService* ms) {
	destroy(ms->repo);
	free(ms);
	ms->repo = NULL;
	ms = NULL;
}

void testsService() {
	MaterialService* s = createService();
	if (s == NULL)
		assert(0);

	assert(s->repo->nrMaterials == 0);

	Material m = createMaterial("Choc", "sup1", 100, "2021.04.12");
	addMaterial(s, m);
	assert(s->repo->nrMaterials == 1);

	Material m1 = createMaterial("Sugar", "sup2", 200, "2021.05.12");
	addMaterial(s, m1);
	assert(s->repo->nrMaterials == 2);

	Material m2 = createMaterial("Milk", "sup2", 200, "2021.05.12");
	addMaterial(s, m2);
	assert(s->repo->nrMaterials == 3);

	destroyService(s);
}
