"""
Assemble the program and start the user interface here
"""
from datetime import datetime
from src.ui.console import add_expense_command_ui, list_expense_command_ui, remove_expense_command_ui, \
    total_sum_of_category, max_day_of_expense, sort_by_day, sort_by_category, filter_expenses_command_ui, \
    undo_command_ui, menu
from src.functions.functions import split_command, test_init


def start_command_ui():
    expenses_list = []
    history = []
    test_init(expenses_list)
    command_dict = {'add': add_expense_command_ui, 'insert': add_expense_command_ui, 'list': list_expense_command_ui,
                    'remove': remove_expense_command_ui, 'sum': total_sum_of_category, 'max': max_day_of_expense,
                    'sort_day': sort_by_day, 'sort_category': sort_by_category, 'filter': filter_expenses_command_ui,
                    'undo': undo_command_ui}
    done = False

    while not done:

        menu()
        command = input('command: ')
        today = datetime.now().day
        try:

            cmd_word, cmd_params = split_command(command)
            if cmd_word == 'sort':
                if cmd_params == 'day':
                    cmd_word = 'sort_day'
                else:
                    cmd_word = 'sort_category'
            if cmd_word in command_dict:

                if cmd_word == 'add':
                    cmd_params = str(today) + ' ' + cmd_params

                if cmd_word in ['add', 'insert', 'remove', 'filter']:
                    history.append(expenses_list.copy())

                if cmd_word == 'undo':
                    try:
                        expenses_list = command_dict[cmd_word](history)
                    except ValueError as ve:
                        print(str(ve))

                else:
                    command_dict[cmd_word](expenses_list, cmd_params)

            elif cmd_word == 'exit':
                done = True
            else:
                print('bad command')
        except ValueError as ve:
            print(str(ve))


start_command_ui()
