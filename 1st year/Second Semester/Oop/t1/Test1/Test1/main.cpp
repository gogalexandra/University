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

    Gene gene1("E_Coli_K12", "hokC", "TTAATGAAGCATAAGCTTGATTTC");
    Gene gene2("E_Coli_ETEC", "yqgE", "GTGACAGCGCCCTTCTTTCCACG");
    Gene gene3("Mouse", "Col2a1", "TTAAAGCATGGCTCTGTG");
    Gene gene4("M_tuberculosis", "ppiA", "TTAACTCTGTG");
    Gene gene5("E_Coli_K12 ", "yqgE", "ATCATCATTG");

    repository.addGene(gene1);
    repository.addGene(gene2);
    repository.addGene(gene3);
    repository.addGene(gene4);
    repository.addGene(gene5);

    Service service{ repository };
    UI ui{ service };
    ui.start();

    _CrtDumpMemoryLeaks();
    return 0;

}