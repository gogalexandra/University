def createString(list):
    newList = []
    for elem in list:
        stringList = ''
        s = str(elem)
        length = len(s)
        i = 0
        while (i < length):
            if s[i] == '-':
                stringList += 'minus'
            elif s[i] == '.':
                stringList += 'virgula'
            elif s[i] == '1':
                stringList += 'unu'
            elif s[i] == '2':
                stringList += 'doi'
            elif s[i] == '3':
                stringList += 'trei'
            elif s[i] == '4':
                stringList += 'patru'
            elif s[i] == '5':
                stringList += 'cinci'
            elif s[i] == '6':
                stringList += 'sase'
            elif s[i] == '7':
                stringList += 'sapte'
            elif s[i] == '8':
                stringList += 'opt'
            elif s[i] == '9':
                stringList += 'noua'
            else:
                stringList += 'zero'
            i += 1
        newList.append(stringList)
    return newList

def f4(list):
    newList = []
    for elem in list:
        s = str(elem)
        if s == '-':
            s = s[1:]
        parts = s.split('.')
        part_intreaga = int(parts[0])
        part_frac = int(parts[1])
        if part_frac % part_intreaga == 0:
            newList.append(elem)
    return newList




list = [1.5, -3.3, 9.8, 14.45]
list1 = createString(list)
list2 = f4(list)
print(list1)
print(list2)