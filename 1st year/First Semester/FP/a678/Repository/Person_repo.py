from Entity.Person import Person


class PersonRepositoryException(Exception):

    def __init__(self, msg):
        self._msg = msg


class PersonRepository:

    def __init__(self):
        self._persons = []

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

    def add_person(self, person: Person):
        """
        Adds a new person to the list
        :param person: the object that the user wants to add to the list
        :return: raise an error if the parameters of the person are incorrect or adds the person to the list (list of dictionaries)
        """
        p = self.is_id_unique(person.person_id)
        if p is not None:
            raise PersonRepositoryException('!!! Duplicate id !!!')
        else:
            self._persons.append(person)

    def is_id_unique(self, idd):
        """
        Checks if the id is unique (there can not be two identical ids)
        :param idd: the new id
        :return: true if the id is unique / false if it is not
        """
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
        p = self.is_id_unique(idd)
        if p is not None:
            self._persons.remove(p)
        else:
            raise PersonRepositoryException('!!! This person does not exist !!!')
        return p

    def update_person(self, person: Person):
        """
        Change the attributes of the person (except the id)
        :param person: all the attributes to update a person(id: to know of which person to change the attributes,
                        the rest are new attributes)
        :return: an exception if the person could not be fine
        """
        p = self.is_id_unique(person.person_id)
        if p is not None:
            p._name = person.name
            p._phone_number = person.phone_number
        else:
            raise PersonRepositoryException('!!! There is no person with this id !!!')

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


