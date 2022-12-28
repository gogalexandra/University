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