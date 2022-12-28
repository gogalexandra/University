#define _CRT_SECURE_NO_WARNINGS
#include "Repository.h"
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <string.h>
#include <cassert>


MaterialRepository* createRepo() {
	/// <summary>
	/// This function allocates the necesary space for the dynamic array and for every element from the array
	/// </summary>
	/// <returns> the dynamic array</returns>
	MaterialRepository* mr = (MaterialRepository*)malloc(sizeof(MaterialRepository));
	if (mr == NULL) {
		return NULL;
	}
	mr->capacity = 50;
	mr->nrMaterials = 0;

	mr->all_materials = (TElem*)malloc(mr->capacity * sizeof(TElem));
	if (mr->all_materials == NULL) {
		return NULL;
	}
	return mr;
}

void resize(MaterialRepository* mr) {
	/// This function has the pourpose of making space for the elements that need to be added to the dynamic array.
	/// For that, the capacity is being double up, the elements are copied into a new array that has been declared(using malloc also)
	///	and replace the one with the old capacity. 
	/// <param name="mr">is the dynamic array in which are stored the entitites</param>
	if (mr == NULL)
		return;
	TElem* new_mr = (TElem*)malloc(2 * mr->capacity * sizeof(TElem));
	if (new_mr == NULL)
		return;
	mr->capacity = 2 * mr->capacity;
	for (int i = 0; i <= mr->nrMaterials; i++) {
		strcpy(new_mr[i].name, mr->all_materials[i].name);
		strcpy(new_mr[i].supplier, mr->all_materials[i].supplier);
		new_mr[i].quantity =  mr->all_materials[i].quantity;
		strcpy(new_mr[i].date, mr->all_materials[i].date);
		/*ew_mr[i] = mr->all_materials[i];*/
	}
	free(mr->all_materials);
	mr->all_materials = new_mr;
}

void destroy(MaterialRepository* mr) {
	/// This function is meant to free the memory blocks allocated for the da
	/// at first are the elements from the da are freed, then the da is freed 
	/// <param name="mr">is the dynamic array in which are stored the entitites</param>
	if (mr == NULL)
		return NULL;
	free(mr->all_materials);
	mr->all_materials = NULL;
	free(mr);
}

void add_material(MaterialRepository* mr, TElem m)
{
	/// <summary>
	/// this function adds a element to the da: first checks if the capacity allows to add a new element(all allocated spaces are occpuied)
	/// if not resize function is called, else the element is added (added as a new element or increasing the quantity if it is already in the list)
	/// </summary>
	/// <param name="mr">mr is the da</param>
	/// <param name="m">m is a the new elememy</param>
	int index;
	if (mr == NULL)
		return;
	if (mr->all_materials == NULL)
		return;
	if (mr->nrMaterials == mr->capacity) {
		resize(mr);
	}
	if (find(mr, m) == false) {
		mr->all_materials[mr->nrMaterials] = m;
		mr->nrMaterials++;
		
		
	}
	else {
		index = return_position(mr, m.name);
		mr->all_materials[index].quantity += m.quantity;
	}
	
}

bool delete_material(MaterialRepository* mr, char* name){
	/// <summary>
	/// Delets an element from the da(if that elemen exist: this is checked using the return position function
	/// </summary>
	/// <param name="mr"> the da</param>
	/// <param name="name"> the element search be the name</param>
	/// <returns>true if the element is deleted, false otherwise</returns>
	int index = return_position(mr, name);
	if (index == -1) {
		return false;
	}
	else {
		for (int i = index; i < mr->nrMaterials - 1; i++) {
			mr->all_materials[i] = mr->all_materials[i + 1];
		}
		mr->nrMaterials--;
	}
	return true;

}

bool update_material(MaterialRepository* mr, TElem m) {
	/// <summary>
	/// This function is looking throw the dynamic array for the element given by the user and modifies it with the new data.
	/// If the element with that name does not exist nothing is changed
	/// </summary>
	/// <param name="mr"> is the dynamic array where the elements are stored</param>
	/// <param name="m">m is type TElem and has the name of the elem the user wants to modify and the rest are new data</param>
	/// <returns>true is the elem has been modified, false otherwise</returns>
	int index = return_position(mr, m.name);
	if (index == -1) {
		return false;
	}
	else {
		mr->all_materials[index] = m;
		return true;
	}
}

int return_position(MaterialRepository* mr, char* name) {
	/// <summary>
	/// this function goes throw of elements and compers every name with the given one
	/// </summary>
	/// <param name="mr">the dynamic array where elements are stored</param>
	/// <param name="name">the name of the element this function searches for</param>
	/// <returns>the position of the elem if found, -1 otherwise</returns>

	for (int i = 0; i <= mr->nrMaterials; i++) {
		if (strcmp(mr->all_materials[i].name, name) == 0) {
			return i;
		}
	}
	return -1;
}

