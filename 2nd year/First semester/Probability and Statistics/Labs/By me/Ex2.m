% To reach the maximum efficiency in performing an assembling operation in a manufacturing plant, new employees
%are required to take a 1-month training course. A new method was suggested, and the manager wants to compare 
%the new method with the standard one, by looking at the lengths of time required for employees to assemble a certain device.

% standard: 46 37 39 48 47 44 35 31 44 37
% new:      35 33 31 35 34 30 27 32 31 31
pkg load statistics

x1=[46 37 39 48 47 44 35 31 44 37]
x2=[35 33 31 35 34 30 27 32 31 31]

n1=length(x1)
n2=length(x2)

%a) At the 5% significance level, do the population variances seem to differ?
alpha=0.05

%H0: miu=miu0
%H1: miu!=miu0

disp("We are doing a two-tailed test fot the varience\n")
[h,p,ci,stats]=vartest2(x1,x2,"alpha",alpha,"tail","both")

q1 = finv(alpha/2,n1-1,n2-1);
q2 = finv(1-alpha/2,n1-1,n2-1)

fprintf('\n h is %d', h)
if h == 1 % result of the test, h = 0, if H0 is NOT rejected, h = 1, if H0 IS rejected
    fprintf('\n So the null hypothesis is rejected,\n')
    fprintf('i.e. the data suggests that the variances differ.\n')
else
    fprintf('\n So the null hypothesis is not rejected,\n')
    fprintf('i.e. the data suggests that the variances are equal.\n')
end 

fprintf('Rejection region RR is (-inf, %3.4f) U (%3.4f, inf)\n', q1, q2);
fprintf('the value of the test statistic z is %4.4f\n', stats.fstat)
fprintf('the P-value of the test is %4.4f\n\n', p)

%b) Find the 95% confidence interval for the difference of average assembling times
pkg load statistics

x1=[46 37 39 48 47 44 35 31 44 37]
x2=[35 33 31 35 34 30 27 32 31 31]

n1=length(x1)
n2=length(x2)

x1bar=mean(x1)
x2bar=mean(x2)

s1=var(x1) %deja e la patrat
s2=var(x2)

c=(s1/n1)/(s1/n1+s2/n2)
n=(n1-1)*(n2-1)/(c^2*(n2-1)+(1-c)^2*(n1-1))

q=tinv(1-alpha/2,n) 
v1=x1bar-x2bar-q*sqrt(s1/n1+s2/n2) %interval 3.3
v2=x1bar-x2bar+q*sqrt(s1/n1+s2/n2)