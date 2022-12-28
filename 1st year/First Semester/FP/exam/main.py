from Board.BoardGame import Board
from Console.UI import UI
from Game.GameModule import Game


board = Board()
game = Game(board)
ui = UI(game)
ui.start()
