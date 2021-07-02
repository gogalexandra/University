


#include <stdio.h>

// the function declased in the en_modulSumaNumere.asm file
int base2(int a);

int main()
{
    // declare variables
    int list[] = {12, 101, 5, 1, 9};
    char * p;
    
    
     for(int i = 0; i < 5; ++i) {
        printf("%x, ", list[i]);
  }

    for(int i = 0; i < 5; ++i) {
         base2(list[i]);
  }
    
    return 0;
}
