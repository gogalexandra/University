class UI:
    def __init__(self, game):
        self._game = game

    def print_menu(self):
        print('Move ')
        print('Move [n]')
        print('up/down/left/right')
        print('Exit: exit')

    def start(self):
        print(self._game.board)
        self.print_menu()

        done = False
        while not done:
            command = input('Give a command: ')

            if ' ' in command:
                command_word, params = command.split(' ')
            else:
                command_word =command
                params = ' '
            ok = self._game.check_if_game_is_lost()
            self._game.move(command_word, params)
            # ok = self._game.check_if_game_is_lost()
            if ok:
                print('You have lost')
                done = True
            else:
                print(self._game.board)