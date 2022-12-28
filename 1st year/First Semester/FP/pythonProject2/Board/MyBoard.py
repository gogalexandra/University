from texttable import Texttable
from random import choice


class Board:
    def __init__(self):

        self._rows = 8
        self._columns = 9

        # Empty squares marked with None
        self._data = [[None for j in range(self._columns)] for i in range(self._rows)]
        for i in range(self._rows):
            self._data[i][0] = chr(65 + i)

        self.place_stars()
        self.place_endeavour()
        self.place_cruisers()


    @property
    def nr_of_rows(self):
        """
        Getter fot the number of rows
        :return:
        """
        return self._rows

    @property
    def nr_of_columns(self):
        """
        Getter fot the number of columns
        :return:
        """
        return self._columns

    @property
    def endeavour(self):
        """
        Getter for the current position of the endeavour
        :return:
        """
        for i in range(self._rows):
            for j in range(self._columns):
                if self.get(i, j) == 'E':
                    return i, j

    def get(self, x, y):
        """
        Return symbol at position [x,y] on board
            None     -> empty circle
            'R', 'Y' -> the playing colors
        """
        return self._data[x][y]

    def is_star(self, x, y):
        """
        Checks if a desired space is free
        :param x: the x coordinate
        :param y: the y coordinate
        :return:
        """
        return self.get(x, y) is None

    def chek_surroundings(self, x, y):
        """
        This function checks for the surroundings of a given place on the board to ensure that a star can
        be placed on that specific spot or not
        :param x: the x coordinate
        :param y: the y coordinate
        :return: true if it can be placed or false otherwise
        """
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
        """
        This function places randomly the stars on the board
        :return:
        """
        count = 0
        ok = False

        while not ok:
            i = choice([0, 1, 2, 3, 4, 5, 6, 7])
            j = choice([1, 2, 3, 4, 5, 6, 7, 8])

            if self.chek_surroundings(i, j):
                self._data[i][j] = '*'
                count += 1

            if count == 10:
                ok = True

    def place_endeavour(self):
        """
        This function places randomly the endeavour on the board
        :return:
        """
        ok = False

        while not ok:
            i = choice([0, 1, 2, 3, 4, 5, 6, 7])
            j = choice([1, 2, 3, 4, 5, 6, 7, 8])

            if self._data[i][j] is None:
                self._data[i][j] = 'E'
                ok = True

    def place_cruisers(self):
        """
        This function places randomly the cruisers on the board
        :return:
        """
        ok = False
        count = 0

        while not ok:
            i = choice([0, 1, 2, 3, 4, 5, 6, 7])
            j = choice([1, 2, 3, 4, 5, 6, 7, 8])

            if self._data[i][j] is None:
                self._data[i][j] = 'B'
                count += 1

            if count == 3:
                ok = True

    def move(self, x, y):
        """
        This function makes a move by choosing the given column and placing the circle in the free slot
        of that column
        :param y: the y coordinate
        :param color: the color of the given payer
        :return: the x, y, color and make the move
        """
        i, j = self.endeavour
        self._data[i][j] = None
        self._data[x][y] = 'E'

    def delete_move(self, x, y):
        """
        This function deletes a move, sets the value of that slot back to None
        :param x: the x coordinate
        :param y: the y coordinate
        :return:
        """
        self._data[x][y] = None

    def __str__(self):
        """
        The string representation of the game board
        :return:
        """
        t = Texttable()
        t.header(['0', '1', '2', '3', '4', '5', '6', '7', '8'])
        for row in range(8):
            row_data = []

            for index in self._data[row]:
                if index is None:
                    row_data.append(' ')
                elif index in ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', '*']:
                    row_data.append(index)
            t.add_row(row_data)

        return t.draw()
