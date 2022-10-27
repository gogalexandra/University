p = input("Give p (0.05,0.95): ") 
n = input("Give n (large values): ") 
x = 0:n

plot(x, binopdf(x, n, p), '*r') 
hold on

mu = n*p
sigma = sqrt(n*p*(1-p))

plot(x, normpdf(x, mu, sigma), '*r') 
hold on
