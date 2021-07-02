from texttable import Texttable


class Board:
    def __init__(self):
        self._rows = 6
        self._columns = 7
        self._data = [[None for j in range(self._columns)] for i in range(self._rows)]
        # Count the number of unoccupied squares remaining
        self._free_squares = self._rows * self._columns

    @property
    def row_count(self):
        return self._rows

    @property
    def col_count(self):
        return self._columns

    @property
    def empty_squares(self):
        return self._free_squares

    def get(self, x, y):
        """
        Return symbol at position [x,y] on board
            None     -> empty square
            'R', 'Y' -> symbols
        """
        return self._data[x][y]

    def is_free(self, x, y):
        return self.get(x, y) is None

    def move(self, y, symbol):
        """

        :param y: is the column where the move should be made
        :param symbol: the color of the player
        :return: the parameters of the move(coordinates + color)
        """
        # Mark exceptions for moves
        if symbol not in ['R', 'Y']:
            raise Exception('Bad color!')

        empty_squares_left = False
        row = self._rows - 1
        while row >= 0 and not empty_squares_left:
            if self.is_free(row, y):
                empty_squares_left = True
            else:
                row -= 1

        if empty_squares_left:
            self._data[row][y] = symbol
            self._free_squares -= 1

        else:
            raise Exception('Column is full')

        return row, y, symbol

    def delete_move(self, x, y):
        self._data[x][y] = None

    def __str__(self):
        t = Texttable()
        t.header(['A', 'B', 'C', 'D', 'E', 'F', 'G'])
        for row in range(6):
            row_data = []

            for index in self._data[row]:
                if index is None:
                    row_data.append(' ')
                elif index == 'R' or index == 'Y':
                    row_data.append(index)
            t.add_row(row_data)

        return t.draw()
