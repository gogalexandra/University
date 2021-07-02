class Game:
    def __init__(self, board):
        self._board = board

    @property
    def board(self):
        return self._board

    def move(self, command, params):
        self._board.move(command, params)

    def check_if_game_is_lost(self):
        game_lost = False
        x_head, y_head = self._board.coord_head()

        if x_head < 0 or y_head < 0 or x_head > self._board._dim -1 or y_head > self._board._dim-1:
            game_lost = True
            return game_lost

        for i in range(self._board._dim):
            for j in range(self._board._dim):
                if self.board.get(i, j) == '+':
                    if i == x_head and j == y_head:
                        game_lost = True
        return game_lost
