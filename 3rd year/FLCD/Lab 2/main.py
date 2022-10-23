from SymbolTable import SymbolTable

if __name__ == '__main__':
    symbolTable = SymbolTable()
    symbolTable.add('lol', 4)
    symbolTable.add('sdfgdh', 5)
    symbolTable.add('idk', 1)
    assert ('idk' in symbolTable)
    for x in symbolTable.hashTable.items:
        if x is not None:
            print(x.value)
    symbolTable.delete('idk')
    symbolTable.add('scdc', 3)
    print(symbolTable.hashTable.items)
    for x in symbolTable.hashTable.items:
        if x is not None:
            print(x.value)
