class UI:
    def __init__(self, game):
        self._game = game

    def split_command(self, command):
        command = command.strip()
        tokens = command.split(' ')
        command_function = tokens[0]
        coord = tokens[1]
        row = ord(coord[0].upper()) - 65
        column = int(coord[1])
        return command_function, row, column

    def print_menu(self):
        print('Move: warp C6')
        print('Fire: fire G6')
        print('Cheat: cheat')
        print('Exit: exit')

    def cheat(self):
        print(self._game.board)

    def warp(self, x, y):
        self._game.board.move(x, y)

    def fire(self, x, y):
        pass

    def start(self):
        command_dict = {'warp': self.warp, 'cheat': self.cheat}
        print(self._game.board)
        self.print_menu()

        done = False
        while not done:
            command = input('Give a command: ')
            try:
                command_word, params = command.split(' ')
                row = ord(params[0].upper()) - 65
                column = int(params[1])
                print(command_word)

                if command_word == 'cheat':
                    command_dict[command_word]()

                elif command_word == 'exit':
                    done = True

                elif command_word in command_dict:
                    command_dict[command_word](row, column)
                    print(self._game.board)
                    # if self._game.check_if_game_is_won:
                    #     print('You lose :(')

                else:
                    raise Exception("Wrong command !!!")
            except Exception as ex:
                print('\n' + str(ex))


