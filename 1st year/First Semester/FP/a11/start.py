from Board.BoardGame import Board
from Console.UI import UI
from Game.GameModule import Game
from StrategyModule.Strategy import ImprovedMoveStrategy

random_strategy = ImprovedMoveStrategy()
board = Board()
game = Game(random_strategy, board)
ui = UI(random_strategy, game)
ui.start()
