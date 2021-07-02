
#include <stdio.h>
#include <string.h>

// the function declased in the en_modulSumaNumere.asm file
int double_nr(char a);

int main()
{
     
   int num;
   int a;
   FILE *fptr;
   char c[1000];

   if ((fptr = fopen("C:\\Users\\gogan\\Documents\\ASM\\Homework\\lab 12 asm+c\\ex19\\numere.txt","r")) == NULL){
       printf("Error! opening file");

       // Program exits if the file pointer returns NULL.
       exit(1);
   }
   
    fscanf(fptr, "%s", c);
    
    for(int i = strlen(c)-1; i >= 0; --i) {
        a = double_nr(c[i]);
        printf("%d, ", a);
  }
    
    fclose(fptr);

    return 0;

}
