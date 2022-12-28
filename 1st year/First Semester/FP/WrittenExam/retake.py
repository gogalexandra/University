import unittest
#
#
def insert_nr(a, lst):
    """
    The function goes throw the elements of the list using a while loop. At the beginning initializes position with -1
    which can not be an index from the list. In the while loop, using if it is check if the element from the current
    position in the list (index) is bigger or equal to a. If it is, but another element bigger or equal to it was no
    not found (double condition: position == -1), position will get the value of index.
    :param a: is the number we want to find the position for
    :param lst:is the list with numbers in which the function searches
    :return: returns the possible position of a in the ascending ordered list lst or None if that is not found
    """
    position = -1
    index = 0
    while index <= len(lst) - 1:
        if lst[index] >= a and position == -1:
            position = index
        index += 1
    return position if position != -1 else None

lst = [1, 3, 4]
a = 2
pos = insert_nr(a, lst)
print(pos)

lst = [1]
a = 2
pos = insert_nr(a, lst)
print(pos)

class FunctionTest(unittest.TestCase):

    def insert_nr_test(self):
        lst = [1, 3, 4]
        a = 2
        pos = insert_nr(a, lst)
        self.assertEqual(1, pos)

        lst = [3, 5, 7]
        self.assertEqual(None, insert_nr(a, lst))

        lst = [2, 2, 5, 6, 9]
        self.assertEqual(1, insert_nr(a, lst))


if __name__ == '__main__':
    unittest.main()

# def x(n):
#     s = 0
#     for i in range(1, n + 1):
#         s = s + f(n+1)
#         return s
# def f(m):
#     s = 0
#     while (m != 0):
#         s = s + m // 10
#         m = m // 10
#     return s
#
# print(x(23))

