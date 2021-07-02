import pickle
from configparser import ConfigParser
from jproperties import Properties

from Console.UI import Ui
from Repository.Activity_repo import ActivityRepository
from Repository.BinRepo import PersonBinFileRepository, ActivityBinFileRepository
from Repository.Person_repo import PersonRepository
from Repository.Statistics import Statistics
from Repository.TextRepo import PersonTextFileRepository, ActivityTextFileRepository
from Service.Person_service import PersonService
from Service.Activity_service import ActivityService
from Entity.Activity import ActivityValidator, Activity
from Entity.Person import PersonValidator, Person
from Service.Undo_sevice import UndoService


def read_persons_text_file(file_name):
    result = []
    f = open(file_name, "r")
    line = f.readline().strip()
    while len(line) > 0:
        line = line.split(";")
        result.append(Person(int(line[0]), line[1], line[2]))
        line = f.readline().strip()
    f.close()
    return result


def read_activities_text_file(file_name):
    result = []
    f = open(file_name, "r")
    line = f.readline().strip()
    while len(line) > 0:
        line = line.split(";")
        st = line[1].split(",")
        list_id = []
        for s in st:
            list_id.append(s)
        result.append(Activity(int(line[0]), list_id, line[2], line[3], line[4]))
        line = f.readline().strip()
    f.close()
    return result


persons = read_persons_text_file('pers.txt')
activities = read_activities_text_file('act.txt')

configs = Properties()
with open('settings.properties', 'rb') as config_file:
    configs.load(config_file)

type = configs.get('repository').data

if type == 'textfiles':
    person_repo = PersonTextFileRepository(configs.get('persons').data)
    activity_repo = ActivityTextFileRepository(configs.get('activities').data)

elif type == 'binfiles':
    person_repo = PersonBinFileRepository(configs.get('persons').data)
    activity_repo = ActivityBinFileRepository(configs.get('activities').data)

elif type == 'inmemory':
    person_repo = PersonRepository(persons)
    activity_repo = ActivityRepository(activities)

person_validator = PersonValidator()
undo_service = UndoService()

activity_validator = ActivityValidator()
statistics = Statistics(activity_repo)
activity_service = ActivityService(activity_repo, person_repo, activity_validator, statistics, undo_service)
person_service = PersonService(person_repo, person_validator, undo_service, activity_service)

ready = Ui(person_service, activity_service, undo_service)
ready.start_ui()
