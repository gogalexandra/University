import prob1
import prob2


def print_menu():
    print('0.Exit')
    print('1.Find most popular k scores')
    print('2.Remove unavailable employees')


def start():
    while True:
        print_menu()
        command = input('Enter a command: ')
        if command == '0':
            print('Bye!')
            break
        elif command == '1':
            n = int(input('How many elements has the list? '))
            listOfElems = []
            for number in range(0, n):
                nr = int(input('Give number: '))
                listOfElems.append(nr)
            k = int(input('How many elements you want printed? '))
            if n >= k:
                prob1.mostPopularScores(n, k, listOfElems)
            else:
                print('!!!Given values are not correct!!!')

        elif command == '2':
            print('Format: (John(Jasmine(Jay)(Unavailable))(Unavailable(Jack(Jeremy)))(Johanna))')
            hierarchy = input('Give a company’s hierarchy: ')
            print(prob2.removeEmployees(hierarchy))

        else:
            print('Not a valid command')


start()
