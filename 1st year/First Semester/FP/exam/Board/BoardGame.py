from texttable import Texttable
import random


def on_same_diagonal(row, column, current_row, current_column):
    same_diagonal = False
    if (row - current_row) == (column - current_column) or abs((row - current_row)) == (column - current_column) or (row - current_row) == abs((column - current_column)):
        same_diagonal = True
    # if row > current_row and column > current_column:
    #     if row - current_row == column - current_column:
    #         same_diagonal = True
    # elif row < current_row and column < current_column:
    #     if current_row- row == current_column - column:
    #         same_diagonal = True
    # elif row > current_row and column < current_column:
    #     if row - current_row == -(column - current_column):
    #         same_diagonal = True
    # elif row < current_row and column > current_column:
    #     if -(row - current_row) == column - current_column:
    #         same_diagonal = True
    return same_diagonal


class Board:
    def __init__(self):
        self._rows = 8
        self._columns = 9
        self._data = [[None for j in range(self._columns)] for i in range(self._rows)]
        for i in range(self._rows):
            self._data[i][0] = chr(65 + i)

        self.place_stars()
        self.place_ship()
        self.place_enemies()

    @property
    def row_count(self):
        return self._rows

    @property
    def col_count(self):
        return self._columns

    @property
    def get_ship(self):
        for i in range(self._rows):
            for j in range(self._columns):
                if self.get(i, j) == 'E':
                    return i, j

    def get(self, x, y):
        """
        Return symbol at position [x,y] on board
            None     -> empty square
            '*'      -> star
            'B'      -> enemy
        """
        return self._data[x][y]

    def check_star(self, x, y):
        if x == 0:
            if y == 1:
                return self._data[x][y] is None and \
                       self._data[x][y + 1] is None and \
                       self._data[x + 1][y] is None and \
                       self._data[x + 1][y + 1] is None

            elif y == 8:
                return self._data[x][y] is None and \
                       self._data[x][y - 1] is None and \
                       self._data[x + 1][y] is None and \
                       self._data[x - 1][y - 1] is None

            else:
                return self._data[x][y] is None and \
                       self._data[x][y + 1] is None and \
                       self._data[x][y - 1] is None and \
                       self._data[x + 1][y] is None and \
                       self._data[x + 1][y + 1] is None and \
                       self._data[x + 1][y - 1] is None

        elif x == 7:
            if y == 1:
                return self._data[x][y] is None and \
                       self._data[x][y + 1] is None and \
                       self._data[x - 1][y] is None and \
                       self._data[x - 1][y + 1] is None

            elif y == 8:
                return self._data[x][y] is None and \
                       self._data[x][y - 1] is None and \
                       self._data[x - 1][y] is None and \
                       self._data[x - 1][y - 1] is None

            else:
                return self._data[x][y] is None and \
                       self._data[x][y + 1] is None and \
                       self._data[x][y - 1] is None and \
                       self._data[x - 1][y] is None and \
                       self._data[x - 1][y + 1] is None and \
                       self._data[x - 1][y - 1] is None

        elif y == 1:
            return self._data[x][y] is None and \
                   self._data[x][y + 1] is None and \
                   self._data[x - 1][y] is None and \
                   self._data[x + 1][y] is None and \
                   self._data[x - 1][y + 1] is None and \
                   self._data[x + 1][y + 1] is None

        elif y == 8:
            return self._data[x][y] is None and \
                   self._data[x][y - 1] is None and \
                   self._data[x - 1][y] is None and \
                   self._data[x + 1][y] is None and \
                   self._data[x - 1][y - 1] is None and \
                   self._data[x + 1][y - 1] is None

        else:
            return self._data[x][y] is None and \
                   self._data[x][y + 1] is None and \
                   self._data[x][y - 1] is None and \
                   self._data[x - 1][y] is None and \
                   self._data[x + 1][y] is None and \
                   self._data[x - 1][y + 1] is None and \
                   self._data[x - 1][y - 1] is None and \
                   self._data[x + 1][y - 1] is None and \
                   self._data[x + 1][y + 1] is None

    def place_stars(self):
        nr_of_stars = 0
        list_rows = [0, 1, 2, 3, 4, 5, 6, 7]
        list_columns = [1, 2, 3, 4, 5, 6, 7, 8]

        while nr_of_stars < 10:
            random_row = random.choice(list_rows)
            random_column = random.choice(list_columns)
            if self._data[random_row][random_column] is None:
                if self.check_star(random_row, random_column):
                    self._data[random_row][random_column] = '*'
                    nr_of_stars += 1

    def place_ship(self):
        list_rows = [0, 1, 2, 3, 4, 5, 6, 7]
        list_columns = [1, 2, 3, 4, 5, 6, 7, 8]
        placed_well = False

        while not placed_well:
            random_row = random.choice(list_rows)
            random_column = random.choice(list_columns)
            if self._data[random_row][random_column] is None:
                self._data[random_row][random_column] = 'E'
                placed_well = True

    def place_enemies(self):
        list_rows = [0, 1, 2, 3, 4, 5, 6, 7]
        list_columns = [1, 2, 3, 4, 5, 6, 7, 8]
        nr_of_enemies = 0

        while nr_of_enemies < 3:
            random_row = random.choice(list_rows)
            random_column = random.choice(list_columns)
            if self._data[random_row][random_column] is None:
                self._data[random_row][random_column] = 'B'
                nr_of_enemies += 1

    def is_free(self, x, y):
        return self.get(x, y) is None

    def delete_enemy(self, x, y):
        self._data[x][y] = None

    def check_valid_move(self, row, column, current_row, current_column):
        valid_move = True

        while valid_move:
            if row == current_row:
                if column < current_column:
                    i = current_column
                    while i > column:
                        if self._data[row][i] == '*':
                            valid_move = False
                        else:
                            i -= 1
                else:
                    i = current_column
                    while i > column:
                        if self._data[row][i] != '*':
                            valid_move = False
                        else:
                            i += 1

            elif column == current_column:
                if row < current_row:
                    i = current_row
                    while i > row:
                        if self._data[i][column] != '*':
                            valid_move = False
                        else:
                            i -= 1
                else:
                    i = current_row
                    while i > row:
                        if self._data[i][column] != '*':
                            valid_move = False
                        else:
                            i += 1

            elif (current_row < row) and (current_column < column):
                i = current_row
                j = current_column
                while i < row and j < column:
                    if self._data[i][j] == '*':
                        valid_move = False
                    else:
                        i += 1
                        j += 1

            elif (current_row > row) and (current_column > column):
                i = current_row
                j = current_column
                while i > row and j > column:
                    if self._data[i][j] == '*':
                        valid_move = False
                    else:
                        i -= 1
                        j -= 1

            elif (current_row > row) and (current_column < column):
                i = current_row
                j = current_column
                while i > row and j < column:
                    if self._data[i][j] == '*':
                        valid_move = False
                    else:
                        i -= 1
                        j += 1

            elif (current_row < row) and (current_column > column):
                i = current_row
                j = current_column
                while i < row and j > column:
                    if self._data[i][j] == '*':
                        valid_move = False
                    else:
                        i += 1
                        j -= 1

            return valid_move

    def move(self, row, column):
        if row not in [0, 7] or column not in [1, 8]:
            raise Exception('Invalid move')

        current_row, current_column = self.get_ship
        if row != current_row or column != current_column :
            raise Exception('Invalid move')

        # if self._data[row][column] == '*' or 'B':
        #     raise Exception('Invalid move!')

        if self.check_valid_move(row, column, current_row, current_column):
            self._data[current_row][current_column] = None
            self._data[row][column] = 'E'
        else:
            raise Exception('Star in way')

    def __str__(self):
        t = Texttable()
        t.header(['0', '1', '2', '3', '4', '5', '6', '7', '8'])
        for row in range(8):

            row_data = []

            for index in self._data[row]:
                if index is None:
                    row_data.append(' ')
                elif index == '*' or index == 'B' or index == 'E' or index in ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H']:
                    row_data.append(index)
            t.add_row(row_data)

        return t.draw()

