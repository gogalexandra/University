from HashTable import HashTable


class SymbolTable:
    def __init__(self):
        self.hashTable = HashTable()

    def add(self, key, value):
        self.hashTable.insert(key, value)

    def delete(self, key):
        return self.hashTable.remove(key)

    def find(self, key):
        return self.hashTable.find(key)

    def __contains__(self, key):
        return key in self.hashTable