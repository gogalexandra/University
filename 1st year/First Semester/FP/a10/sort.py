def gnomeSort(arr, cmp_function):
    index = 0
    while index < len(arr):
        if index == 0:
            index = index + 1
        if cmp_function(arr[index - 1], arr[index]):
            aux = arr[index]
            arr[index] = arr[index - 1]
            arr[index - 1] = aux
            index = index - 1
        else:
            index = index + 1

    return arr
