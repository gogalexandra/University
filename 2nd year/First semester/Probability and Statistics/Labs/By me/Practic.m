pkg load statistics

x1 = [21.8, 22.6, 21.0, 19.7, 21.9, 21.6, 22.5, 23.1, 22.2, 20.1, 21.4, 20.5]
x2 = [36.5, 35.2, 36.2, 34.0, 36.4, 36.1, 37.5, 38.0, 36.3, 35.9, 35.7, 34.9]
 
n1 = length(x1)
n2 = length(x2)

#a
alpha = 0.05

%H0: miu  = miu0
%H1: miu != miu0

disp("We are doing a two-tailed test fot the varience\n")
[h, p, ci, stats] = vartest2(x1, x2, "alpha", alpha, "tail", "both")
#both indicates the type of test

q1 = finv(alpha/2,n1-1,n2-1)
q2 = finv(1-alpha/2,n1-1,n2-1)

fprintf('\n h: %d', h)
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


#b

x1bar = mean(x1)
x2bar = mean(x2)

s1 = var(x1)
s2 = var(x2)

c=(s1/n1)/(1/n1+1/n2)
n=(n1-1)*(n2-1)/(c^2*(n2-1)+(1-c)^2*(n1-1))

q=tinv(1-alpha/2,n) 
v1=x1bar-x2bar-q*sqrt(s1/n1+s2/n2) %interval 3.2
v2=x1bar-x2bar+q*sqrt(s1/n1+s2/n2)