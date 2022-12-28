import unittest

from Board.BoardGame import Board
from GameModule import Game
from StrategyModule.Strategy import Strategy


class GameTest(unittest.TestCase):

    def test_human_move(self):
        board = Board()
        strategy = Strategy()
        game = Game(strategy, board)
        game.human_move(2, "R")
        x = board.get(5, 2)
        self.assertEqual(x, "R")


if __name__ == '__main__':
    unittest.main()
