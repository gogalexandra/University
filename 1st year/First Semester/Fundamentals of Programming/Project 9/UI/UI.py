class UI:
    def __init__(self, strategy, game):
        self._strategy = strategy
        self._game = game

    def read_human_move(self):
        # TODO Add error handling
        coord = input('where to play>').upper()
        if coord not in ['A', 'B', 'C', 'D', 'E', 'F', 'G']:
            raise Exception('Not a valid column!!')
        # Calculate column index from character's ASCII code
        col = ord(coord[0].lower()) - 97
        return col

    def start(self):

        done = False
        while not done:
            try:
                finished = False
                color = input('What color do you want to be? R or Y ').upper()

                if color == "Y":
                    user_color = "Y"
                    computer_color = "R"
                    human_turn = False
                    done = True

                elif color == 'R':
                    user_color = "R"
                    computer_color = "Y"
                    human_turn = True
                    done = True

                else:
                    raise Exception("Wrong color !!!")

            except Exception as ex:
                print('\n' + str(ex))

        finished = False

        while not finished:
            try:
                # Print the board state
                print(self._game.board)

                if self._game.board.empty_squares == 0:
                    done = True
                    raise Exception('Tie!!!!')

                if human_turn:
                    coord = self.read_human_move()
                    # print(coord)

                    if self._game.human_move(coord, user_color):
                        print(self._game.board)
                        print('You win!')
                        finished = True
                        done = True
                else:

                    if self._game.computer_move(computer_color):
                        print(self._game.board)
                        print('You lose!')
                        finished = True
                        done = True
                human_turn = not human_turn

            except Exception as ex:
                print('\n' + str(ex))

