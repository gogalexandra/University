#define _CRT_SECURE_NO_WARNINGS
#include "Domain.h"
#include <string.h>
#include <stdio.h>



Material createMaterial(char* name, char* supplier, int quantity, char* date)
{
	Material m;
	strcpy(m.name, name);
	strcpy(m.supplier, supplier);
	m.quantity = quantity;
	strcpy(m.date, date);

	return m;
}

char* getName(Material* m)
{
	return m->name;
}

char* getSupplier(Material* m)
{
	return m->supplier;
}

int getQuantity(Material* m)
{
	return m->quantity;
}

char* getDate(Material* m)
{
	return m->date;
}

void toString(Material m, char str[])
{
	sprintf(str, "Material name: %s; Supplier: %s; Quantity: %d; Expiration date: %s \n", m.name, m.supplier, m.quantity, m.date);
}