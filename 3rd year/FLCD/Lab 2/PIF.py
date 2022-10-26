class PIF:
    def __init__(self):
        self.content = []

    def add(self, code, id):
        self.content.append((code, id))

    def __str__(self):
        return str(self.content)