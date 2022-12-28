"""Provide the _dbm module as a dbm submodule."""

from src.domain.entity import student, studentException


class school:

    def __init__(self, students):
        self._students = students.copy()
        self._history = []

    @property
    def get_all(self):
        return self._students

    def add_student(self, student):
        """
        Adds a new student
        :param student: the new student
        :return: An exception if its the case
        """
        self._history.append(self._students.copy())
        if len(self._students) == 0:
            self._students.append(student)
        else:
            for s in self._students:
                if s.id == student.id:
                    raise studentException('Two students can not have the same id ')

            self._students.append(student)

    def __len__(self):
        return len(self._students)

    def filter_by_group(self, group):
        """
        Delets the student from other group than the given one
        :param group: the group that should be kept
        :return: -
        """

        self._history.append(self._students.copy())
        s = 0
        ok = False
        while s <= len(self._students) - 1:
            if self._students[s].group != group:
                self._students.pop(s)
                ok = True
            else:
                s += 1
        if not ok:
            print('No students in that group')

    def undo(self):
        """
        Returns to the last object before modifing it
        :return: the last object from the list
        """
        if len(self._history) > 0:
            self._students = self._history[-1].copy()
            self._history.pop(-1)
        else:
            raise AssertionError('Cannot undo anymore')


def test_school():
    s = school()
    assert len(s) == 0

    s.add_student(student(11, 'Gog Alexandra', 13))
    assert len(s) == 1

    try:
        s.add_student(student(1, 'Gog Alexandra', 13))
    except studentException:
        assert True
    assert len(s) == 1

    s.add_student(student(22, 'Campean Catalin', 1))
    assert len(s) == 2
    s.add_student(student(3, 'Chetan Anca', 3))
    assert len(s) == 3

    s.display_student()
    s.filter_by_group(9)
    s.display_student()

#test_school()
