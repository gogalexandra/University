clear;
p = input("p: ");
for n=100:110
  x=0:n;
  disp(binopdf(x,n,p));
  pause;
end
n =input("n>30: ");
p =input("p<0.05: ");
x=0:n;
y1 = binopdf(x,n,p);

plot(x, y1, 'x')