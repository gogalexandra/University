
from datetime import date

from Entity.Person import Person
from Entity.Activity import Activity
from Service.Person_service import PersonService
from Service.Activity_service import ActivityService
from Service.Undo_sevice import UndoService


def print_start():
    print('\n')
    print('1: Add')
    print('2: Remove')
    print('3: Update')
    print('4: Display')
    print('5: Search')
    print('6: Statistics')
    print('7: Undo')
    print('8: Redo')
    print('0: Exit')


class Ui:

    def __init__(self, person_service: PersonService, activity_service: ActivityService, undo_service: UndoService):
        self._person_service = person_service
        self._activity_service = activity_service
        self._undo_service = undo_service

    def display_all_activities(self):
        if len(self._activity_service) == 0:
            print('There are no activities')
        else:
            for a in self._activity_service.activities:
                print(str(a))

    def display_all_persons(self):
        if len(self._person_service) == 0:
            print('There are no persons')
        else:
            for p in self._person_service.persons:
                print(str(p))

    def displays(self, list):
        if len(list) == 0:
            print('Nothing to display')
        for a in list:
            print(str(a))

    def display_st_1(self, list):
        if len(list) == 0:
            print('Nothing to display')
        for a in list:
            print('Activity id: ' + str(a['id']) + 'at: '.rjust(14) + a['time'])

    def display_st_2(self, list):
        if len(list) == 0:
            print('Nothing to display')
        for a in list:
            print('In day: ' + str(a['date']) + ' are activities: ' + str(a['list_id']))

    def display_st_3(self, list):
        if len(list) == 0:
            print('Nothing to display')
        for a in list:
            print('Activity id: ' + str(a['id']) + 'in day: '.rjust(14) + a['date'])

    def start_ui(self):

        done = False
        while not done:

            print_start()
            x = input('Enter a command: ')

            try:
                if x == '1':

                    print('1.1: Add person')
                    print('1.2: Add activity')
                    y = input('Enter a command: ')

                    if y == '1.1':

                        id_here = int((input('Give id: ')))
                        name_here = input('Give name: ')
                        phone_here = input('Give phone number: ')
                        self._person_service.add(Person(id_here, name_here, phone_here))
                        print('Added successfully')

                    elif y == '1.2':

                        id_here = int(input('Give id: '))
                        id_list = []
                        nr = int(input('Number of persons: '))
                        for a in range(1, nr + 1):
                            idd = int(input('Give id of persons: '))
                            id_list.append(idd)
                        date_here = input('Give date(year-mm-dd): ')
                        time_here = input('Give time(hh:mm-hh:mm): ')
                        description_here = input('Give description: ')
                        list_with_ids = self._person_service.get_ids()
                        self._activity_service.add(Activity(id_here, id_list, date_here, time_here, description_here))
                        print('Added successfully')

                    else:
                        print('\n Wrong command')

                elif x == '2':

                    print('2.1: Remove person')
                    print('2.2: Remove activity')
                    y = input('Enter a command: ')

                    if y == '2.1':
                        id_here = int(input('The id: '))
                        self._person_service.delete(id_here)
                        print('Deleted successfully')

                    elif y == '2.2':
                        id_here = int(input('The id: '))
                        self._activity_service.delete(id_here)
                        print('Deleted successfully')

                    else:
                        print('\n Wrong command')

                elif x == '3':

                    print('3.1: Update person')
                    print('3.2: Update actvity')
                    y = input('Enter a command: ')

                    if y == '3.1':
                        id_here = int(input('The id: '))
                        name_here = input('New Name: ')
                        phone_here = input('New phone number: ')
                        self._person_service.update(Person(id_here, name_here, phone_here))
                        print('Modified successfully')

                    elif y == '3.2':
                        id_here = int(input('The id: '))
                        id_list = []
                        nr = int(input('Number of persons: '))
                        for a in range(1, nr + 1):
                            idd = int(input('Give id of persons: '))
                            id_list.append(idd)
                        date_here = input('Give new date: ')
                        time_here = input('Give new time(hh:mm-hh:mm): ')
                        description_here = input('Give new descriptions: ')
                        self._activity_service.update(
                            Activity(id_here, id_list, date_here, time_here, description_here))
                        print('Modified successfully')

                    else:
                        print('\n Wrong command')

                elif x == '4':
                    print('4.1: Display persons')
                    print('4.2: Display activities')
                    y = input('Enter a command: ')

                    if y == '4.1':
                        self.display_all_persons()

                    elif y == '4.2':
                        self.display_all_activities()

                    else:
                        print('\n Wrong command')

                elif x == '5':
                    print('5.1: Search in persons')
                    print('5.2: Search in activities')
                    y = input('Enter a command: ')

                    if y == '5.2':
                        info = input('Search for ? ')
                        self.displays(self._activity_service.search(info))

                    elif y == '5.1':
                        info = input('Search for ? ')
                        self.displays(self._person_service.search(info))

                    else:
                        print('\n Wrong command')

                elif x == '6':
                    print('6.1: Activities for a given date')
                    print('6.2: Busiest days')
                    print('6.3: Activities with a given person')
                    y = input('Enter a command: ')

                    if y == '6.1':
                        d = input('Give a date(yyyy-mm-dd): ')
                        list_with_act = self._activity_service.statistic_1(d)
                        if len(list_with_act) != 0:
                            print('Activities from date ' + d + ' are: ')
                        self.display_st_1(list_with_act)

                    elif y == '6.2':
                        list_with_act = self._activity_service.statistic_2()
                        if len(list_with_act) != 0:
                            print('Busiest days in descending order with the number of activities from that day are: ')
                        self.display_st_2(list_with_act)

                    elif y == '6.3':
                        p = input('Give an id: ')
                        today = str(date.today())
                        print('Activities with person ' + p + ' are: ')
                        list_with_act = self._activity_service.statistic_3(p, today)
                        self.display_st_3(list_with_act)

                    else:
                        print('\n Wrong command')

                elif x == '7':
                    self._undo_service.undo()

                elif x == '8':
                    self._undo_service.redo()

                elif x == '0':
                    done = True
                    print('Bye!')

                else:
                    print('Wrong command')

            except Exception as ex:
                print('\n' + str(ex))
