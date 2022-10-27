#Application
n = 3
p = 0.5
x = 0:n

#a
plot(x, binopdf(x, n, p), '*r') 
hold on

#b
stairs(binocdf(x, n , p))
hold on
printf("Cdf: \n")
# print cdf for all values : fx(x) = P(X<=x) (sum of pdf for all values smaller 
#or equal to the parameter)
printf("Fx(0)= %f \n", binocdf(0, n, p))
printf("Fx(1)= %f \n", binocdf(1, n, p))
printf("Fx(2)= %f \n", binocdf(2, n, p))
printf("Fx(3)= %f \n", binocdf(3, n, p))

#c
printf("\nP(X=0): %f \n" , binopdf(0, n, p))
printf("P(X!=1): %f \n" , binopdf(0, n, p) + binopdf(2, n, p) + binopdf(3, n, p))
printf("P(X!=1): %f \n" , binocdf(3, n, p) - binopdf(1, n ,p))

#d
printf("\nP(X<=2): %f \n", binopdf(0, n, p) + binopdf(1, n, p) + binopdf(2, n, p))
printf("P(X<=2): %f \n", binocdf(2, n, p))
printf("P(X<2): %f \n", binopdf(0, n, p) + binopdf(1, n, p))

#e
printf("\nP(X>=1): %f \n", binopdf(1, n, p) + binopdf(2, n, p) + binopdf(3, n, p))
printf("P(X>1): %f \n", binopdf(2, n, p) + binopdf(3, n, p))
