n = input("Give n:")
p = input("Give p:")
x = 0:n
plot(x, binopdf(x, n, p), '*r') 
hold on
stairs(x, binocdf(x, n, p)) 
hold off


