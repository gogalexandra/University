def filter(arr, fnc):
    filter_list = []
    for item in arr:
        if fnc(item):
            filter_list.append(item)
    return filter_list
