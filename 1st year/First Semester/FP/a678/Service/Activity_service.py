import copy

from Repository.Activity_repo import ActivityRepository
from Entity.Activity import ActivityValidator, Activity
from Repository.Statistics import Statistics
from Service.Undo_sevice import FunctionCall, Operation, UndoService


class ActivityService:

    def __init__(self, activity_repo: ActivityRepository, validator: ActivityValidator, statistics: Statistics,
                 undo_service: UndoService):
        self._undo_service = undo_service
        self._repo = activity_repo
        self._validator = validator
        self._statistics = statistics

    @property
    def activities(self):
        return self._repo._activities

    def add(self, activity: Activity):
        """
        Call the add function from repo
        :param activity: the new activity
        :return: possible error from the validator
        """
        self._validator.is_activity_valid(activity)
        undo_fun = FunctionCall(self._repo.delete_activity, activity.activity_id)
        redo_fun = FunctionCall(self._repo.add_activity, activity)
        self._undo_service.record(Operation(undo_fun, redo_fun))
        self._repo.add_activity(activity)

    def delete(self, act_id):
        """
            Call the delete function from repo
            :param act_id: the id of the activity that needs to be deleted
        """
        activity = self._repo.delete_activity(act_id)
        undo_fun = FunctionCall(self._repo.add_activity, activity)
        redo_fun = FunctionCall(self._repo.delete_activity, activity.activity_id)
        self._undo_service.record(Operation(undo_fun, redo_fun))

    def delete_person(self, pers_id):
        """
        If a person is deleted from the persons list, is also deleted from each activity in which he participates
        :param pers_id: id of the person
        :return: -
        """
        return self._repo.delete_person_from_activity(pers_id)

    def add_person(self, list, pers_id):
        self._repo.add_pers_to_activity(list, pers_id)

    def update(self, activity: Activity):
        """
            Call the update function from repo
            :param activity: the new params of the activity
        """
        self._validator.is_activity_valid(activity)
        old_activity = copy.deepcopy(self._repo.is_id_unique(activity.activity_id))
        self._repo.update_activity(activity)
        undo_fun = FunctionCall(self._repo.update_activity, old_activity)
        redo_fun = FunctionCall(self._repo.update_activity, activity)
        self._undo_service.record(Operation(undo_fun, redo_fun))

    def search(self, info):
        return self._repo.search_activity(info)

    def statistic_3(self, p, day):
        return self._statistics.init_activities_with_a_person(p, day)

    def statistic_1(self, d):
        return self._statistics.init_activities_from_a_date(d)

    def statistic_2(self):
        return self._statistics.init_upcoming_activities()

    def __len__(self):
        return len(self._repo)
