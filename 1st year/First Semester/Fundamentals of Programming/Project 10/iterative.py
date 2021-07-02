

def backIter():
    x = [0]  # candidate solution
    while len(x) > 0:
        choosed = False
        while (not choosed) and l.index(x[-1]) < len(l) - 1:
            x[-1] = l[l.index(x[-1]) + 1]  # increase the last component
            choosed = consistent(x)
        if choosed:
            if solution(x):
                solutionFound(x)
            x.append(0)  # expand candidate solution
        else:
            x.pop()  # go back one component


def consistent(possible_sol):
    return len(possible_sol) < number_of_elements


def solution(s):
    evaluated_expression = elements[0]
    if not len(s) == number_of_elements - 1:
        return False
    for i in range(number_of_elements - 1):
        if s[i] == "-":
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


l = [0, "+", "-"]

backIter()
