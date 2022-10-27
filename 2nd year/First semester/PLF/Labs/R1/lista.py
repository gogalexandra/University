class Nod:
    def __init__(self, e):
        self.e = e
        self.urm = None


class Lista:
    def __init__(self):
        self.prim = None


'''
crearea unei liste din valori citite pana la 0
'''


def creareLista():
    lista = Lista()
    lista.prim = creareLista_rec()
    return lista


def creareLista_rec():
    x = int(input("x="))
    if x == 0:
        return None
    else:
        nod = Nod(x)
        nod.urm = creareLista_rec()
        return nod


'''
tiparirea elementelor unei liste
'''


def tipar(lista):
    tipar_rec(lista.prim)


def tipar_rec(nod):
    if nod != None:
        print(nod.e)
        tipar_rec(nod.urm)


'''
program pentru test
'''


def addEndList_rec(nod, elem):
    if nod is None:
        return Nod(elem)
    else:
        nod.urm = addEndList_rec(nod.urm, elem)
    return nod

def addEndList(list, elem):
    nod = addEndList_rec(list.prim, elem)
    return nod


def concateLists_rec(nod1, nod2):
    if nod1 is None:
        return nod2
    else:
        nod1.urm = concateLists_rec(nod1.urm, nod2)
    return nod1


def concateLists(nod, nod2):
    return concateLists_rec(nod, nod2)


def main():
    print("Give list(end in 0): ")
    list = creareLista()
    tipar(list)
    nr = int(input("Give number to add at the end: "))
    nod = addEndList(list, nr)
    tipar_rec(nod)
    print("Now give another list to add to the existing one: ")
    list2 = creareLista()
    nod2 = concateLists(nod, list2.prim)
    print("The new list: ")
    tipar_rec(nod2)


main()
