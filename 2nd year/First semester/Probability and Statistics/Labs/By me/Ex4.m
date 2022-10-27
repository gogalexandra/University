% A food store owner receives 1-liter water bottles from two suppliers.
% After some complaints from customers, he wants to check the accuracy of the weights of 1-liter water bottles

% A: 1021 980 1017 988 1005 998 1014 985 995 1004 1030 1015 995 1023
% B: 1070 970 993 1013 1006 1002 1014 997 1002 1010 975

pkg load statistics

x1=[1021 980 1017 988 1005 998 1014 985 995 1004 1030 1015 995 1023]
x2=[1070 970 993 1013 1006 1002 1014 997 1002 1010 975]

n1=length(x1)
n2=length(x2)

%a) At the 5% significance level, do the population variances seem to differ?

alpha=0.05

%H0: miu  = miu0
%H1: miu != miu0

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


%b) At the same signif level, on average, does supplier A seem to be more reliable?

pkg load statistics

x1=[1021 980 1017 988 1005 998 1014 985 995 1004 1030 1015 995 1023]
x2=[1070 970 993 1013 1006 1002 1014 997 1002 1010 975]

n1=length(x1)
n2=length(x2)

alpha=0.05

%H0: miu= miu0
%H1: miu> miu0

disp("We are doing a right-tailed test for the mean\n")
[h,p,ci,stats]=ttest2(x1,x2,"alpha",alpha,"tail","right") %cazul 3.2

s1=var(x1) %deja e la patrat
s2=var(x2)

%din a) =>ca variantele sunt egale, iar cum std=sqrt(var) => std1=std2 (sigma egale)
sp=((n1-1)*s1+(n2-1)*s2)/(n1+n2-2)

RR=[tinv(1-alpha,n1+n2-2),inf]

fprintf('\n h is %d', h)
if h == 1 % result of the test, h = 0, if H0 is NOT rejected, h = 1, if H0 IS rejected
    fprintf('\n So the null hypothesis is rejected,\n')
    fprintf('i.e. the data suggests that supplier A is more reliable.\n')
else
    fprintf('\n So the null hypothesis is not rejected,\n')
    fprintf('i.e. the data suggests that supplier A is not more reliable.\n')
end 

fprintf('Rejection region RR is %4.4f\n',RR);
fprintf('the value of the test statistic z is %4.4f\n', stats.tstat)
fprintf('the P-value of the test is %4.4f\n\n', p)