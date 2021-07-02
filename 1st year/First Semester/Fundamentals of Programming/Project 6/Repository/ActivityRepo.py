from _ast import operator
from datetime import datetime, date
from turtle import pd

from Entity.Activity import Activity
from Repository.Person_repo import PersonRepository


class ActivityRepositoryException(Exception):

    def __init__(self, msg):
        self._msg = msg


def check_pers_in_id_list(activity, list_with_ids: list):
    """
    The participant of an activity must be already in the list of persons and this function checks if they exist
    :param activity: is the activity for which the functions checks the
    :type list_with_ids: is the list with all the persons s ids
    """
    for idd in activity.persons_id:
        if idd not in list_with_ids:
            return False
    return True


def check_for_overlaps(activity, list_with_activities):
    """

    :param activity: is the activity we want to check time for
    :param list_with_activities: is a dictionary
    :return: False if the activities overlap
    """
    for a in list_with_activities:
        if a.date == activity.date:
            time_info_new_activity = str(activity.time).split('-')
            start_hour_new_activity = time_info_new_activity[0].strip().split(':')
            end_hour_new_activity = time_info_new_activity[1].strip().split(':')
            time_info_old_activity = str(a.time).split('-')
            start_hour_old_activity = time_info_old_activity[0].strip().split(':')
            end_hour_old_activity = time_info_old_activity[1].strip().split(':')
            if str(a.time) == str(activity.time):
                return False
            if int(start_hour_new_activity[0]) == int(end_hour_old_activity[0]) == int(
                    start_hour_old_activity[0]):
                if int(end_hour_old_activity[1]) > int(start_hour_new_activity[1]):
                    return False
            if int(start_hour_old_activity[0]) <= int(start_hour_new_activity[0]) < int(
                    end_hour_old_activity[0]):
                if int(start_hour_old_activity[1]) < int(start_hour_old_activity[1]):
                    return False
            if int(start_hour_new_activity[0]) == int(end_hour_old_activity[0]):
                if int(end_hour_old_activity[1]) > int(start_hour_new_activity[1]):
                    return False

            if int(end_hour_new_activity[0]) == int(end_hour_old_activity[0]) == int(
                    start_hour_old_activity[0]):
                if int(end_hour_old_activity[1]) > int(start_hour_new_activity[1]):
                    return False
            if int(start_hour_old_activity[0]) <= int(end_hour_new_activity[0]) < int(
                    end_hour_old_activity[0]):
                if int(end_hour_new_activity[1]) > int(start_hour_old_activity[1]):
                    return False
            if int(end_hour_new_activity[0]) == int(end_hour_old_activity[0]):
                if int(end_hour_new_activity[1]) < int(end_hour_old_activity[1]):
                    return False
    return True


class ActivityRepository:

    def __init__(self, activity_list, person_repo: PersonRepository):
        self._activities = activity_list.copy()
        self._person_repo = person_repo

    @property
    def activities(self):
        return self._activities

    def add_activity(self, activity: Activity):
        """
        Adds a new activity
        :param activity: the new activity that the user wants to add
        :return:
        """
        a = self.is_id_unique(activity.activity_id)
        list = self._person_repo.get_ids
        if a is not None:
            raise ActivityRepositoryException('!!! Duplicate id !!!')
        elif not check_pers_in_id_list(activity, list):
            raise ActivityRepositoryException('!!! Persons not in persons list !!!')
        elif not check_for_overlaps(activity, self.activities):
            raise ActivityRepositoryException('!!! Activities overlap !!!')
        else:
            self._activities.append(activity)

    def is_id_unique(self, idd):
        """
        Checks if the id is unique
        :param idd: the new id that needs to be verified
        :return: true if is unique or false if it is not
        """

        for a in self._activities:
            if a.activity_id == idd:
                return a
        return None

    def delete_activity(self, idd):
        """
        Delets a activity
        :param idd: id of the activity that needs to be deleted
        :return: a message if the activity could not be found
        """

        ac = self.is_id_unique(idd)
        if ac is not None:
            self._activities.remove(ac)
        else:
            raise ActivityRepositoryException('!!! This activity does not exist !!!')
        return ac

    def delete_person_from_activity(self, idd):

        in_this_act = []
        for a in self.activities:
            i = 0
            while i <= len(a.persons_id) - 1:
                if a.persons_id[i] == idd:
                    a.persons_id.pop(i)
                    in_this_act.append(a.activity_id)
                else:
                    i += 1
        return in_this_act

    def add_pers_to_activity(self, list, id_p):

        for a in self._activities:
            if a.activity_id in list:
                a.persons_id.append(id_p)

    def update_activity(self, activity: Activity):
        """
        Updates an activity (except the id)
        :param activity: the activity with the new params
        :return: error messages if the params are no good or the activity does not exist
        """
        ok = True
        ac = self.is_id_unique(activity.activity_id)
        list = self._person_repo.get_ids
        if ac is None:
            raise ActivityRepositoryException('!!! There is no activity with this id !!!')
        if not check_pers_in_id_list(activity, list):
            raise ActivityRepositoryException('!!! Persons not in persons list !!!')
        elif not check_for_overlaps(activity, self.activities):
            raise ActivityRepositoryException('!!! Activities overlap !!!')

        for a in self._activities:
            if a.activity_id == activity.activity_id:
                a._persons_id = activity.persons_id
                a._date = activity.date
                a._time = activity.time
                a._description = activity.description

    def search_activity(self, info):
        """
        Creates a list with all activities in which is found the input that is given by the user
        :param info: the input that the user gives
        :return:
        """
        search_list = []
        for a in self.activities:
            if info.casefold() in a.time.casefold() or info.casefold() in str(
                    a.date).casefold() or info.casefold() in a.description.casefold():
                search_list.append(a)
        return search_list

    def __len__(self):
        return len(self._activities)
