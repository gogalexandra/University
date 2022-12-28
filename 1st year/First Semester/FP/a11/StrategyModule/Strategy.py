import copy
import random


class Strategy:
    """
    Class decides computer's next move
    Basic implementation of the strategy design pattern - https://refactoring.guru/design-patterns/strategy
    """

    def next_move(self, board, color):
        """
        Return the computer's next move
        """
        # TODO Acts like an abstract base class
        raise Exception('Subclass strategy in order to implement computer play!')

#
# class RandomMoveStrategy(Strategy):
#     def next_move(self, board, color):
#         """
#         Make a random, but valid move
#         """
#         # Store possible moves here
#         available_moves = []
#
#         for col in range(board.col_count):
#             for row in range(board.row_count):
#                 if board.is_free(row, col):
#                     available_moves.append(col)
#         # Pick one of the available moves
#         move = random.choice(available_moves)
#         return board.move(move, color)


class ImprovedMoveStrategy(Strategy):
    def next_move(self, board, color):
        """
        This strategy enables the computer to win if having the chance, to block if the user can win
        or make a random move otherwise
        """

        if color == 'R':
            rival_color = 'Y'
        else:
            rival_color = 'R'

        available_moves = []

        for col in range(board.col_count):  # 0 - 4
            move = ()
            for row in range(board.row_count):  # 0 - 3

                if board.is_free(row, col) and row == 5:
                    move = (row, col)
                    available_moves.append(move)
                elif board.is_free(row, col) and not board.is_free(row + 1, col) and row < 5:
                    move = (row, col)
                    available_moves.append(move)

        win = self.move_to_win(board, color, available_moves)
        block = self.move_to_block(board, rival_color, available_moves)
        random_move = random.choice(available_moves)[1]
        if win is not None:
            return board.move(win, color)
        elif block is not None:
            return board.move(block, color)
        else:
            return board.move(random_move, color)

    def move_to_win(self, board, color, available_moves):
        """

        :param board: the board game
        :param color: the color of the player
        :param available_moves: the possible moves
        :return: a move that finishes the game or none
        """
        b = copy.deepcopy(board)
        possible_win = False
        winning_move = ()
        for m in available_moves:
            params = b.move(m[1], color)
            if self.check_if_game_is_won(params[0], params[1], params[2], b):
                possible_win = True
                winning_move = m
                break
            else:
                b.delete_move(m[0], m[1])

        if possible_win:
            return winning_move[1]
        else:
            return None

    def move_to_block(self, board, rival_color, available_moves):
        """

        :param board: the board game
        :param rival_color: the color of the other player
        :param available_moves: the possible moves
        :return: a move that blocks the other player or none
        """

        b = copy.deepcopy(board)
        possible_win = False
        blocking_move = ()
        for m in available_moves:
            params = b.move(m[1], rival_color)
            if self.check_if_game_is_won(params[0], params[1], params[2], b):
                possible_win = True
                blocking_move = m
                break
            else:
                b.delete_move(m[0], m[1])

        if possible_win:
            return blocking_move[1]
        else:
            return None

    def check_if_game_is_won(self, x, y, color, board):
        return self.check_row(x, color, board) or \
               self.check_column(y, color, board) or \
               self.check_left_diagonal(x, y, color, board) or \
               self.check_right_diagonal(x, y, color, board)

    def check_row(self, x, color, board):
        """
        :param x: is the row where the program searched for a 4 line(the row of the last move)
        :param symbol: the color of the player
        :return: True if a 4line is found else false
        """
        count = 1
        for y in range(board.col_count - 1):
            if board.get(x, y) == color == board.get(x, y + 1):
                count += 1
                if count == 4:
                    return True
            else:
                count = 1

        return False

    def check_column(self, y, color, board):
        """
        :param y: is the column where the program searched for a 4 line(the column of the last move)
        :param color: the color of the player
        :param board: the board game
        :return: True if a 4line is found else false
        """
        count = 1
        for x in range(board.row_count - 1):
            if board.get(x, y) == color == board.get(x + 1, y):
                count += 1
                if count == 4:
                    return True
            else:
                count = 1

        return False

    def check_left_diagonal(self, x, y, color, board):
        """
        Searches throw the left diagonal where the coordinates(x,y) are places for a 4line
        :param x: the row of the last move
        :param y: the column of the last move
        :param color: the color of the player
        :param board: the board game
        :return: True if a 4line is found else false
        """
        i = copy.deepcopy(x)
        j = copy.deepcopy(y)
        while i < 5 and j > 0:
            i += 1
            j -= 1

        count = 1
        while i > 0 and j < 6:
            if board.get(i, j) == color == board.get(i - 1, j + 1):
                count += 1
                if count == 4:
                    return True
            else:
                count = 1
            i -= 1
            j += 1

        return False

    def check_right_diagonal(self, x, y, color, board):
        """
        Searches throw the left diagonal where the coordinates(x,y) are places for a 4line
        :param x: the row of the last move
        :param y: the column of the last move
        :param color: the color of the player
        :param board: the board game
        :return: True if a 4line is found else false
        """
        i = copy.deepcopy(x)
        j = copy.deepcopy(y)
        while i < 5 and j < 6:
            i += 1
            j += 1

        count = 1
        while i > 0 and j > 0:
            if board.get(i, j) == color == board.get(i - 1, j - 1):
                count += 1
                if count == 4:
                    return True
            else:
                count = 1
            i -= 1
            j -= 1

        return False
