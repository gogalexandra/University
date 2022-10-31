from HashTable import HashTable


class SymbolTable:
    def __init__(self):
        self.symbolTable = HashTable()

    def add(self, key):
        self.symbolTable.insert(key)

    def findPosition(self, key):
        return self.symbolTable.calculate_position(key)

