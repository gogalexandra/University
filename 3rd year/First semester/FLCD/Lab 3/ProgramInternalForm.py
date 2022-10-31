class ProgramInternalForm:
    def __init__(self):
        self.content = []

    def add(self, code, pos1, pos2):
        # code: "id" if identifier/"const" if constant/ char itself if operator, separator or reserved word
        # pos1: position in hashtable or 0 if not identifier or constant
        # pos2: position in linked list or non
        self.content.append((code, pos1, pos2))
