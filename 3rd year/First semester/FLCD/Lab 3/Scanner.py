import re

from LanguageSpecifications import *
from ProgramInternalForm import ProgramInternalForm
from SymbolTable import SymbolTable


def get_quotes_index(list):
    return [index for index, elem in enumerate(list) if elem == "\""]


class Scanner:
    def __init__(self, programFile):
        self.languageSpecification = LanguageSpecifications()
        self.ST = SymbolTable()
        self.PIF = ProgramInternalForm()
        self.programFile = programFile
        self.STFile = "st.out"
        self.PIFFile = "pif.out"

    def split_by_separator(self, line):
        tokens = []
        separator_index = 0
        current_index = 0
        for char in line:
            if self.languageSpecification.is_separator(char):
                if separator_index != current_index:
                    tokens.append(line[separator_index:current_index])
                tokens.append(char)
                separator_index = current_index+1
            current_index += 1
        if separator_index != len(line):
            tokens.append(line[separator_index:current_index])

        quote_indexes = get_quotes_index(tokens)
        while len(quote_indexes) >= 2:
            first_pos = quote_indexes[0]
            second_pos = quote_indexes[1]

            tokens = tokens[:first_pos + 1] + ['"' + ''.join(tokens[first_pos + 1: second_pos]) + '"'] + tokens[second_pos:]

            quote_indexes.pop(0)
            quote_indexes.pop(0)
            for i in range(len(quote_indexes)):
                quote_indexes[i] -= len(tokens[first_pos + 1: second_pos]) - 1

        return tokens

    def split_by_operator(self, line):
        index = 0
        tokens = []
        token = ''
        while index in range(len(line)):
            if not self.languageSpecification.is_part_of_operator(line[index]):
                token += line[index]
            else:
                operator = ''
                if index + 1 < len(line):
                    if self.languageSpecification.is_operator(line[index] + line[index + 1]):
                        operator = line[index: index + 2]
                        index += 1
                    else:
                        operator = line[index]
                else:
                    operator = line[index]

                if token:
                    tokens.append(token)
                tokens.append(operator)
                token = ''
            index += 1
        if token:
            tokens.append(token)

        return tokens

    def split_line(self, line):
        separated_by_separators = self.split_by_separator(line)
        tokens = []
        for token in separated_by_separators:
            splited_by_operators = self.split_by_operator(token)
            if len(splited_by_operators) == 1:
                tokens.append(splited_by_operators[0])
            else:
                for piece in splited_by_operators:
                    tokens.append(piece)
        return tokens

    def scan(self):
        with open(self.programFile, 'r') as file:
            lineNo = 0
            tokenNo = 0
            for line in file:
                lineNo += 1
                tokens = self.split_line(line)
                for token in tokens:
                    if self.languageSpecification.is_operator(token) or self.languageSpecification.is_separator(token) \
                            or self.languageSpecification.is_reserved_word(token):
                        if token == ' ':
                            self.PIF.add("" "", 0, '')
                        elif token == '\n':
                            self.PIF.add("\ n", 0, '')
                        elif token == '\t':
                            self.PIF.add("\ t", 0, '')
                        else:
                            self.PIF.add(token, 0, '')

                    else:
                        if self.languageSpecification.is_constant(token):
                            if self.ST.findPosition(token) == -1:
                                self.ST.add(token)
                            self.PIF.add("const", self.ST.symbolTable.hash(token), self.ST.findPosition(token))
                        elif self.languageSpecification.is_identifiers(token):
                            if self.ST.findPosition(token) == -1:
                                self.ST.add(token)
                            self.PIF.add("id", self.ST.symbolTable.hash(token), self.ST.findPosition(token))
                        else:
                            print("Error on line ", lineNo, " at token index: ", tokenNo)
                            return
                    tokenNo += len(token)

        self.write_in_file()

        return


    def write_in_file(self):
        f = open("pif.out", "w")
        f.write("{:<10} {:<10} {:<10} \n".format("Code", "Position 1", "Position 2"))
        for elem in self.PIF.content:
            f.write("{:<10} {:<10} {:<10} \n".format(elem[0], elem[1], elem[2]))

        f.close()
        f = open("st.out", "w")
        f.write("{:<10} {:<10} \n".format("Position", "Associated values"))
        index = 0
        while index < self.ST.symbolTable.capacity:
            node = self.ST.symbolTable.items[index]
            if node is None:
                f.write("{:<10} {:<10} \n".format(index, "None"))
            else:
                prev = self.ST.symbolTable.items[index]
                list_of_values = []
                while node is not None:
                    list_of_values.append(node.key)
                    prev = node
                    node = node.next
                f.write("{:<10} {:<10} \n".format(index, str(list_of_values)))
            index += 1

