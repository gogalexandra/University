#define _CRT_SECURE_NO_WARNINGS
#include "UI.h"
#include <stdlib.h>
#include <stdio.h>


UI* createUI() {

	UI* user_interface = (UI*)malloc(sizeof(UI));
	if (user_interface == NULL) {
		return NULL;
	}
	user_interface->service = createService();
	user_interface->undoservice = createUndoRepo();
	return user_interface;
}


void addMaterialUI(UI* ui) {
	char name[50], supplier[50], date[50];
	int quantity;
	TElem m;
	printf("\n Give name: ");
	scanf("%s", name);
	printf("\n Give supplier: ");
	scanf("%s", supplier);
	printf("\n Give quantity: ");
	scanf("%d", &quantity);
	printf("\n Give date(format yyyy.mm.dd): ");
	scanf("%s", date);

	m = createMaterial(name, supplier, quantity, date);
	addMaterial(ui->service, m);
	SaveData(ui->undoservice, ui->service->repo);

}

bool updateMaterialUI(UI* ui) {
	char name[50], supplier[50], date[50];
	int quantity;
	TElem m;
	printf("\nGive name of material you want to change: ");
	scanf("%s", name);
	printf("\nChange supplier: ");
	scanf("%s", supplier);
	printf("\nChange quantity: ");
	scanf("%d", &quantity);
	printf("\nChange date(format yyyy.mm.dd): ");
	scanf("%s", date);
	m = createMaterial(name, supplier, quantity, date);
	if (updateMaterial(ui->service, m) == false) {
		printf("\nMaterial not found!");
	}
	SaveData(ui->undoservice, ui->service->repo);
	return true;
}

bool deleteMaterialUI(UI* ui) {
	char name[50];
	printf("\n Give name of material you want to delete: ");
	scanf("%s", name);
	if (deleteMaterial(ui->service, name) == false) {
		printf("\n Material not found!");
	}
	SaveData(ui->undoservice, ui->service->repo);
	return true;
}

void searchMaterialUI(UI* ui) {
	char given_string[50], today[50], buffer[10];
	TElem new_array[100];
	int dim = 0;
	printf("\n Give a string to search for:  ");
	scanf("%c", buffer);
	fgets(given_string, sizeof(given_string), stdin);
	printf("\n Give today's date:  ");
	scanf("%s", today);
	searchMaterial(ui->service, new_array, &dim, given_string, today);
	if (dim == 0) {
		printf("\nNothing to print");
	}
	else {
		print_materials(ui, new_array, dim - 1);
	}
}

void sorted_by_supplier(TElem* array, int dim) {
	TElem aux;
	for (int i = 0; i <= dim; i++) {
		for (int j = i + 1; j <= dim; j++) {
			if (strcmp(array[i].supplier, array[j].supplier) < 0) {
				aux = array[i];
				array[i] = array[j];
				array[j] = aux;
			}
		}
	}
}

void sorted_by_quantity(TElem* array, int dim) {
	TElem aux;
	for (int i = 0; i <= dim; i++) {
		for (int j = i + 1; j <= dim; j++) {
			if (array[i].quantity > array[j].quantity) {
				aux = array[i];
				array[i] = array[j];
				array[j] = aux;
			}
		}
	}
}

void sortedDescendingUI(UI* ui) {
	char given_str[50];
	TElem new_array[100];
	int dim = 0;
	printf("\n Give a string to search for:  ");
	scanf("%s", given_str);
	//fgets(given_str, sizeof(given_str), stdin);
	sortedDescending(ui->service, new_array, &dim, given_str);
	sorted_by_supplier(new_array, dim - 1);
	if (dim == 0) {
		printf("\nNothing to print");
	}
	else {
		print_materials(ui, new_array, dim - 1);
	}
}

