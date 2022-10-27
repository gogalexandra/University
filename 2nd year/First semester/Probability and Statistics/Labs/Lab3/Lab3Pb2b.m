
clc
clear 
close all

n=55;
p=0.024;

lambda=n*p;

k=0:1:n;
bk=pdf('bino',k,n,p);
bp=pdf('poiss',k,lambda);

figure(1)
hold on
box on
plot(k,bk,'b*')
plot(k,bp,'ro')
legend('Binomial','Poisson')

[bk; bp]

figure(2)
hold on
box on
plot(k,log10(abs(bk-bp)),'r*')
