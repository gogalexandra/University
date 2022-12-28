from datetime import date

from Repository.BinRepo import ActivityBinFileRepository
from Repository.TextRepo import ActivityTextFileRepository


class Statistics:

    def __init__(self, activity_repo):
        self._activity_repo = activity_repo

    def init_activities_with_a_person(self, p, day):
        """
        Creates a list of dictionaries that stores the activity id with the date in which person p participates
        :param p: the id that the user gives to search the activities with that person
        :param day: is today(looking for upcoming activities)
        :return: the list
        """
        statistic_list = []
        for a in self._activity_repo.activities:
            if str(a.date) >= str(day):
                if p in a.persons_id:
                    statistic_list.append({'id': a.activity_id, 'date': a.date})

        statistic_list.sort(key=lambda i: i['date'], reverse=False)
        return statistic_list

    def init_upcoming_activities(self):
        """
        Creates a list of dictionaries that stores the upcoming dates + the activities s ids in that day + the number
        of activities based on which the list id sorted
        :return: the list with upcoming activities
        """
        statistic_list = []
        today = str(date.today())
        for a in self._activity_repo.activities:
            ok = False
            if str(a.date) >= str(today):
                for x in statistic_list:
                    if x['date'] == str(a.date):
                        x['list_id'].append(a.activity_id)
                        x['number_of_activities'] += 1
                        ok = True

                if not ok:
                    list_with_act = [a.activity_id]
                    statistic_list.append({'date': a.date, 'list_id': list_with_act, 'number_of_activities': 0})

        statistic_list.sort(key=lambda i: i['number_of_activities'], reverse=True)
        return statistic_list

    def init_activities_from_a_date(self, d):
        """
        Creates a list (a list of dictionaries ) with all activities from a specific date + the time frame based on
        which the list is sorted
        :param d: is the day to look after in the list with activities
        :return: the list
        """
        statistics_list = []
        for a in self._activity_repo.activities:
            if str(a.date) == str(d):
                statistics_list.append({'id': a.activity_id, 'time': a.time})
        statistics_list.sort(key=lambda i: i['time'], reverse=False)
        return statistics_list
