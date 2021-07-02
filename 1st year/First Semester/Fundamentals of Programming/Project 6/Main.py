from datetime import date, datetime, timedelta

import names

from Console.UI import Ui
from Entity.Person import Person
from Entity.Activity import Activity
import random

from Repository.Activity_repo import ActivityRepository
from Repository.Person_repo import PersonRepository
from Repository.Statistics import Statistics
from Service.Person_service import PersonService
from Service.Activity_service import ActivityService
from Entity.Activity import ActivityValidator
from Entity.Person import PersonValidator
from Service.Undo_sevice import UndoService


def get_random_persons_list():
    """
    Creates a list with 10 random persons
    :return: the list with the persons
    """
    data = []
    persons = []
    for x in range(1, 11):
        data.append(x)

    for i in range(1, 11):
        person_id = random.choice(data)
        data.remove(person_id)
        name = names.get_full_name()
        phone_number = '07'
        for j in range(1, 9):
            phone_number += str(random.randint(0, 9))
        persons.append(Person(person_id, name, phone_number))
    return persons


def random_date(start, end):
    """
    This function will return a random datetime between two datetime
    objects.
    """
    delta = end - start
    return start


def get_random_activities(list):
    description_var = ['shopping', 'homework', 'study for exams', 'movie', 'coffee', 'date', 'pay bills', 'visit mom',
                       'me-time', 'gym']
    dat = []
    activities = []
    hour = []
    min = []
    for x in range(1, 60):
        dat.append(x)
        min.append(x)

    for i in range(0, 24):
        hour.append(i)

    for i in range(1, 11):
        _id = random.choice(dat)
        dat.remove(_id)
        list1 = []
        for j in range(1, 3):
            list1.append(random.choice(list))

        start_dt = date.today().replace(day=1, month=11, year=2020).toordinal()
        end_dt = date.today().replace(day=31, month=12, year=2021).toordinal()
        random_day = date.fromordinal(random.randint(start_dt, end_dt))
        data = random_day
        h1 = random.choice(hour)
        m1 = random.choice(min)
        # hour.remove(h)
        min.remove(m1)
        h2 = random.randint(h1 + 1, 23)
        if h1 == h2:
            m2 = random.randint(m1 + 1, 59)
        else:
            m2 = random.choice(min)
        timee = str(h1) + ':' + str(m1) + '-' + str(h2) + ':' + str(m2)
        description = random.choice(description_var)
        activities.append(Activity(_id, list1, str(data), timee, description))

    return activities


person_validator = PersonValidator()
person_repo = PersonRepository(get_random_persons_list())
id_list = person_repo.get_ids
undo_service = UndoService()


activity_validator = ActivityValidator()
activity_repo = ActivityRepository(get_random_activities(id_list), person_repo)
statistics = Statistics(activity_repo)
activity_service = ActivityService(activity_repo, activity_validator, statistics, undo_service)
person_service = PersonService(person_repo, person_validator, undo_service, activity_service)

ready = Ui(person_service, activity_service, undo_service)
ready.start_ui()
