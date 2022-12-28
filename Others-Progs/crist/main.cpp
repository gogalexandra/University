#include <iostream>

int main() {
    int[] array = {12, 23, -23, -12, 13};
    int[] positiveNr = new int[array.Length()];
    int index1 = 0;
    int[] negativeNr = new int[array.Length()];
    int index2 = 0;
    for (int i = 0; i < array.Length(); i++){
        if (array[i] > 0 ){
            positiveNr[index1] = array[i];
            index1 ++;
        }
        else{
            negativeNr[index2] = array[i];
            index2 ++;
        }
    }
    return 0;
}
