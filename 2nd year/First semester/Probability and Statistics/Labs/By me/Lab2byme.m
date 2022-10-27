#ex 2
n = input("Give n: ")
p = input("Give p: ")
x = 0:n
#plot the graph 

#binofpdf to find the pdf of the random variable x

#n - nr of trials, p - the probability of success
plot(x, binopdf(x, n, p), '*r') 
hold on
stairs(x, binocdf(x, n, p)) 
hold off

#Application
n = 3
p = 0.5
x = 0:n
plot(x, binopdf(x, n, p), '*r') 
