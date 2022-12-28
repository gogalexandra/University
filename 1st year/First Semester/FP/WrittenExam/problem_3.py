# Recursive function that uses the divide and conquer method to calculate the sum of the even numbers found
# on even positions in a list.


# DIVIDE AND CONQUER ITERATIVE
def even_pos_even_numbers_sum_iter(list_of_numbers):
    total = 0
    stack = []
    stack.append(list_of_numbers)
    while stack:
        last_elem = stack.pop()
        size = len(last_elem)
        if size > 1:
            if size == len(list_of_numbers):
                even = 2
            else:
                even = 1
            stack.append(last_elem[size // 2::even])
            stack.append(last_elem[:size // 2:even])
        else:
            if last_elem and last_elem[0] % 2 == 0:
                total += last_elem[0]

    return total


# print(even_pos_even_numbers_sum_iter([4, 8, 3, 7, 3, 4, 7]))


# DIVIDE AND CONQUER BUT RECURSIVE
def dcsum(l, r, list):
    """
    @param l: left boundary of the list
    @param r: right boundary of the list
    @param list: list of numbers
    @return: the product of the required numbers, 0 if no good numbers found
    """
    # if len(list) < 3
    # 1 number -> return 1 or itself, depending on eveness
    # 2 numbers -> return from even position or 1
    if l >= r:
        # base case
        # basically if we get to a list formed by a single element we check
        # if the requirements are met and add it to the product
        if r % 2 == 0 and list[r] % 2 == 0:
            return list[r]
        else:
            return 0
    else:
        mid = (l + r) // 2
        result = dcsum(mid + 1, r, list) + dcsum(l, mid, list)
        # divide the list into two parts in the middle, add to the product the results gained from the respective parts
        return result


# print(dcsum(0, len([2, 2, 4, 5, 6, 4, 13, 4, 10]) - 1, [2, 2, 4, 5, 6, 4, 13, 4, 10]))

# Recursive function that uses the divide and conquer method to calculate the sum of the numbers found
# on prime positions in a list.

def is_prime(nr):
    d = 2
    if nr == 0 or nr == 1:
        return False
    while d <= nr // 2:
        if nr % d == 0:
            return False
        d += 1
    if d >= nr // 2:
        return True


def sum_nr_from_even_pos(l, r, list):
    if l >= r:
        # base case
        # basically if we get to a list formed by a single element we check
        # if the requirements are met and add it to the product
        if is_prime(r):
            return list[r]
        else:
            return 0
    else:
        mid = (l + r) // 2
        result = sum_nr_from_even_pos(mid + 1, r, list) + sum_nr_from_even_pos(l, mid, list)
        # divide the list into two parts in the middle, add to the product the results gained from the respective parts
        return result


# print(sum_nr_from_even_pos(0, len([5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]) - 1,
#                           [5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]))


# Recursive function that calculates the greatest common divisor of the numbers found on odd position of a list,
# using divide and conquer method

def gcd(a, b):
    if a is None:
        a = b
    elif b is None:
        b = a
    while b:
        a, b = b, a % b

    return a


def gcd_numbers_on_odd_pos(l, r, list_of_numbers):
    if l == r:
        if l % 2 == 1:
            return list_of_numbers[l]
    else:
        middle = (l + r) // 2
        right = gcd_numbers_on_odd_pos(middle + 1, r, list_of_numbers)
        left = gcd_numbers_on_odd_pos(l, middle, list_of_numbers)
        result = gcd(right, left)

        return result


# print(gcd_numbers_on_odd_pos(0, len([2, 35, 5, 65, 10, 20, 5]) - 1, [2, 1, 5, 65, 10, 20, 5]))


# Recursive function which returns the largest prime number found on an even position of a list, using divide and
# conquer method

def largest_prime_number(l, r, list_of_numbers):
    if l == r:
        if r % 2 == 0 and is_prime(list_of_numbers[r]):
            return list_of_numbers[r]
        else:
            return 0
    else:
        middle = (l + r) // 2
        maxi = max(largest_prime_number(l, middle, list_of_numbers),
                   largest_prime_number(middle + 1, r, list_of_numbers))
        return None if r - l == len(list_of_numbers) - 1 and maxi == 0 else maxi


print(largest_prime_number(0, len([21, 3, 50]) - 1, [21, 3, 50]))


# Recursive function which returns the product of prime number found on multiple of 3 position of a list,
# using divide and conquer method

def product_of_nr(l, r, list_of_numbers):
    if l == r:
        if r % 3 == 0 and is_prime(list_of_numbers[r]):
            return list_of_numbers[r]
        else:
            return 1
    else:
        middle = (l + r) // 2
        # left = product_of_nr(l, middle, list_of_numbers)
        # right = product_of_nr(middle + 1, r, list_of_numbers)
        product = product_of_nr(l, middle, list_of_numbers) * product_of_nr(middle + 1, r, list_of_numbers)
        return None if r - l == len(list_of_numbers) and product == 1 else product


print(product_of_nr(0, len([0, 12, 2, 30, 4, 5, 26, 7, 8, 9, 10]), [0, 12, 2, 30, 4, 5, 2, 7, 8, 13, 10]))
