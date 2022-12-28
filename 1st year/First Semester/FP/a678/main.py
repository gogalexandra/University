from datetime import date, datetime, timedelta

import names

from Console.UI import Ui
from Entity.Person import Person
from Entity.Activity import Activity
import random

from Repository.Activity_repo import ActivityRepository
from Repository.Person_repo import PersonRepository
from Repository.Statistics import Statistics
from Repository.TextRepo import PersonTextFileRepository
from Service.Person_service import PersonService
from Service.Activity_service import ActivityService
from Entity.Activity import ActivityValidator
from Entity.Person import PersonValidator
from Service.Undo_sevice import UndoService

person_validator = PersonValidator()
person_repo = PersonTextFileRepository()
id_list = person_repo.get_ids
undo_service = UndoService()


activity_validator = ActivityValidator()
activity_repo = ActivityRepository(person_repo)
statistics = Statistics(activity_repo)
activity_service = ActivityService(activity_repo, activity_validator, statistics, undo_service)
person_service = PersonService(person_repo, person_validator, undo_service, activity_service)

ready = Ui(person_service, activity_service, undo_service)
ready.start_ui()
