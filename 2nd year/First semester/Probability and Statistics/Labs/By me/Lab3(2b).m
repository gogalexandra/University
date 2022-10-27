p = input("Give p <= 0.05: ") 
n = input("Give n >= 30: ") 
x = 0:n

plot(x, binopdf(x, n, p), '*r') 
hold on

lamba = n*p
sigma = sqrt(n*p*(1-p))

plot(x, poisspdf(x, lamba), '*r') 
hold on