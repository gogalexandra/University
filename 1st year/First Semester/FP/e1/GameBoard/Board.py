import random

from texttable import Texttable


class Board:
    def __init__(self, dim, apples):
        self._dim = dim
        self.nr_of_apples = apples
        self._data = [[None for j in range(self._dim)] for i in range(self._dim)]
        self.place_snake()
        self.place_apples()

    def get(self, x, y):
        return self._data[x][y]

    def get_end_of_tail(self):
        """
        it suppose to give the coord of the end of the tail so it could be set to none after moving
        :return:
        """
        pass

    def coord_head(self):
        x = 0
        y = 0
        for i in range(self._dim):
            for j in range(self._dim):
                if self.get(i, j) == '*':
                    x = i
                    y = j
        return x, y

    def apples_left(self):
        nr = 0
        for i in range(self._dim):
            for j in range(self._dim):
                if self.get(i, j) == '.':
                    nr += 1
        return nr

    def place_snake(self):
        y_snake = (self._dim - 1) // 2
        x_snake = (self._dim - 3) // 2
        self._data[x_snake][y_snake] = '*'
        self._data[x_snake + 1][y_snake] = '+'
        self._data[x_snake + 2][y_snake] = '+'

    def place_apples(self):
        already_placed = 0
        must_be_placed = self.nr_of_apples
        while already_placed < must_be_placed:
            random_row = random.randint(0, self._dim - 1)
            random_column = random.randint(0, self._dim - 1)
            if self._data[random_row][random_column] is None:
                if self.check_apples(random_row, random_column):
                    self._data[random_row][random_column] = '.'
                    already_placed += 1

    def check_apples(self, x, y):
        placed_well = True
        if x == 0:
            if y == 0:
                if self._data[x][y + 1] == '.' or self._data[x + 1][y] == '.':
                    placed_well = False
                    return placed_well
            elif y == self._dim - 1:
                if self._data[x][y - 1] == '.' or self._data[x + 1][y] == '.':
                    placed_well = False
                    return placed_well
            else:
                if self._data[x][y - 1] == '.' or self._data[x][y + 1] == '.' or self._data[x + 1][y] == '.':
                    placed_well = False
                    return placed_well

        elif x == self._dim - 1:
            if y == 0:
                if self._data[x - 1][y] == '.' or self._data[x][y + 1] == '.':
                    placed_well = False
                    return placed_well
            elif y == self._dim - 1:
                if self._data[x][y - 1] == '.' or self._data[x - 1][y] == '.':
                    placed_well = False
                    return placed_well
            else:
                if self._data[x][y - 1] == '.' or self._data[x][y + 1] == '.' or self._data[x - 1][y] == '.':
                    placed_well = False
                    return placed_well

        elif y == 0:
            if self._data[x - 1][y] == '.' or self._data[x + 1][y] == '.' or self._data[x][y + 1] == '.':
                placed_well = False
                return placed_well

        elif y == self._dim - 1:
            if self._data[x - 1][y] == '.' or self._data[x + 1][y] == '.' or self._data[x][y - 1] == '.':
                placed_well = False
                return placed_well

        else:
            if self._data[x - 1][y] == '.' or self._data[x + 1][y] == '.' or self._data[x][y - 1] == '.' or \
                    self._data[x][y + 1] == '.':
                placed_well = False
                return placed_well

        return placed_well

    def eat_apple(self, x, y):
        self._data[x][y] = '*'
        already_placed = 0
        must_be_placed = 1
        while already_placed < must_be_placed:
            random_row = random.randint(0, self._dim - 1)
            random_column = random.randint(0, self._dim - 1)
            if self._data[random_row][random_column] is None:
                if self.check_apples(random_row, random_column):
                    self._data[random_row][random_column] = '.'
                    already_placed += 1

    def move(self, direction, params):
        x_head, y_head = self.coord_head()
        if direction == 'move':
            if params != ' ':
                moves = int(params)
            else:
                moves = 1
            if self._data[x_head + 1][y_head] == '+':
                if self._data[x_head - 1][y_head] == '.':
                    self.eat_apple(x_head - 1, y_head)
                else:
                    self.move_snake_up(x_head, y_head, moves)
            elif self._data[x_head - 1][y_head] == '+':
                if self._data[x_head + 1][y_head] == '.':
                    self.eat_apple(x_head + 1, y_head)
                else:
                    self.move_snake_down(x_head, y_head, moves)
            elif self._data[x_head][y_head - 1] == '+':
                if self._data[x_head][y_head + 1] == '.':
                    self.eat_apple(x_head, y_head + 1)
                else:
                    self.move_snake_right(x_head, y_head, moves)
            else:
                if self._data[x_head][y_head - 1] == '.':
                    self.eat_apple(x_head, y_head - 1)
                else:
                    self.move_snake_left(x_head, y_head, moves)

        elif direction == 'up':
            if self._data[x_head - 1][y_head] == '+':
                raise Exception('Dead')
            elif self._data[x_head+1][y_head] == '+':
                self.move_snake_up(x_head,y_head, 1)
            elif self._data[x_head][y_head-1] == '+':
                self.move_snake_left(x_head, y_head, 1)
            else:
                self.move_snake_right(x_head, y_head, 1)

        elif direction == 'down':
            if self._data[x_head + 1][y_head] == '+':
                raise Exception('Dead')
            elif self._data[x_head][y_head-1] == '+':
                self.move_snake_up(x_head, y_head, 1)
            elif self._data[x_head][y_head - 1] == '+':
                self.move_snake_right(x_head, y_head, 1)
            else:
                self.move_snake_left(x_head, y_head, 1)

        elif direction == 'left':
            if self._data[x_head][y_head-1] == "+":
                raise Exception('Dead')
            elif self._data[x_head+1][y_head] == "+":
                self.move_snake_left(x_head, y_head, 1)
            elif self._data[x_head-1][y_head] == "+":
                self.move_snake_right(x_head, y_head, 1)
            elif self._data[x_head][y_head+1] == "+":
                self.move_snake_down(x_head, y_head, 1)

        elif direction == 'right':
            if self._data[x_head][y_head+1] == "+":
                raise Exception('Dead')
            elif self._data[x_head+1][y_head] == "+":
                self.move_snake_right(x_head, y_head, 1)
            elif self._data[x_head-1][y_head] == "+":
                self.move_snake_left(x_head, y_head, 1)
            elif self._data[x_head][y_head+1] == "+":
                self.move_snake_up(x_head, y_head, 1)

    def __str__(self):
        t = Texttable()
        for row in range(self._dim):

            row_data = []

            for index in self._data[row]:
                if index is None:
                    row_data.append(' ')
                elif index == '*' or index == '+' or index == '.':
                    row_data.append(index)
            t.add_row(row_data)

        return t.draw()

    def move_snake_up(self, x, y, z):
        self._data[x - z][y] = '*'
        i = 0
        while i < z:
            self._data[x - i][y] = "+"
            i += 1
        self._data[x + z + 1][y] = None
        # i,j = self.get_end_of_tail()
        # self._data[i][j] = None

    def move_snake_down(self, x, y, z):
        self._data[x + z][y] = '*'
        i = 0
        while i < z:
            self._data[x + i][y] = "+"
            i += 1
        self._data[x - z - 1][y] = None
        # i,j = self.get_end_of_tail()
        # self._data[i][j] = None

    def move_snake_left(self, x, y, z):
        self._data[x][y - z] = '*'
        i = 0
        while i < z:
            self._data[x][y - i] = "+"
            i += 1
        self._data[x][y + z + 1] = None
        # i,j = self.get_end_of_tail()
        # self._data[i][j] = None

    def move_snake_right(self, x, y, z):
        self._data[x][y + z] = '*'
        i = 0
        while i < z:
            self._data[x][y + i] = "+"
            i += 1
        self._data[x][y - z - i] = None
        # i,j = self.get_end_of_tail()
        # self._data[i][j] = None
