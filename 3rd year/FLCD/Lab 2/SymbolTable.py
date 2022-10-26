from HashTable import HashTable


class SymbolTable:
    def __init__(self):
        self.symbolTable = HashTable()

    def add(self, key, value):
        self.symbolTable.insert(key, value)

    def delete(self, key):
        return self.symbolTable.remove(key)

    def find(self, key):
        return self.symbolTable.find(key)

    def __contains__(self, key):
        return key in self.symbolTable

    def __str__(self):
        print("Symbol table:")
        for x in self.symbolTable.items:
            if x is not None:
                print(x.key, x.value)
