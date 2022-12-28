#pragma once
#include "Domain.h"
#include <stdbool.h>
#include <string.h>

typedef Material TElem;
typedef struct
{

	TElem* all_materials;
	int nrMaterials;
	int capacity;

}MaterialRepository;

MaterialRepository* createRepo();
void resize(MaterialRepository*);
void destroy(MaterialRepository* mr);
void add_material(MaterialRepository* mr , TElem m);
bool delete_material(MaterialRepository* mr, char* name);
bool update_material(MaterialRepository* mr, TElem m);
bool find(MaterialRepository* mr, TElem m);
void search_material(MaterialRepository* mr, TElem* new_array, int* dim, char string[], char string2[]);
void sorted_descending(MaterialRepository* mr, TElem* new_array, int* dim, char string[]);
void sorted_supplier(MaterialRepository* mr, TElem* new_array, int* dim, char string[], int quantity);
bool pass_exp_date(char* string1, char* string2);
int return_position(MaterialRepository* mr, char* name);
void testsRepo();
