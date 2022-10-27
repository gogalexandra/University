
class Game:
    def __init__(self, my_board):
        self._board = my_board

    @property
    def board(self):
        """
        Returns the board
        :return:
        """
        return self._board

    def make_a_move(self, x, y):
        """
        """
        self._board.move(x, y)
