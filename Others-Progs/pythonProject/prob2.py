# 2. In a company’s hierarchy, some employees manage other employees, who can in turn manage other employees. The
# input (as a string) format for the hierarchy is (name, sub-hierarchy_1, sub-hierarchy_2, … , sub-hierarchy_n),
# with every sub-hierarchy having the same format, recursively. Your task is to write code that parses the hierarchy,
# removes employees that are marked as “Unavailable” along with all the employees they manage and prints the
# resulting hierarchy.
# Input: (John(Jasmine(Jay)(Unavailable))(Unavailable(Jack(Jeremy)))(Johanna))
# Output: (John(Jasmine(Jay))(Johanna)) Note: The input must be read as a string

def parseHierarchy(sir):
    # parsing nested parantheses and dividing them by levels

    result = []
    stack = []
    for i, c in enumerate(sir):
        if c == '(':
            stack.append(i)
        elif c == ')' and stack:
            start = stack.pop()
            cut = sir[start + 1: i].find('(')
            if cut == -1:
                result.append([len(stack), sir[start + 1: i]])
            else:
                result.append([len(stack), sir[start + 1: start + cut + 1]])
    return result


def removeEmployees(sir):
    # removes the unwanted employees
    dividedByLevels = parseHierarchy(sir)
    index = len(dividedByLevels) - 1
    while index > 0:
        if dividedByLevels[index][1] == 'Unavailable':
            if dividedByLevels[index][0] == 0:
                return "No employees left"
            else:
                level = dividedByLevels[index][0]
                dividedByLevels.pop(index)
                while dividedByLevels[index - 1][0] > level:
                    index -= 1
                    dividedByLevels.pop(index)
        index -= 1
    return recreateFormat(dividedByLevels)


def recreateFormat(list):
    # this function recreates the array
    result = ''
    index = len(list) - 1
    while index >= 0:
        result += '(' + list[index][1]
        if list[index][0] >= list[index - 1][0]:
            result += ')'
        index -= 1
    return result + ')'
