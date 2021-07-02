"""
This is the user interface module. These functions call functions from the domain and functions module.
"""
from src.domain.entity import get_day, get_sum, get_category, create_expense
from src.functions.functions import to_str, remove_days, remove_a_full_day, remove_just_a_category, add_expense, \
    filter_category, filter_with_value, test_init


def menu():
    print('Your options are :')
    print('Add to current day an expense')
    print('Insert to a specific day an expense')
    print('List / List a category/ List a category with a specific sum')
    print('Remove a day / several days / a category')
    print('Sum of a category : sum category')
    print('Max day')
    print('Sort day/category')
    print('Filter category/ +-value')
    print('Undo')


def remove_expense_command_ui(expenses_list, cmd_params):
    """
        Removes a hole day, a hole type of category or more than one day
        expenses_list : is the list with all the expenses
        cmd_params: are the parameters of what should be removed

    """
    tokens = cmd_params.split(' ')
    for i in range(len(tokens)):
        tokens[i] = tokens[i].strip()
    if tokens[0] > tokens[2]:
        raise ValueError('Not valid params')

    if len(tokens) == 1:
        if tokens[0].isalpha():
            remove_just_a_category(expenses_list, tokens[0])
        else:
            remove_a_full_day(expenses_list, tokens[0])
    else:
        remove_days(expenses_list, tokens)


def add_expense_command_ui(expenses_list, cmd_params):
    """
        This function add a new expense
        expenses_list : is the list with all expenses
        cmd_params: is the info for the new expense
    """

    tokens = cmd_params.split(' ')
    if len(tokens) != 3:
        raise ValueError('Invalid parameter count')

    for i in range(len(tokens)):
        tokens[i] = tokens[i].strip()

    expense = create_expense(int(tokens[0]), int(tokens[1]), (tokens[2]))
    add_expense(expenses_list, expense)


def list_expense_command_ui(expenses_list, cmd_params):
    """
        This function prints the expenses that the user wants
        expenses_list : the list with all the expenses
        cmd_params : the parameters of the expenses that need to be printed
    """
    if len(cmd_params) == 0:
        display_all_expenses_list(expenses_list)
    else:
        tokens = cmd_params.split(' ')
        if len(tokens) == 1:
            display_only_one_category(expenses_list, cmd_params)
        else:
            display_specific_sums(expenses_list, tokens)


def display_all_expenses_list(expenses_list):
    """
        This function prints the whole list of expenses
        expenses_list: is the list with all expenses
    """
    ok = False
    expenses_list.sort(key=get_day)
    for expense in expenses_list:
        print(to_str(expense))
        ok = True
    if not ok:
        print('There is no such expense')


def test_display_all_expenses_list():
    expenses_list = []
    test_init(expenses_list)
    try:
        display_all_expenses_list(expenses_list)
    except ValueError:
        assert False


#test_display_all_expenses_list()


def display_only_one_category(expenses_list, cmd_params):
    """
        This function prints only one of the category from the expenses types
        expenses_list: the list with all expenses
        cmd_params: the category that needs to be printed
    """
    ok = False
    for expense in expenses_list:
        if get_category(expense) == cmd_params:
            print(to_str(expense))
            ok = True
    if not ok:
        print('There is no such expense')


def test_display_only_one_category():
    expenses_list = []
    test_init(expenses_list)
    try:
        display_only_one_category(expenses_list,'food')
    except ValueError:
        assert False


#test_display_only_one_category()


def display_specific_sums(expenses_list, tokens):
    """
        this function prints only the expenses that have a specific sum
        expenses_list : the list with all expenses
        tokens: the parameters of the expenses that need to be printed related to the sum
    """
    ok = False
    for expense in expenses_list:
        if get_category(expense) == tokens[0]:
            if tokens[1] == '=':
                if get_sum(expense) == int(tokens[2]):
                    print(to_str(expense))
                    ok = True

            else:
                if tokens[1] == '>':
                    if get_sum(expense) > int(tokens[2]):
                        print(to_str(expense))
                        ok = True

                else:
                    if get_sum(expense) < int(tokens[2]):
                        print(to_str(expense))
                        ok = True
    if ok == False:
        print('There is no such expense')


def test_display_specific_sums():
    expenses_list = []
    test_init(expenses_list)
    tokens = 'food = 50'
    try:
        display_specific_sums(expenses_list, tokens)
    except ValueError:
        assert False


#test_display_specific_sums()


