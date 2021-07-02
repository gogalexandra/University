import copy


class Game:
    def __init__(self, strategy, board):
        self._board = board
        self._strategy = strategy

    @property
    def board(self):
        return self._board

    def human_move(self, y, human_color):
        """
        Place the move
        :param y: the column where the user wants to make the move
        :param human_color: color which the human plays with
        :return: true if the game is won with the last move, otherwise false
        """
        params = self._board.move(y, human_color)
        return self.check_if_game_is_won(params[0], params[1], params[2])

    def computer_move(self, computer_color):
        """
        Place the move
        :param computer_color: color which the computer plays with
        :return: true if the game is won with the last move, otherwise false
        """
        params = self._strategy.next_move(self._board, computer_color)
        return self.check_if_game_is_won(params[0], params[1], params[2])

    def check_if_game_is_won(self, x, y, symbol):
        return self.check_row(x, symbol) or self.check_column(y, symbol) or self.check_diagonal_1(x, y, symbol) or \
               self.check_diagonal_2(x, y, symbol)

    def check_row(self, x, symbol):
        """
        :param x: is the row where the program searched for a 4 line(the row of the last move)
        :param symbol: the color of the player
        :return: True if a 4line is found else false
        """
        count = 1
        for y in range(self.board.col_count - 1):
            if self._board.get(x, y) == symbol == self.board.get(x, y + 1):
                count += 1
                if count == 4:
                    return True
            else:
                count = 1
        return False

    def check_column(self, y, symbol):
        """
        :param y: is the column where the program searched for a 4 line(the column of the last move)
        :param symbol: the color of the player
        :return: True if a 4line is found else false
        """
        count = 1
        for x in range(self.board.row_count - 1):
            if self.board.get(x, y) == symbol == self.board.get(x + 1, y):
                count += 1
                if count == 4:
                    return True
            else:
                count = 1

        return False

    def check_diagonal_1(self, x, y, symbol):
        """
        Searches throw the left diagonal where the coordinates(x,y) are places for a 4line
        :param x: the row of the last move
        :param y: the column of the last move
        :param symbol: the color of the player
        :return: True if a 4line is found else false
        """
        i = copy.deepcopy(x)
        j = copy.deepcopy(y)
        while i < 5 and j > 0:
            i += 1
            j -= 1

        count = 1
        while i > 0 and j < 6:
            if self.board.get(i, j) == symbol == self.board.get(i - 1, j + 1):
                count += 1
                if count == 4:
                    return True
            else:
                count = 1
            i -= 1
            j += 1

        return False

    def check_diagonal_2(self, x, y, symbol):
        """
        Searches throw the right diagonal where the coordinates(x,y) are places for a 4line
        :param x: the row of the last move
        :param y: the column of the last move
        :param symbol: the color of the player
        :return: True if a 4line is found else false
        """
        i = copy.deepcopy(x)
        j = copy.deepcopy(y)
        while i < 5 and j < 6:
            i += 1
            j += 1

        count = 1
        while i > 0 and j > 0:
            if self.board.get(i, j) == symbol == self.board.get(i - 1, j - 1):
                count += 1
                if count == 4:
                    return True
            else:
                count = 1
            i -= 1
            j -= 1

        return False
