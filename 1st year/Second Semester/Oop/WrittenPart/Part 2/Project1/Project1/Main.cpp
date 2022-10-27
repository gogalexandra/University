#include "BeverageMachine.h"

int main() {
    BeverageMachine bm;
    bm.prepare("Tea", 0);
    bm.prepare("Coffee", 1);
    bm.prepare("Coffee", 2);

    return 0;
}