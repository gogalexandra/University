from Entity.Person import Person
from DataStructure.Iterable import DataStructure


class PersonRepositoryException(Exception):

    def __init__(self, msg):
        self._msg = msg


class PersonRepository:

    def __init__(self, person_list):
        self._persons = DataStructure()
        for p in person_list:
            self._persons.add(p)
            self.save()

    @property
    def persons(self):
        return self._persons

    @property
    def get_ids(self) -> list:
        """
        Creates a list with every id of the persons to use for the activities
        :return: a list with all ids
        """
        id_list = []
        for p in self.persons:
            id_list.append(p.person_id)
        return id_list

    def save(self):
        pass

    def add_person(self, person: Person):
        """
        Adds a new person to the list
        :param person: the object that the user wants to add to the list
        :return: raise an error if the parameters of the person are incorrect or adds the person to the list (list of dictionaries)
        """
        p = self.does_id_exist(person.person_id)
        if p is not None:
            raise PersonRepositoryException('!!! Duplicate id !!!')

        self._persons.add(person)
        self.save()

    def does_id_exist(self, idd):
        for p in self._persons:
            if p.person_id == idd:
                return p
        return None

    def delete_person(self, idd):
        """
        Deletes a person from the list
        :param idd: id of the person the user wants to delete
        :return: raise exception if the person does not exist(could not find the id) or deletes the person
        """
        person_object = self.does_id_exist(idd)
        if person_object is None:
            raise PersonRepositoryException("Person does not exist!!!!")

        p = 0
        while p <= len(self._persons) - 1:
            if self.persons[p].person_id == idd:
                del self._persons[p]
                self.save()
            else:
                p += 1

        return person_object


    def update_person(self, person: Person):
        """
        Change the attributes of the person (except the id)
        :param person: all the attributes to update a person(id: to know of which person to change the attributes,
                        the rest are new attributes)
        :return: an exception if the person could not be fine
        """
        person_object = self.does_id_exist(person.person_id)
        if person_object is None:
            raise PersonRepositoryException("Person does not exist!!!!")

        p = 0
        while p <= len(self._persons) - 1:
            if self.persons[p].person_id == person.person_id:
                self.persons[p] = person
                self.save()
            p += 1

    def search_person(self, info):
        """
        Creates a list with all persons in which is found the input that the user writes
        :param info: the input from the user
        :return: the list
        """
        search_list = []
        for p in self.persons:
            if info.casefold() in p.name.casefold() or info.casefold() in p.phone_number.casefold():
                search_list.append(p)
        return search_list

    def __len__(self):
        return len(self._persons)
