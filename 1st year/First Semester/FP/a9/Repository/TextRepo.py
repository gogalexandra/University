from Entity.Activity import Activity
from Entity.Person import Person
from Repository.Person_repo import PersonRepository
from Repository.Activity_repo import ActivityRepository


class PersonTextFileRepository(PersonRepository):
    """
    Inheritance -> TextFileRepository 'IS A' Repository

    What we want:
        1. TextFileRepository behaves EXACTLY like Repository
            with one exception
        2. Ingredients are saved to/loaded from a text file
    """

    def __init__(self, file_name='persons.txt'):
        super().__init__([])
        self._file_name = file_name
        self._load()

    # @property
    # def persons(self):
    #     return super().persons
    #
    # def __len__(self):
    #     return super().__len__()
    #
    # @property
    # def get_ids(self) -> list:
    #     return super().get_ids
    #
    # def add_person(self, item):
    #     super().add_person(item)
    #     self._save()
    #
    # def delete_person(self, id_):
    #     pers_object = super().delete_person(id_)
    #     self._save()
    #     return pers_object
    #
    # def update_update(self, item):
    #     super().update_person(item)
    #     self._save()

    # def search_person(self, info):
    #     super().search_person(info)
    #
    # def _save(self):
    #     f = open(self._file_name, 'wt')
    #     for p in self._persons:
    #         line = str(p.person_id) + ';' + p.name + ';' + str(p.phone_number)
    #         f.write(line)
    #         f.write('\n')
    #     f.close()

    def save(self):
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
            line = line.strip()
            line = [part.strip() for part in line.split(';') if part != '']
            super().add_person(Person(int((line[0])), line[1], line[2]))


class ActivityTextFileRepository(ActivityRepository):
    """
    Inheritance -> TextFileRepository 'IS A' Repository

    What we want:
        1. TextFileRepository behaves EXACTLY like Repository
            with one exception
        2. Ingredients are saved to/loaded from a text file
    """

    def __init__(self, file_name='activities.txt'):
        super().__init__([])
        self._file_name = file_name
        self._load()

    # @property
    # def activities(self):
    #     return super().activities
    #
    # def __len__(self):
    #     return super().__len__()
    #
    # def add_activity(self, item):
    #     super().add_activity(item)
    #     self._save()

    # def delete_activity(self, id_):
    #     super().delete_activity(id_)
    #     self._save()

    def save(self):
        f = open(self._file_name, 'wt')
        for a in self._activities:
            line = str(a.activity_id) + ';' + str(a.persons_id) + ';' + a.date + ';' + a.time + ';' + a.description
            f.write(line)
            f.write('\n')
        f.close()

    #
    # def delete_person_from_activity(self, id_):
    #     deleted_list = super().delete_person_from_activity(id_)
    #     self._save()
    #     return deleted_list
    #
    # def update_activity(self, item):
    #     super().update_activity(item)
    #     self._save()

    # def search_activity(self, info):
    #     super().search_activity(info)
    #
    # def _save(self):
    #     f = open(self._file_name, 'wt')
    #     for a in self._activities:
    #         line = str(a.activity_id) + ';' + str(a.persons_id) + ';' + a.date + ';' + a.time + ';' + a.description
    #         f.write(line)
    #         f.write('\n')
    #     f.close()

    def _load(self):
        """
        Load data from file
        We assume file-saved data is valid
        """
        f = open(self._file_name, 'rt')  # read text
        lines = f.readlines()
        f.close()

        # for line in lines:
        #     line = line.split(';')
        #     # st = line[1].split(',')
        #     # list_id = []
        #     # for s in st:
        #     #     list_id.append(s)
        #     super().add_activity(Activity(int(line[0]), line[1], line[2], line[3], line[4]))

        for line in lines:
            line = line.strip()
            line = [part.strip() for part in line.split(';') if part != '']
            super().add_activity(Activity(int(line[0]), line[1], line[2], line[3], line[4]))
