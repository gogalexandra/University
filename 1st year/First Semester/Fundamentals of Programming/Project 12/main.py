from jproperties import Properties
from GameBoard.Board import Board
from Console.UI import UI
from Game.GameModule import Game

configs = Properties()
with open('settings.properties', 'rb') as config_file:
    configs.load(config_file)

dim = int(configs.get('dimension').data)
nr_of_apples = int(configs.get('number_of_apples').data)

board = Board(dim, nr_of_apples)
game = Game(board)
ui = UI(game)
ui.start()
