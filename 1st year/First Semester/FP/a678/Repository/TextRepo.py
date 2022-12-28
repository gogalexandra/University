from Entity.Person import Person
from Repository.Person_repo import PersonRepository


class PersonTextFileRepository(PersonRepository):
    """
    Inheritance -> TextFileRepository 'IS A' Repository

    What we want:
        1. TextFileRepository behaves EXACTLY like Repository
            with one exception
        2. Ingredients are saved to/loaded from a text file
    """

    def __init__(self, file_name='persons.txt'):
        super().__init__()
        self._file_name = file_name
        self._load()

    def add(self, item):
        super().add_person(item)
        self._save()

    def delete(self, id_):
        super().delete_person(id_)
        self._save()

    def _save(self):
        f = open(self._file_name, 'wt')
        for p in self._persons:
            line = str(p.person_id) + ';' + p.name + ';' + str(p.phone_number)
            f.write(line)
            f.write('\n')
        f.close()

    def _load(self):
        """
        Load data from file
        We assume file-saved data is valid
        """
        f = open(self._file_name, 'rt')  # read text
        lines = f.readlines()
        f.close()

        for line in lines:
            line = line.split(';')
            super().add_person(Person(int(line[0]), line[1], line[2]))