bool find(MaterialRepository* mr, TElem m) {
	/// <summary>
	/// this function goes throw of elements and compers every elem with the given one
	/// </summary>
	/// <param name="mr">the dynamic array where elements are stored</param>
	/// <param name="name">the given elem</param>
	/// <returns>true if elem is found, false otherwise</returns>

	for (int i = 0; i <= mr->nrMaterials; i++) {
		if (strcmp(mr->all_materials[i].name, m.name) == 0 &&
			strcmp(mr->all_materials[i].supplier, m.supplier) == 0 &&
			strcmp(mr->all_materials[i].date, m.date) == 0) {
			return true;
		}
	}
	return false;
}

bool pass_exp_date(char* other_day, char* today) {
	/// <summary>
	/// this function compers 2 strings both representaing a date and checks if string1 is smaller than string2
	/// </summary>
	/// <param name="other_day">string1 as date 1</param>
	/// <param name="today">string2 as date 2</param>
	/// <returns>true if str1< str2, false otherwise</returns>

	bool ok = true;
	int i = 0;
	while (i < strlen(today)) {
		if (other_day[i] > today[i]) {
			return false;
		}
		else {
			if (other_day[i] < today[i]) {
				return true;
			}
		}
		i++;
	}
}

void search_material(MaterialRepository* mr, TElem* new_array, int* dim, char string[], char today[])
{
	/// <summary>
	/// This function searches for entities that contain a specific string. If that entity is found, is added to a new array, dimension 
	/// of that array is increasing, and goes to the next elem from the dynamic array
	/// </summary>
	/// <param name="mr">the dynamic array</param>
	/// <param name="new_array">the new array containg the elements that have that string</param>
	/// <param name="dim">dimension of the new array</param>
	/// <param name="string">the substring that elements must contain to be added in the new array</param>
	/// <param name="today">elements should have a field smaller than today</param>
	char str[50];

	if (string[0] != '\n') {
		string[strlen(string) - 1] = '\0';
	}
	for (int i = 0; i <= mr->nrMaterials; i++) {

		if (pass_exp_date(mr->all_materials[i].date, today) == true) {
			if (string[0] == '\n') {
				new_array[*dim] = mr->all_materials[i];
				*dim += 1;
			}
			else {
				
				*str = strstr(mr->all_materials[i].name, string);
				if (str) {

					new_array[*dim] = mr->all_materials[i];
					*dim += 1;
				}
				else {
					*str = strstr(mr->all_materials[i].supplier, string);
					if (str) {

						new_array[*dim] = mr->all_materials[i];
						*dim += 1;
					}
				}
			}
			
		}
	}
}

void sorted_descending(MaterialRepository* mr, TElem* new_array, int* dim, char string[])
{
	/// <summary>
	/// This function sorts the elements from the array in descending order
	/// </summary>
	/// <param name="mr"></param>
	/// <param name="new_array"></param>
	/// <param name="dim"></param>
	/// <param name="string"></param>
	char str[50];
	for (int i = 0; i <= mr->nrMaterials; i++) {
		*str = strstr(mr->all_materials[i].name, string);
		if (str) {

			new_array[*dim] = mr->all_materials[i];
			*dim += 1;
		}
		else {
			*str = strstr(mr->all_materials[i].supplier, string);
			if (str) {

				new_array[*dim] = mr->all_materials[i];
				*dim += 1;
			}
		}
	}
}

void sorted_supplier(MaterialRepository* mr, TElem* new_array, int* dim, char string[], int quantity) {
	/// <summary>
	/// This function adds to a new array all elemets that havea specific supplier and have a quantity smaller than the one give by the user
	/// </summary>
	/// <param name="mr">The dynamic array with al entities</param>
	/// <param name="new_array">Is the new array with only the elements that respect the critiries</param>
	/// <param name="dim">The dimension of the new string</param>
	/// <param name="string">is the substring that must be in the element</param>
	/// <param name="quantity">the int the function comperes the quantity to </param>

	for (int i = 0; i < mr->nrMaterials; i++) {
		if (strcmp(mr->all_materials[i].supplier, string) == 0) {
			if (mr->all_materials[i].quantity < quantity) {
				new_array[*dim] = mr->all_materials[i];
				*dim += 1;
			}
		}
	}
}

void testsRepo()
{
	MaterialRepository* r = createRepo();
	if (r == NULL)
		assert(0);

	assert(r->nrMaterials == 0);

	Material m = createMaterial("Choc", "sup1", 100, "2021.04.12");
	add_material(r, m);
	assert(r->nrMaterials == 1);

	Material m1 = createMaterial("Sugar", "sup2", 200, "2021.05.12");
	add_material(r, m1);
	assert(r->nrMaterials == 2);

	// capacity must double
	Material m2 = createMaterial("Milk", "sup2", 200, "2021.05.12");
	add_material(r, m2);
	assert(r->nrMaterials == 3);
	
	delete_material(r, "Choc");
	assert(r->nrMaterials == 2);

	Material m3 = createMaterial("Milk", "sup4", 250, "2021.05.12");
	update_material(r, m3);
	assert(r->all_materials[1].quantity == 250);

	destroy(r);
}

