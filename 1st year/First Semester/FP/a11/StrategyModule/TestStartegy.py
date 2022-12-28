import unittest

from Board.BoardGame import Board
from StrategyModule.Strategy import ImprovedMoveStrategy


class StrategyTest(unittest.TestCase):

    def test_next_move(self):
        board = Board()
        random_strategy = ImprovedMoveStrategy()
        params = random_strategy.next_move(board, "R")
        self.assertEqual(params[2], "R")


if __name__ == '__main__':
    unittest.main()