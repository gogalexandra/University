"""Provide the _gdbm module as a dbm submodule.
"""


class studentException(Exception):
    def __init__(self, msg):
        self._msg = msg


class student:
    def __init__(self, id_, name, group):
        if name is None:
            raise studentException('Name should contain a surname and a firstname')
        if not isinstance(name, str):
            raise studentException('Name should be a string')
        if id_ < 1:
            raise studentException('Id should be greater than 1')
        if group > 20:
            raise studentException('There are no more groups than 20')
        self._id = id_
        self._name = name
        self._group = group

    @property
    def id(self):
        return self._id

    @property
    def name(self):
        return self._name

    @property
    def group(self):
        return self._group

    def __str__(self):
        return str('id: ' + str(self._id) + ' Name: ' + self._name + ' Group: ' + str(self._group))


def test_student():
    try:
        s = student(3, 'Pop Ionescu', 1)
    except studentException as st:
        print(str(st))

# test_student()
