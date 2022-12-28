# 1. Given a list of n natural numbers ranging between 1 and 1,000,000,000, find the most popular k scores
# in the descending order of their frequency.Input: n = 11, k = 3, numbers = [6, 5, 2, 6, 6, 2, 1, 7, 3, 3, 3]
# Output: [6, 3, 2]


def partition(arr, low, high):
    i = (low - 1)
    pivot = arr[high][1]

    for j in range(low, high):
        if arr[j][1] >= pivot:
            i = i + 1
            arr[i], arr[j] = arr[j], arr[i]

    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return (i + 1)


def quickSort(arr, low, high):
    if len(arr) == 1:
        return arr
    if low < high:
        res = partition(arr, low, high)
        quickSort(arr, low, res - 1)
        quickSort(arr, res + 1, high)


def mostPopularScores(nrElems, nrElemsToPrint, listOfElems):

    listOfOccurences = {}
    for index in range(nrElems):
        if listOfElems[index] in listOfOccurences:
            listOfOccurences[listOfElems[index]] += 1
        else:
            listOfOccurences[listOfElems[index]] = 1
    # listOfOccurrences is a list where the value from pos i is equal to the number of occurrences of the number i in the
    # array (increasing the value with 1 if it appeared by then or initializing it with 1 if the first time it occurred)
    a = [0] * (len(listOfOccurences))
    # a is a new array where the value represents a pair (number, nr of occurrences of number)
    index = 0
    for i in listOfOccurences:
        a[index] = [i, listOfOccurences[i]]
        index += 1
    # a needs to be sorted in descending order
    # i've used a sorting function(quick sort- for being the most efficient) to avoid using predefined functions
    quickSort(a, 0, len(a) - 1)
    print("Top " + nrElemsToPrint + " numbers with the most occurrences are: ")
    for i in range(nrElemsToPrint):
        print(a[i][0], end=" ")
    print("\n")
