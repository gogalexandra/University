#include <iostream>
#include "Repository.h"
#include "Service.h"
#include "UI.h"
#include "Test.h"
#include <crtdbg.h>


int main() {
    /*Test t;
    t.runAll();*/


    Repository repository{};

    Dog doggy1("Husky", "Cali", 1, "https://www.huskydevanzare.ro/uploads/imagini/M3YpjDkcU7OxC5c2n7FhyJpa1DIqYbp8NT8Pbpif.jpg");
    Dog doggy2("Akita", "Bailey", 2, "https://www.animalepierdute.ro/wp-content/uploads/2019/06/akita-inu-747885_1280.jpg");
    Dog doggy3("Labrador", "Otis", 3, "https://www.animalepierdute.ro/wp-content/uploads/2020/04/95107254_530921387571902_8796591182579761152_n.jpg");
    Dog doggy4("Chow-Chow", "Sierra", 2, "https://frankfurt.apollo.olxcdn.com/v1/files/rx8530pkvnng3-RO/image;s=644x461");
    Dog doggy5("Golden Retriever", "Loki", 3, "https://media2.lajumate.ro/media/i/api_list/3/104/10449063_pui-golden-retriever-golden-retriver-mascul_2.jpg");
    Dog doggy6("Akita", "Layla", 5, "https://frankfurt.apollo.olxcdn.com/v1/files/wdggq3u4ff9x2-RO/image;s=1000x700");
    Dog doggy7("Bulldog", "Chase", 2, "https://frankfurt.apollo.olxcdn.com/v1/files/nkjk666a9xez1-RO/image;s=1000x700");
    Dog doggy8("Husky", "Phoebe", 1, "https://frankfurt.apollo.olxcdn.com/v1/files/jevwtclpgx9q2-RO/image;s=644x461");
    Dog doggy9("Husky", "Ziggy", 1, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSuyv6lVyqT7jwcO_nEcsodk7II4iYK2K5MHg&usqp=CAU");
    Dog doggy10("Pomeraniam", "Chico", 3, "https://i.pinimg.com/originals/b5/9f/87/b59f8728480231a869b262c5df1978d9.jpg");

    repository.addDog(doggy1);
    repository.addDog(doggy2);
    repository.addDog(doggy3);
    repository.addDog(doggy4);
    repository.addDog(doggy5);
    repository.addDog(doggy6);
    repository.addDog(doggy7);
    repository.addDog(doggy8);
    repository.addDog(doggy9);
    repository.addDog(doggy10);

    Service service{ repository };
    UI ui{ service };
    ui.start();

    _CrtDumpMemoryLeaks();
    return 0;

}