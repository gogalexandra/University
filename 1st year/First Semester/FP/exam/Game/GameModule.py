class Game:
    def __init__(self, board):
        self._board = board

    @property
    def board(self):
        return self._board

    def player_move(self, x, y):
        self._board.move(x, y)
        return self.check_if_game_is_won()

    def check_if_game_is_won(self):
        game_won = True
        i = 0
        j = 1
        while i < 8 and j < 9:
            if self._board.date[i][j] == 'B':
                game_won = False
            i += 1
            j += 1
        return game_won
