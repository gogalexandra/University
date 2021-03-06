#
# Implement the program to solve the problem statement from the first set here
#
from math import *

#this function counts the number of dividers 
def is_number_prime(x): 
    number_of_dividers = 0
    divider = 2
    if (x==1) or (x==0) :
        return False
    #to be more effective we start from 2 to the squared root of the number     
    while divider <= int(sqrt(x)) : 
        if x % divider == 0: 
            number_of_dividers +=1
        divider +=1
    return number_of_dividers == 0

def search_prime(x):
    #usign the while loop we are searching for a prime number and it is gonna stop when it finds one
    while True: 
        x += 1
        if is_number_prime(x) == True :
            print ("The prime number greater than the given one is " + str(x))
            break

def main():
    n = input ("Enter a number ")
    n = int(n)
    search_prime(n)
 
main()
#
# Implement the program to solve the problem statement from the second set here
#

#using this function we will create the invers of a specific number
def palindrom(x):
    #this is the variable in which we gradually building the invers 
    aux = 0 
    while x > 0 :
        aux = aux *10 + x % 10
        x //=10
    return aux
def main():
    n = input("Enter a number ")
    n = int(n)
    print (palindrom(n))
main()
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
