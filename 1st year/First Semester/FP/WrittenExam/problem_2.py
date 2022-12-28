def f(a, b):
    if a != 1:
        f(a-1, b-1)
        print(a, b)
        f(a - 1, b - 1)
    else:
        print(a, b)

f(3,2)