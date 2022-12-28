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