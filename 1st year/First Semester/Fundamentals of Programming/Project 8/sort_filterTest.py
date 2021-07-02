import unittest

from sort import gnomeSort
from filter import filter

class SortTest(unittest.TestCase):
    def test_gnomesort(self):
        list = [4, 1, 6, 0]
        list = gnomeSort(list, lambda a, b: a > b)
        self.assertEqual(list, [0, 1, 4, 6])


class FilterTest(unittest.TestCase):
    def test_filter(self):
        nr_list = [1, 4, 9, 12]
        filter_list = []
        for nr in filter(nr_list, lambda p: p % 2 == 0):
            filter_list.append(nr)

        self.assertEqual(filter_list, [4, 12])


if __name__ == '__main__':
    unittest.main()
