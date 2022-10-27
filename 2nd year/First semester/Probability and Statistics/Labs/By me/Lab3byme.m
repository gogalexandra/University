mu = input('Give mu: ')
sigma = input('Give sigma: ')
alpha = input('Give alpha: ')
beta = input('Give beta: ')

#Normal dis
printf("\nNormal distribution \n")
#a
printf("\nP(X<=0): %f \n" , normcdf(0, mu, sigma))
printf("P(X>=0): %f \n" , 1 - normcdf(0, mu, sigma))

#b
printf("\nP(-1<=X<=1): %f \n" , normcdf(1, mu, sigma) - normcdf(-1, mu, sigma))
printf("P(X<=-1 or X>=1): %f \n" ,1 - (normcdf(1, mu, sigma) - normcdf(-1, mu, sigma)))

#c
printf("\nOuantile of order alpha: %f \n" , norminv(alpha, mu, sigma))

#d
printf("\nOuantile of order beta: %f \n" , norminv(1 - beta, mu, sigma))

#Student dis
printf("\nStudent distribution \n")
n = input('Give n: ')

#a
printf("\nP(X<=0): %f \n" , tcdf(0, n))
printf("\nP(X>=0): %f \n" , 1 - tcdf(0, n))

#b
printf("\nP(-1<=X<=1): %f \n" , tcdf(1, n) - tcdf(-1, n))
printf("\nP(X<=-1 or X>=1): %f \n" ,1 - (tcdf(1, n) - tcdf(-1, n)))

#c
printf("\nOuantile of order alpha: %f \n" , tinv(alpha,n))

#d
printf("\nOuantile of order beta: %f \n" , tinv(1 - beta, n))


#Chisquare dis
printf("\nChisquare distribution \n")
n = input('Give n: ')


#a
printf("\nP(X<=0): %f \n" , chi2cdf(0, n))
printf("\nP(X>=0): %f \n" , 1 - chi2cdf(0, n))

#b
printf("\nP(-1<=X<=1): %f \n" , chi2cdf(1, n) - chi2cdf(-1, n))
printf("\nP(X<=-1 or X>=1): %f \n" ,1 - (chi2cdf(1, n) - chi2cdf(-1, n)))

#c
printf("\nOuantile of order alpha: %f \n" , chi2inv(alpha, n))

#d
printf("\nOuantile of order beta: %f \n" , chi2inv(1 - beta, n))

#Fischer dis
printf("\nF distribution \n")
m = input('Give m: ')
n = input('Give n: ')


#a
printf("\nP(X<=0): %f \n" , fcdf(0, m, n))
printf("\nP(X>=0): %f \n" , 1 - fcdf(0, m, n))

#b
printf("\nP(-1<=X<=1): %f \n" , fcdf(1, m, n) - fcdf(-1, m, n))
printf("\nP(X<=-1 or X>=1): %f \n" ,1 - (fcdf(1, m, n) - fcdf(-1, m, n)))

#c
printf("\nOuantile of order alpha: %f \n" , finv(alpha, m, n))

#d
printf("\nOuantile of order beta: %f \n" , finv(1 - beta, m, n))