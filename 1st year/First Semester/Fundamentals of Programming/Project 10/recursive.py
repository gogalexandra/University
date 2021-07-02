def backtracking_recursive(x):
    for operator in operators:
        x.append(operator)
        if consistent(x):
            if solution(x):
                solutionFound(x)
            backtracking_recursive(x[:])
        x.pop()


def consistent(possible_sol):
    return len(possible_sol) < number_of_elements


def solution(possible_sol):
    evaluated_expression = elements[0]
    if not len(possible_sol) == number_of_elements - 1:
        return False
    for i in range(number_of_elements - 1):
        if possible_sol[i] == "-":
            evaluated_expression -= elements[i + 1]
        else:
            evaluated_expression += elements[i + 1]
    return evaluated_expression > 0


def solutionFound(s):
    print(s)


number_of_elements = int(input("Number of elements: "))
elements = []
for i in range(number_of_elements):
    elem = int(input("Give element: "))
    elements.append(elem)

operators = ["+", "-"]
backtracking_recursive([])
