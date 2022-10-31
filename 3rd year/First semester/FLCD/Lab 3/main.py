from LanguageSpecifications import LanguageSpecifications
from Scanner import Scanner
from SymbolTable import SymbolTable

if __name__ == '__main__':
    # symbolTable = SymbolTable()
    # symbolTable.add('lol')
    # symbolTable.add('sdfgdh')
    # symbolTable.add('idk')
    # symbolTable.add('E')
    # symbolTable.add('O')
    # print(str(symbolTable))
    # print(str(symbolTable.findPosition('O')))
    # l = LanguageSpecifications()
    # scan = Scanner("p1.txt")
    # tokens = scan.split_by_separator("var a:int")
    # print(str(scan.split_line("if(a>b){")))
    filename = input("File name: ")
    scan = Scanner(filename)
    scan.scan()
