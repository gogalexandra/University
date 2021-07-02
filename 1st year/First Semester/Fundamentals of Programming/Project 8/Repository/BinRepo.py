import pickle

from Repository.Activity_repo import ActivityRepository
from Repository.Person_repo import PersonRepository


class PersonBinFileRepository(PersonRepository):
    """
    Inheritance -> TextFileRepository 'IS A' Repository

    What we want:
        1. TextFileRepository behaves EXACTLY like Repository
            with one exception
        2. Ingredients are saved to/loaded from a text file
    """

    def __init__(self, file_name='persons.bin'):
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
    #
    # def search_person(self, info):
    #     super().search_person(info)
    #     self._save()

    def save(self):
        f = open(self._file_name, "wb")
        pickle.dump(self._persons, f)
        f.close()

    def _load(self):
        """
        Load data from file
        We assume file-saved data is valid
        """
        f = open(self._file_name, "rb")
        self._persons = pickle.load(f)
        f.close()


class ActivityBinFileRepository(ActivityRepository):
    """
    Inheritance -> TextFileRepository 'IS A' Repository

    What we want:
        1. TextFileRepository behaves EXACTLY like Repository
            with one exception
        2. Ingredients are saved to/loaded from a text file
    """

    def __init__(self, file_name='activities.bin'):
        super().__init__([])
        self._file_name = file_name
        self._load()

    # @property
    # def activities(self):
    #     for a in super(ActivityBinFileRepository, self).activities():
    #         print(a)
    #     return super().activities
    #
    # def __len__(self):
    #     return super().__len__()
    #
    # def add_activity(self, item):
    #     super().add_activity(item)
    #     self._save()
    #
    # def delete_activity(self, id_):
    #     super().delete_activity(id_)
    #     self._save()
    #
    # def delete_person_from_activity(self, id_):
    #     deleted_list = super().delete_person_from_activity(id_)
    #     self._save()
    #     return deleted_list
    #
    # def update_activity(self, item):
    #     super().update_activity(item)
    #     self._save()
    #
    # def search_activity(self, info):
    #     super().search_activity(info)
    #     self._save()

    def save(self):

        f = open(self._file_name, "wb")
        pickle.dump(self._activities, f)
        f.close()

    def _load(self):
        """
        Load data from file
        We assume file-saved data is valid
        """
        f = open(self._file_name, "rb")
        self._activities = pickle.load(f)
        f.close()

