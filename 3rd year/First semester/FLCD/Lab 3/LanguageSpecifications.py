import re


class LanguageSpecifications:
    def __init__(self):
        self.separators = ['[', ']', '{', '}', '(', ')', ';', ' ', ':', ',', '\n', '\t', '\"']
        self.operators = ['+', '-', '*', '/', '//', '%', '<', '<=', '=', '>=', '>',
                          '==', '!=' '&&', '||', '!', '^', '+=', '-=']
        self.reservedWords = ['var', 'if', 'else', 'write', 'read', 'while', 'int']
        self.all = self.separators + self.operators + self.reservedWords

    def is_separator(self, token):
        return token in self.separators

    def is_reserved_word(self, token):
        return token in self.reservedWords

    def is_operator(self, token):
        return token in self.operators

    def is_part_of_operator(self, token):
        for operator in self.operators:
            if token in operator:
                return True
        return False

    def is_identifiers(self, token):
        return re.match(r'^[a-zA-Z]([a-zA-Z]|[0-9]|_){,7}$', token) is not None

    def is_constant(self, token):
        return re.match('(^(0|[\+\-]?[1-9][0-9]*))$|(^\"[a-z|A-Z| ]*\")$|^\'.\'$|^\".*\"$', token) is not None
