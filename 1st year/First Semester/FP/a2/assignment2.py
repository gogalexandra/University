def sequence_prop1(number_list):
    """
    Searches for the longest seqeunce in which the sum of elements is 10+10i
    param :number_list :the list of numbers
    return : the longest sequence appeares on the output
    """
    sum_of_reals = 0
    sum_of_imaginaries = 0
    current_length = 0
    max_length = 0
    index_of_the_last_number_of_max_sequence = 0
    first_of_sequence = 0
    i = 0
    while i <= len(number_list)-1:
        sum_of_reals += get_real_part(number_list[i])
        sum_of_imaginaries += get_imaginary_part(number_list[i])
        current_length += 1
        if sum_of_imaginaries > 10 or sum_of_reals >10:
            first_of_sequence += 1
            i = first_of_sequence
            sum_of_reals = get_real_part(number_list[i])
            sum_of_imaginaries = get_imaginary_part(number_list[i])
            current_length = 1
        elif sum_of_reals == 10 and sum_of_imaginaries == 10:
            if current_length > max_length:
                max_length = current_length
                index_of_the_last_number_of_max_sequence = i
        i += 1

    show_list( number_list [index_of_the_last_number_of_max_sequence-max_length + 1 : index_of_the_last_number_of_max_sequence + 1 ])
    
    
def sequence_prop2(number_list):   
    """
    Searches for the longest seqeunce in which the real parts are in form of a mountain
    param :number_list :the list of numbers
    return : the longest sequence appeares on the output
    """
    top_of_mountain = False
    max_sequence_length = 0
    current_sequence_length = 1
    index_of_last_item_of_max_sequence = 0
    i = 1
    while i <= len(number_list)-1 :
        if top_of_mountain == False :
    
            current_sequence_length +=1

            if get_real_part(number_list[i]) < get_real_part(number_list[i-1]) :
                top_of_mountain = True

        else :
            if get_real_part(number_list[i]) > get_real_part(number_list[i-1]) :

                if current_sequence_length > max_sequence_length :
                    index_of_last_item_of_max_sequence = i - 1 
                    max_sequence_length = current_sequence_length
                    top_of_mountain = False
                    current_sequence_length = 2

            else :
                current_sequence_length +=1
    
        i+=1
        
    if top_of_mountain  :
        if current_sequence_length > max_sequence_length:
            max_sequence_length = current_sequence_length
            index_of_last_item_of_max_sequence = len(number_list) -1


    show_list(number_list[index_of_last_item_of_max_sequence-max_sequence_length + 1 : index_of_last_item_of_max_sequence + 1 ])
    print(max_sequence_length, index_of_last_item_of_max_sequence)

    
        

def creating_a_list (number_list):
    """
    Adds 10 numbers to the list
    :param number_list :The list with numbers
    :return : The list with 10 elements in it
    """
    number_list.append(create_number(1,1))
    number_list.append(create_number(2,2))
    number_list.append(create_number(3,8))
    number_list.append(create_number(2,5))
    number_list.append(create_number(1,-11))
    number_list.append(create_number(-1,-1))
    number_list.append(create_number(3,3))
    number_list.append(create_number(2,5))
    number_list.append(create_number(1,3))
    number_list.append(create_number(-1,0))

def get_real_part(number):
    """
    :param :a number from the list
    :return :the real part of the number
    """
    return number['real_part'] 

def get_imaginary_part(number):
    """
    :param :a number from the list
    :return :the imginary part of the number
    """
    return number['imaginary_part'] 

def create_number(real_part,imaginary_part):
    """
    Creates a new number
    :param real_part: The real part of the number
           imaginari_part: The imaginary part of the number
    :return : new number
    """
    return {'real_part' : real_part, 'imaginary_part': imaginary_part}

def read_number():
    """
    Reads a number from the console
    Returns a new number 
    """
    real_part = int (input('Enter the real part of the number: '))
    imaginary_part = int (input('Enter the imaginari part of the number: '))
    return create_number (real_part,imaginary_part)

def add_number(number_list):
    """
    Adds a new number to the list of numbers
    :param number_list: The list of numbers
    :return :-
    """
    number = read_number() 
    number_list.append(number)

def convert_to_str(number,x): 
    """
    Converts the numbers to string
    :param number: A number from the list
    :return : the number in a string form
    """
    if negative_nr(number) == False:
        return 'z' + str(x) +' = ' + str( get_real_part(number) ).rjust(2) + '+ ' + str( get_imaginary_part(number) ) + 'i'
    else:
        return 'z'+ str(x) +' = ' + str( get_real_part(number) ).rjust(2) + '- ' + str(abs( get_imaginary_part(number)) )  + 'i'

def negative_nr(number) :
    """
    Checks if the imaginary part is negative
    :param number: A number from the list of numbers
    :return : a true or false message
    """
    if get_imaginary_part(number) < 0 :
        return True
    else:
        return False

def show_list(number_list):
    """
    Prints the list
    :param number_list: The list with nnumbers
    :return : The list is shown on output
    """
    x = 0 
    for number in number_list:
        print (convert_to_str(number,x))
        x+=1

def print_menu():
    print('0.Exit')
    print('1.Show the list of numbers')
    print('2.Add a complex number')
    print('3.Show the longest sequence of numbers of which sum of elements are 10 + 10i')
    print('4.Show the longest sequence of numbers of which real part is in the form of a mountain')

def start():
    number_list = []
    creating_a_list(number_list)
    exit_now = False
    set_of_commands = {'1': show_list, '2':add_number, '3':sequence_prop1, '4':sequence_prop2}
    while not exit_now:
        print_menu()
        command = input('Enter a command : ')
        if command == '0' :
            exit_now = True
            print ("Bye!")
        elif command not in set_of_commands:
            print('Not a valid command')
        else :
            x = set_of_commands[command]
            x(number_list)            

start()