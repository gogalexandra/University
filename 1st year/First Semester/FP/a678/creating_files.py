
from Entity.Person import Person, PersonValidator
from Repository.Person_repo import PersonRepository
from Repository.TextRepo import PersonTextFileRepository

repo = PersonTextFileRepository("persons.txt")
print(len(repo))

#
# def write_text_file(repo):
#     f = open('persons.txt', 'wt')
#     for id_ in range(1, 11):
#         p = repo[id_]
#         line = str(p.person_id) + ';' + p.name + ';' + p.phone_number
#         f.write(line)
#         f.write('\n')
#     f.close()
#
#
# def read_text_file():
#     f = open('persons.txt', 'rt')  # read text
#     lines = f.readlines()
#     f.close()
#     repo = PersonRepository()
#     for line in lines:
#         line = line.split(';')
#         repo.add_person(Person(int(line[0]), line[1], int(line[2])))
#     return repo


# person_validator = PersonValidator()
# person_repo = PersonRepository()
# undo_service = UndoService()
#
# activity_validator = ActivityValidator()
# activity_repo = ActivityRepository(person_repo)
# statistics = Statistics(activity_repo)
# activity_service = ActivityService(activity_repo, activity_validator, statistics, undo_service)
# person_service = PersonService(person_repo, person_validator, undo_service, activity_service)
#
# ready = Ui(person_service, activity_service, undo_service)
# ready.start_ui()
