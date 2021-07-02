#
# Write the implementation for A3 in this file
#

#
# domain section is here (domain = numbers, tran
import self

from src.services.service import school
from src.domain.entity import studentException, student
import names
from random import randint
import random


def print_menu():
    """
    Prints the possible options
    :return: options printed on the output
    """
    print('1.Display all students')
    print('2.Add a student')
    print('3.Filter by group')
    print('4.Undo')
    print('0.Exit')


def get_random_students_list():
    """
    Creates a list with 10 random studens
    :return: the list with the students
    """
    data = []
    students = []
    for x in range(1, 400):
        data.append(x)

    for i in range(1, 11):
        id_ = random.choice(data)
        data.remove(id_)
        name = names.get_full_name()
        group = randint(1, 20)
        students.append(student(id_, name, group))
    return students

class ui:

    def __init__(self):
        self._school = school(get_random_students_list())

    def display_students(self):
        """
        Prints all the students
        :return: list printed on the output
        """
        if len(self._school.get_all) == 0:
            print('No students')
        else:
            for s in self._school.get_all:
                print(str(s))

    def start(self):
        done = False
        while not done:
            print_menu()
            x = int(input('Enter a command: '))

            if x == 0:
                done = True
                print('Bye!')
            elif x == 1:
                self.display_students()

            elif x == 2:
                idd = int(input('Give student id: '))
                name = input('Give student name: ')
                tokens = name.split(' ', 1)
                if len(tokens) > 1:
                    firstname = tokens[0].strip()
                    lastname = tokens[1].strip()
                    name = firstname + ' ' + lastname
                else:
                    name = None
                group = int(input('Give student group: '))
                try:
                    self._school.add_student(student(idd, name, group))
                except studentException as st:
                    print(str(st))

            elif x == 3:
                group = int(input('Give the group you wanna keep: '))
                self._school.filter_by_group(group)

            elif x == 4:
                try:
                    self._school.undo()
                except studentException as st:
                    print(str(st))

            else:
                print('Wrong comand!')


school_ui = ui()
school_ui.start()
