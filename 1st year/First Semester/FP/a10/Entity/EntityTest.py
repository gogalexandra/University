import unittest

from Entity.Activity import Activity
from Entity.Person import Person


class ActivityTest(unittest.TestCase):
    def test_activity(self):
        act = Activity(1, [1, 2, 3], '2020.04.22', '12:09-13:10', 'shopping')
        self.assertEqual(act.activity_id, 1)
        self.assertEqual(act.persons_id, [1, 2, 3])
        self.assertEqual(act.date, '2020.04.22')
        self.assertEqual(act.time, '12:09-13:10')
        self.assertEqual(act.description, 'shopping')


class PersonTest(unittest.TestCase):
    def test_person(self):
        p = Person(1, 'Gog Alexandra', '0756942395')
        self.assertEqual(p.person_id, 1)
        self.assertEqual(p.name, 'Gog Alexandra')
        self.assertEqual(p.phone_number, '0756942395')


if __name__ == '__main__':
    unittest.main()
