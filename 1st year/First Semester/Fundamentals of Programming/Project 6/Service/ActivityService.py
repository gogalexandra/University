from Repository.Person_repo import PersonRepository
from Entity.Person import PersonValidator, Person
from Service.Activity_service import ActivityService
from Service.Undo_sevice import FunctionCall, Operation, UndoService, CascadedOperation
import copy


class PersonService:

    def __init__(self, person_repo: PersonRepository, validator: PersonValidator, undo_service: UndoService,
                 activity_service: ActivityService):
        self._repo = person_repo
        self._validator = validator
        self._undo_service = undo_service
        self._activity_service = activity_service

    @property
    def persons(self):
        return self._repo.persons

    def add(self, person: Person):
        """
        Calls the function for adding a person from repo
        :param person: the new person
        :return: raise an error if the attributes were not valid
        """
        self._validator.is_person_valid(person)
        self._repo.add_person(person)
        undo_fun = FunctionCall(self._repo.delete_person, person.person_id)
        redo_fun = FunctionCall(self._repo.add_person, person)
        self._undo_service.record(Operation(undo_fun, redo_fun))

    def delete(self, pers_id):
        """
            Calls the function for deleting a person from repo
            :param pers_id: id of the person that the user wants to delete
            :return: -
        """
        person = self._repo.delete_person(pers_id)
        undo_fun_person = FunctionCall(self._repo.add_person, person)
        redo_fun_person = FunctionCall(self._repo.delete_person, person.person_id)
        delete_person_operation = Operation(undo_fun_person, redo_fun_person)

        list_act_with_deleted_pers = self._activity_service.delete_person(pers_id)

        undo_fun = FunctionCall(self._activity_service.add_person, list_act_with_deleted_pers, pers_id)
        redo_fun = FunctionCall(self._activity_service.delete_person, pers_id)
        delete_person_from_activity_operation = Operation(undo_fun, redo_fun)

        self._undo_service.record(CascadedOperation(delete_person_operation, delete_person_from_activity_operation))

    def update(self, person: Person):
        """
            Calls the function for changing a person from repo
            :param person: the id of the person that is gonna be modify + the new attributes
            :return: raise an error if the attributes were not valid
        """
        self._validator.is_person_valid(person)
        old_person = copy.deepcopy(self._repo.is_id_unique(person.person_id))
        self._repo.update_person(person)
        undo_fun = FunctionCall(self._repo.update_person, old_person)
        redo_fun = FunctionCall(self._repo.update_person, person)
        self._undo_service.record(Operation(undo_fun, redo_fun))

    def search(self, info):
        return self._repo.search_person(info)

    def __len__(self):
        return len(self._repo)

    def get_ids(self):
        """
        Gets the list with all ids from the persons
        :return:
        """
        return self._repo.get_ids