void specificSupplier(UI* ui) {
	char given_supplier[50];
	int given_quantity = 0, dim = 0;
	TElem new_array[100];
	printf("Give a supplier to search for: ");
	scanf("%s", given_supplier);
	printf("Give a quantity: ");
	scanf("%d", &given_quantity);
	sortedSupplier(ui->service, new_array, &dim, given_supplier, given_quantity);
	sorted_by_quantity(new_array, dim - 1);
	if (dim == 0) {
		printf("\nNothing to print");
	}
	else {
		print_materials(ui, new_array, dim -1);
	}
}

void print_menu(UI* ui) {
	printf("\n 0.Exit");
	printf("\n 1.List materials ");
	printf("\n 2.Add material ");
	printf("\n 3.Update material ");
	printf("\n 4.Delete material ");
	printf("\n 5.All available materials past their expiration date ");
	printf("\n 6.All available materials sorted descending order ");
	printf("\n 7.All materials from a supplier short in stock ");
	printf("\n 8.Undo");
	printf("\n 9.Redo \n");
}

void print_materials(UI* ui, TElem* array, int dimension) {
	char string[100];
	for (int i = 0; i <= dimension; i++)
	{
		toString((array[i]), string);
		printf("\n %d. %s ", i + 1, string );
	}
}

void destroyUI(UI* ui) {
	destroyService(ui->service);
	destroyUR(ui->undoservice);
	free(ui);
}

void undoUI(UI* ui)
{
	
	if (Undo(ui->undoservice, ui->service->repo) == false) {
		printf("Nothing left to undo");
		return;
	}

}

void redoUI(UI* ui)
{

	if (Redo(ui->undoservice, ui->service->repo) == false) {
		printf("Nothing left to redo");
		return;
	}
}

void add(UI* ui) {
	TElem m1;
	m1 = createMaterial("Choco", "supplier2", 200, "2021.02.12");
	addMaterial(ui->service, m1);
	m1 = createMaterial("ChocoWhite", "supplier1", 200, "2021.05.12");
	addMaterial(ui->service, m1);
	m1 = createMaterial("Butter", "supplier3", 200, "2021.01.12");
	addMaterial(ui->service, m1);
	m1 = createMaterial("Eggs", "supplier1", 400, "2021.04.22");
	addMaterial(ui->service, m1);
	m1 = createMaterial("Milk", "supplier2", 100, "2021.05.12");
	addMaterial(ui->service, m1);
	m1 = createMaterial("Milk", "supplier1", 150, "2021.10.12");
	addMaterial(ui->service, m1);
	m1 = createMaterial("Sugar", "supplier2", 300, "2021.07.12");
	addMaterial(ui->service, m1);
	m1 = createMaterial("Brown Sugar", "supplier2", 100, "2021.05.12");
	addMaterial(ui->service, m1);
	m1 = createMaterial("Vanilla", "supplier3", 50, "2021.05.12");
	addMaterial(ui->service, m1);
	m1 = createMaterial("Flour", "supplier2", 270, "2021.02.12");
	addMaterial(ui->service, m1);
	SaveData(ui->undoservice, ui->service->repo);
}

void start(UI* ui) {
	bool done = false;
	int option;
	add(ui);
	while (done == false) {
		print_menu(ui);
		printf("\nChoose an option: \n");
		scanf("%d", &option);
		if (option == 0) {
			printf("Bye-Bye!!!");
			destroyUI(ui);
			done = true;
		}
		else {
			if (option == 1) {
				print_materials(ui, ui->service->repo->all_materials, ui->service->repo->nrMaterials - 1);
			}
			else {
			
				if (option == 2){
					addMaterialUI(ui);
				}
				else {
					if (option == 3) {
						updateMaterialUI(ui);
					}
					else {
						if (option == 4) {
							deleteMaterialUI(ui);
						}
						else {
							if (option == 5) {
								searchMaterialUI(ui);
							}
							else {
								if (option == 6) {
									sortedDescendingUI(ui);
								}
								else {
									if (option == 7) {
										specificSupplier(ui);
									}
									else {
										if (option == 8) {
											undoUI(ui);
										}
										else {
											if (option == 9) {
												redoUI(ui);
											}
											else {
												printf("Give a valid option :( \n");
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}


