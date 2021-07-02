"""
Domain file includes code for entity management
entity = number, transaction, expense etc.
"""


def create_expense(expense_day, expense_sum, expense_category):
    """
        Creates a new expense with the given informations
        :param expense_day: the day of the expense
        :param expense_sum: the sum of the expense
        :param expense_category: the category of the expense
        :return: The new expense, or None if it could not be created
    """

    if expense_day < 1 or expense_day > 30 or expense_sum < 0:
        raise ValueError('Expense could not be created')

    if expense_category not in ['housekeeping', 'food', 'transport', 'clothing', ' internet', 'others']:
        raise ValueError('Expense could not be created')

    return {'day': expense_day, 'sum': expense_sum, 'category': expense_category}


def get_day(expense):
    return expense['day']


def get_sum(expense):
    return expense['sum']


def get_category(expense):
    return expense['category']
