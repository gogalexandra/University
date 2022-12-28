import unittest

from Repository.Person_repo import PersonRepository, PersonRepositoryException
from Repository.Activity_repo import ActivityRepository, ActivityRepositoryException

from Entity.Activity import Activity
from Entity.Person import Person


class ActivityTest(unittest.TestCase):

    def test_activity_repo_add(self):

        persons_id = [1, 2, 3, 4]
        activities = [Activity(1, [1, 2, 3], '2020.04.25', '15:10-16:45', 'plimbare')]

        repo = ActivityRepository(activities)
        self.assertEqual(len(repo), 1)

        repo.add_activity(Activity(11, [1, 2, 3], '2020.04.22', '09:20-15:30', 'shopping'), persons_id)
        self.assertEqual(len(repo), 2)

        try:
            repo.add_activity(Activity(16, [1, 4, 3], '2020.04.22', '09:50-10:30', 'shopping'), persons_id)
        except ActivityRepositoryException:
            self.assertEqual(len(repo), 2)

    def test_activity_repo_delete(self):

        activities = [Activity(1, [1, 2, 3], '2020.04.22', '15:10-16:45', 'plimbare')]
        repo = ActivityRepository(activities)
        self.assertEqual(len(repo), 1)

        repo.delete_activity(1)
        self.assertEqual(len(repo), 0)

    def test_activity_repo_update(self):

        persons_id = [1, 2, 3, 4]
        activities = [Activity(1, [1, 2, 3], '2020.04.22', '15:10-16:45', 'cry')]
        repo = ActivityRepository(activities)
        act = Activity(1, [1, 2, 3], '2020.04.25', '15:34-16:45', 'cry')
        repo.update_activity(act, persons_id)

        self.assertEqual(repo.activities[0].date, '2020.04.25')
        self.assertEqual(repo.activities[0].time, '15:34-16:45')


class PersonTest(unittest.TestCase):

    def test_person_repo_add(self):
        l = [Person(1, 'Gog Alexandra', '0745292396')]
        repo = PersonRepository(l)
        self.assertEqual(len(repo), 1)
        person = Person(2, 'Alexandru Muresan', '0788534768')

        repo.add_person(person)
        self.assertEqual(len(repo), 2)

        try:
            repo.add_person(Person(2, 'Gog Alex', '0745292396'))
        except PersonRepositoryException as st:
            self.assertEqual(len(repo), 2)

    def test_person_repo_delete(self):
        l = [Person(1, 'Gog Alexandra', '0745292396')]
        repo = PersonRepository(l)
        self.assertEqual(len(repo), 1)

        repo.delete_person(1)
        self.assertEqual(len(repo), 0)

    def test_person_repo_update(self):
        l = [Person(1, 'Gog Alexandra', '0745292396')]
        repo = PersonRepository(l)
        self.assertEqual(len(repo), 1)

        p = Person(1, 'Campean Catalin', '0742267812')
        repo.update_person(p)

        self.assertEqual(repo.persons[0].name, 'Campean Catalin')
        self.assertEqual(repo.persons[0].phone_number, '0742267812')


if __name__ == '__main__':
    unittest.main()