def total_sum_of_category(expenses_list, cmd_params):
    """
    This function calculates the total sum of expenses for a specific category
    :param expenses_list: the list with all expenses
    :param cmd_params: points to the category of which we should calculate the sum
    :return: the sum for the category or a message if that category has not been found
    """
    sum_category = 0
    for expense in expenses_list:
        if cmd_params == get_category(expense):
            sum_category += int(get_sum(expense))
    if sum_category != 0:
        print('The sum of expenses for ', cmd_params, ' is ', str(sum_category))
    else:
        print('No expenses for that category has been found')


def test_total_sum_of_category():
    expenses_list = []
    test_init(expenses_list)
    try:
        total_sum_of_category(expenses_list, 'food')
    except ValueError:
        assert False
    print(expenses_list)


#test_total_sum_of_category()


def max_day_of_expense(expenses_list, cmd_params):
    """
    Calculates the sum of expenses for each day
    :param expenses_list: the list with all expenses
    :param cmd_params: does not count
    :return: Prints the day with the biggest sum
    """
    sum_of_each_day = []
    for i in range(1, 31):
        sum_of_each_day.append(0)
    i = 0
    while i <= len(expenses_list) - 1:
        sum_of_each_day[get_day(expenses_list[i])] += get_sum(expenses_list[i])
        i += 1
    aux = max(sum_of_each_day)
    max_sum = sum_of_each_day.index(aux)
    print('The day with the max sum of expenses is day: ', str(max_sum))


def test_max_day_of_expense():
    expenses_list = []
    test_init(expenses_list)
    try:
        max_day_of_expense(expenses_list, None)
    except ValueError:
        assert False


#test_max_day_of_expense()


def sort_by_day(expenses_list, cmd_params):
    """
    Prints in ascending order the sum of each day
    :param expenses_list: the list with all expenses
    :param cmd_params: does not count
    :return: The days printed in ascending order
    """
    sum_of_each_day = []
    for i in range(1, 31):
        sum_of_each_day.append(0)
    i = 0
    while i <= len(expenses_list) - 1:
        sum_of_each_day[get_day(expenses_list[i])] += get_sum(expenses_list[i])
        i += 1
    sum_of_each_day_non_sorted = sum_of_each_day.copy()
    sum_of_each_day.sort()

    for x in sum_of_each_day:
        if x != 0:
            index = sum_of_each_day_non_sorted.index(x)
            sum_of_each_day_non_sorted[index] = 0
            print('day:', str(index), ' sum of expenses: ', str(x))


def test_sort_by_day():
    expenses_list = []
    test_init(expenses_list)
    try:
        sort_by_day(expenses_list, None)
    except ValueError:
        assert False


#test_sort_by_day()


def sort_by_category(expenses_list, cmd_params):
    """
    display the daily expenses for category in ascending order by amount of money spent
    :param expenses_list: the list with all expenses
    :param cmd_params: the category
    :return: The daily expenses for that category are printed in ascending order
    """
    just_category = []
    for expense in expenses_list:
        if get_category(expense) == cmd_params:
            just_category.append(expense)
    just_category.sort(key=get_sum)
    for expense in just_category:
        print(to_str(expense))



def test_sort_by_category():
    expenses_list = []
    test_init(expenses_list)
    try:
        sort_by_category(expenses_list, 'food')
    except ValueError:
        assert False


#test_sort_by_category()


def filter_expenses_command_ui(expenses_list, cmd_params):
    """
    Keeps only some specific elements in the list
    :param expenses_list: the list with all the expenses
    :param cmd_params: the criteria for filter
    :return: expenses that did not fit in the criteria were removed
    """
    tokens = cmd_params.split(' ')
    for i in range(len(tokens)):
        tokens[i] = tokens[i].strip()

    if len(tokens) == 1:
        filter_category(expenses_list, cmd_params)
    else:
        filter_with_value(expenses_list, tokens)


def undo_command_ui(history):
    """
    Undos the last operation
    :param history: the list before the last action
    :return: the previous list
    """
    last_list = []
    if len(history) > 0:
        last_list = history.pop(-1)
        return last_list
    else:
        raise ValueError('Can not undo anymore')


def test_undo_command_ui():
    expenses_list = []
    test_init(expenses_list)
    params = '70 others'
    add_expense(expenses_list, params)
    try:
        undo_command_ui(expenses_list)
    except ValueError:
        assert False
    display_all_expenses_list(expenses_list)


#test_undo_command_ui()
