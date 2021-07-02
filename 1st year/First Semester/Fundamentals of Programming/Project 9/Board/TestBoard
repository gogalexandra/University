import unittest
from Board.BoardGame import Board


class BoardTest(unittest.TestCase):

    def test_move(self):
        board = Board()
        board.move(1, "Y")
        x = board.get(5, 1)
        self.assertEqual(x, "Y")
        try:
            board.move(1, "Y")
        except Exception as ex:
            print('\n' + str(ex))


if __name__ == '__main__':
    unittest.main()
