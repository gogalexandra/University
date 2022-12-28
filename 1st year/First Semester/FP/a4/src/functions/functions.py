"""
Functions that implement program features. They should call each other, or other functions from the domain
"""
from src.domain.entity import get_day, get_sum, get_category, create_expense


def split_command(command):
    """
    Split command string into command word and parameters
    :return: (command_word, command_params)
    """

    command = command.strip()
    tokens = command.split(' ', 1)
    command_word = tokens[0].strip().lower()
    command_params = tokens[1].strip() if len(tokens) == 2 else ''

    return command_word, command_params


def to_str(expense):
    """
    Converts to string
    :param expense: an expense from the list
    :return: the expense in form of a string
    """
    return 'day: ' + str(get_day(expense)) + '   sum: ' + str(get_sum(expense)).rjust(5) + '   category: ' + str(
        get_category(expense)).rjust(2)


def add_expense(expenses_list, expense):
    """
        adds a new expense to the list
        params: expense_list is the list of the expenses
        params expense is the new expense thats being added to the list
    """
    expenses_list.append(expense)


def remove_days(expenses_list, tokens):
    """
        This function removes more than one day
        expenses_list : is the list with all expenses
        tokens: are the days that need to be removed
    """

    i = 0
    while i <= len(expenses_list) - 1:
        if int(tokens[0]) <= get_day(expenses_list[i]) <= int(tokens[2]):
            expenses_list.remove(expenses_list[i])
        else:
            i += 1


def remove_a_full_day(expenses_list, tokens):
    """
        This function removes just one day
        expenses_list : is the list with all expenses
        tokens: is the day that needs to be removed
    """
    i = 0
    while i <= len(expenses_list) - 1:
        if str(get_day(expenses_list[i])) == tokens:
            expenses_list.remove(expenses_list[i])
        else:
            i += 1


def remove_just_a_category(expenses_list, tokens):
    """
        This function removes a whole category
        expenses_list : is the list with all expenses
        tokens: is the category that needs to be removed
    """
    i = 0
    while i <= len(expenses_list) - 1:
        if str(get_category(expenses_list[i])) == str(tokens):
            expenses_list.remove(expenses_list[i])
        else:
            i += 1


def filter_category(expenses_list, cmd_params):
    """
    Keeps in the list only the specified category
    :param expenses_list: the list with all expenses
    :param cmd_params: the category that needs to be kept
    :return: the new list with only the category the user wants
    """
    i = 0
    while i <= len(expenses_list) - 1:
        if get_category(expenses_list[i]) != cmd_params:
            expenses_list.remove(expenses_list[i])
        else:
            i += 1


def filter_with_value(expenses_list, tokens):
    """
        Keeps in the list only the specified category and value
        :param expenses_list: the list with all expenses
        :param tokens: the category that needs to be kept and the value of the sum
        :return: the new list with only the category and sum that user wants
    """
    filter_category(expenses_list, tokens[0])
    if tokens[1] == '=':
        i = 0
        while i <= len(expenses_list) - 1:
            if get_sum(expenses_list[i]) != int(tokens[2]):
                expenses_list.remove(expenses_list[i])
            else:
                i += 1
    else:
        if tokens[1] == '>':
            i = 0
            while i <= len(expenses_list) - 1:
                if get_sum(expenses_list[i]) < int(tokens[2]):
                    expenses_list.remove(expenses_list[i])
                else:
                    i += 1
        else:
            i = 0
            while i <= len(expenses_list) - 1:
                if get_sum(expenses_list[i]) > int(tokens[2]):
                    expenses_list.remove(expenses_list[i])
                else:
                    i += 1


def test_init(expenses_list):
    # use this function to add the 10 required items
    # use it to set up test data
    expenses_list.append(create_expense(9, 50, 'transport'))
    expenses_list.append(create_expense(5, 50, 'food'))
    expenses_list.append(create_expense(23, 150, 'others'))
    expenses_list.append(create_expense(8, 50, 'food'))
    expenses_list.append(create_expense(20, 100, 'others'))
    expenses_list.append(create_expense(10, 700, 'food'))
    expenses_list.append(create_expense(7, 50, 'housekeeping'))
    expenses_list.append(create_expense(10, 50, 'food'))
    expenses_list.append(create_expense(2, 300, 'food'))
    expenses_list.append(create_expense(10, 50, 'food'))


def test_split_command():
    for cmd in ['insert 10 50 food', '  INsert   10 50 food', 'inSERt      10 50 food  ']:
        cmd_word, cmd_params = split_command(cmd)
        assert cmd_word == 'insert' and cmd_params == '10 50 food'

    # a,b = c ????
    cmd_word, cmd_params = split_command('exit')
    assert cmd_word == 'exit' and cmd_params == ''


# test_split_command()


def test_add_expense():
    expense_list = []
    test_init(expense_list)
    # Test that we can add a valid expense
    e = create_expense(3, 250, 'food')
    add_expense(expense_list, e)

    try:
        add_expense(expense_list, e)
    except ValueError:
        assert False


# test_add_expense()


def test_remove_a_category():
    expenses_list = []
    test_init(expenses_list)
    try:
        remove_just_a_category(expenses_list, 'food')
    except ValueError:
        assert False
    print(expenses_list)


# test_remove_a_category()


def test_remove_a_day():
    expenses_list = []
    test_init(expenses_list)
    try:
        remove_a_full_day(expenses_list, '10')
    except ValueError:
        assert False
    print(expenses_list)


# test_remove_a_day()


def test_remove_days():
    expenses_list = []
    test_init(expenses_list)
    try:
        tokens = [9, 'to', 15]
        if tokens[0] > tokens[2]:
            raise ValueError('Not valid params')
        remove_days(expenses_list, tokens)
    except ValueError:
        assert False
    print(expenses_list)


# test_remove_days()


def test_filter_category():
    expenses_list = []
    test_init(expenses_list)
    try:
        filter_category(expenses_list, 'food')
    except ValueError:
        assert False
    print(expenses_list)


# test_filter_category()


def test_filter_with_value():
    expenses_list = []
    test_init(expenses_list)
    cmd_params = 'food = 50'
    tokens = cmd_params.split(' ')
    try:
        filter_with_value(expenses_list, tokens)
    except ValueError:
        assert False
    print(expenses_list)

# test_filter_with_value()
