#
# Write the implementation for A3 in this file
#

#
# domain section is here (domain = numbers, transactions, expenses, etc.)
# getters / setters
# No print or input statements in this section
# Specification for all non-trivial functions (trivial usually means a one-liner)
from datetime import datetime

def create_expense(expense_day, expense_sum, expense_category):
    """
        Creates a new expense with the given informations 
        :param expense_day: the day of the expense
        :param expense_sum: the sum of the expense
        :param expense_category: the category of the expense
        :return: The new expense, or None if it could not be created
    """

    if expense_day < 1 or expense_day> 30 or expense_sum < 0 :
        raise ValueError('Expense could not be created')

    if expense_category not in ['housekeeping', 'food', 'transport','clothing',' internet', 'others' ] :
        raise ValueError('Expense could not be created')

    return {'day': expense_day, 'sum':expense_sum , 'category': expense_category}

def get_day(expense):
    return expense['day']

def get_sum(expense):
    return expense['sum']

def get_category(expense):
    return expense['category']

# Functionalities section (functions that implement required features)

# Specification for all non-trivial functions (trivial usually means a one-liner)
# Each function does one thing only
# Functions communicate using input parameters and their return values

def add_expense(expenses_list, expense):
    """
        adds a new expense to the list
        params: expense_list is the list of the expenses
        params expense is the new expense thats being added to the list
    """
    expenses_list.append(expense)


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


def  to_str(expense) :
    return 'day: ' + str(get_day(expense)) + '   sum: ' + str(get_sum(expense)).rjust(5) + '   category: ' + str(get_category(expense)).rjust(2)


# UI section
# (all functions that have input or print statements, or that CALL functions with print / input are  here).
# Ideally, this section should not contain any calculations relevant to program functionalities


def remove_expense_command_ui (expenses_list,cmd_params):
    """
        Removes a hole day, a hole type of category or more than one day 
        expenses_list : is the list with all the expenses
        cmd_params: are the parameters of what should be removed
    
    """
    tokens = cmd_params.split(' ')
    for i in range(len(tokens)):
        tokens[i] = tokens[i].strip()


    if len(tokens) == 1:
        if tokens[0].isalpha() ==  True:
            remove_just_a_category(expenses_list,tokens[0])
        else :
            remove_a_full_day(expenses_list,tokens[0])
    else :
        remove_days(expenses_list,tokens)


def remove_days(expenses_list,tokens ):
    """
        This function removes more than one day 
        expenses_list : is the list with all expenses
        tokens: are the days that need to be removed
    """
    i = 0 
    while i <= len(expenses_list) -1 :
        if int(tokens[0]) <= get_day(expenses_list[i]) and get_day(expenses_list[i]) <= int(tokens[2]) :
            expenses_list.remove(expenses_list[i])
        else :
            i+=1


def remove_a_full_day(expenses_list,tokens) :
    """
        This function removes just one day
        expenses_list : is the list with all expenses
        tokens: is the day that needs to be removed
    """
    i = 0
    while i <= len(expenses_list) -1 :
        if str(get_day(expenses_list[i])) == tokens:
            expenses_list.remove(expenses_list[i])
        else :
            i+=1


def remove_just_a_category(expenses_list, tokens):
    """
        This function removes a whole category
        expenses_list : is the list with all expenses
        tokens: is the category that needs to be removed
    """
    i = 0
    while i <= len(expenses_list) -1:
        if str(get_category(expenses_list[i])) == str(tokens):
            expenses_list.remove(expenses_list[i])
        else:
            i+=1
  


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


def list_expense_command_ui(expenses_list, cmd_params) :
    """
        This function prints the expenses that the user wants
        expenses_list : the list with all the expenses
        cmd_params : the parameters of the expenses that need to be printed
    """
    if len(cmd_params) == 0:
        display_all_expenses_list(expenses_list)
    else:
        tokens = cmd_params.split(' ')
        if len(tokens) == 1 : 
            display_only_one_category(expenses_list,cmd_params)
        else :
            display_specific_sums(expenses_list, tokens)
    

def display_all_expenses_list(expenses_list):
    """
        This function prints the whole list of expenses
        expenses_list: is the list with all expenses
    """
    ok = False
    expenses_list.sort(key = get_day)
    for expense in expenses_list:
        print(to_str(expense)) 
        ok = True
    if ok == False:
        print('There is no such expense')


def display_only_one_category(expenses_list,cmd_params):
    """
        This function prints only one of the category from the expenses types
        expenses_list: the list with all expenses
        cmd_params: the category that needs to be printed
    """
    ok = False
    for expense in expenses_list:
        if get_category(expense) == cmd_params :
            print (to_str(expense))
            ok =  True
    if ok == False:
        print('There is no such expense')


def display_specific_sums (expenses_list,tokens):
    """
        this function prints only the expenses that have a specific sum
        expenses_list : the list with all expenses
        tokens: the parameters of the expenses that need to be printed related to the sum
    """
    ok =  False
    for expense in expenses_list:
        if get_category(expense) == tokens[0]:
            if tokens[1] == '=' :
                if get_sum(expense) == int (tokens[2]) :
                    print(to_str(expense)) 
                    ok = True
                
            else :
                if tokens[1] == '>' :
                    if get_sum(expense) > int (tokens[2]) :
                        print(to_str(expense)) 
                        ok = True
                        
                else:
                    if get_sum(expense) < int (tokens[2]) :
                        print(to_str(expense)) 
                        ok = True
    if ok == False:
        print('There is no such expense')                   


def start_command_ui():

    expenses_list = []
    test_init (expenses_list)
    command_dict = {'add': add_expense_command_ui, 'insert': add_expense_command_ui, 'list': list_expense_command_ui,
                    'remove': remove_expense_command_ui}
    done = False
    while not done:

        command = input('command: ')
        today = datetime.now().day
        try:
            
            cmd_word, cmd_params = split_command(command)
            
            if cmd_word in command_dict:
                if cmd_word == 'add' :
                    cmd_params = str(today)+ ' ' + cmd_params

                command_dict[cmd_word](expenses_list, cmd_params)
            elif cmd_word == 'exit':
                done = True
            else:
                print('bad command')
        except ValueError as ve:
            print(str(ve))



# Test functions go here
#
# Test functions:
#   - no print / input
#   - great friends with assert

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


#test_split_command()

def test_add_expense():
    expense_list = []
    test_init(expense_list)
    # Test that we can add a valid expense
    e = create_expense(3,250,'food')
    add_expense(expense_list, e)

    try:
        add_expense(expense_list, e)
        assert False  # this means the exception was NOT raised
    except ValueError:
        assert True
#test_add_expense()

start_command_ui()
