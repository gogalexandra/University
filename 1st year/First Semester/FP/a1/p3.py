#
# Implement the program to solve the problem statement from the third set here
#

#this function calculates the sum of the dividers except the number itself
def sum_of_dividers(x): 
    sum = 0
    div = 1
    while div < x:
        if x % div == 0:
            sum +=div
        div +=1
    return sum

#this function verifies if a number is perfect using the function above
def is_perfect(x): 
    return sum_of_dividers(x) == x

def search_number(n):
    perfect_number = False
    #using a for loop that begins with the input number we are searching the largest perfect number
    for i in range (n-1,1,-1): 
        if is_perfect(i):
            perfect_number = True
            #when we find a perfect number, it will be shown on the output and the for loop will stop
            print ("The largest perfect number smaller than the input number is " + str(i) ) 
            break
    #if we didn't find a perfect number, on the ootput will apeare a message saying so
    if perfect_number == False : 

        print ("There is no perfect number")
    
def main() :
    n = input ("Enter a number: ")
    n = int(n)
    search_number(n)
main ()
