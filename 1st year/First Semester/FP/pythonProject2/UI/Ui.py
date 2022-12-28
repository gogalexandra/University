
class UI:
    def __init__(self, my_game):
        self._game = my_game

    def cheat(self):
        print(self._game.board)

    def split_command(self, command):
        """
        Split - command string into command word and parameters
        :return: (command_word, command_params)
        """
        # add 10, 2000, in, Salary
        # insert / remove / list
        # command_word = add / insert / remove / list
        # command_params = 10, 2000, in, Salary
        command = command.strip()
        tokens = command.split(' ', 1)
        command_word = tokens[0].strip().lower()
        command_params = tokens[1].strip() if len(tokens) == 2 else ''

        return command_word, command_params

    def start(self):
        command_dict = {'warp': self._game.make_a_move,
                        'cheat': self.cheat,
                        }
        done = False
        while not done:
            command = input('command> ')
            try:
                cmd_word, cmd_params = self.split_command(command)

                if cmd_word in command_dict:

                    command_dict[cmd_word](cmd_params)

                elif cmd_word == 'exit':
                    done = True

                else:
                    print('bad command')

            except ValueError as ve:
                print(str(ve))
        # done = False
        # while not done:
        #     try:
        #         human_color = input('What color do you want to be? R/Y:').upper()
        #         if human_color == 'R':
        #             computer_color = 'Y'
        #             human_turn = True
        #             done = True
        #         elif human_color == 'Y':
        #             computer_color = 'R'
        #             human_turn = False
        #             done = True
        #         else:
        #             raise Exception('No such color!')
        #
        #     except Exception as input_ex:
        #         print(str(input_ex))
        #
        # finished = False
        #
        # while not finished:
        #
        #     try:
        #
        #         # Print the board state
        #         print(self._game.board)
        #
        #         if self._game.board._empty_circles == 0:
        #             print('Tie!')
        #             done = True
        #             return
        #
        #         if human_turn:
        #             coord = self.read_human_move()
        #             if self._game.human_move(coord, human_color):
        #                 print('You win!')
        #                 print(self._game.board)
        #                 finished = True
        #                 done = True
        #
        #         else:
        #             if self._game.computer_move(computer_color, human_color):
        #                 print('You lose!')
        #                 print(self._game.board)
        #                 finished = True
        #                 done = True
        #
        #         human_turn = not human_turn
        #
        #     except Exception as game_ex:
        #         print(str(game_ex))
