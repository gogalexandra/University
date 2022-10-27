%A study is conducted to compare heat loss in glass pipes, vr steel pipes of the same size.
% Various liquids at identical starting temperatures are run through segments of each type and the heat liss (in C*) is measured

%Steal: 4.6 0.7 4.2 1.9 4.8 6.1 4.7 5.5 5.4
%Glass: 2.5 1.3 2.0 1.8 2.7 3.2 3.0 3.5 3.4
pkg load statistics

x1=[4.6 0.7 4.2 1.9 4.8 6.1 4.7 5.5 5.4]
x2=[2.5 1.3 2.0 1.8 2.7 3.2 3.0 3.5 3.4]

n1=length(x1)
n2=length(x2)

%a) At the 5% significance level, do the population variences seem to differ?
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


%b) At the same signif, level, does it seem that on average, steel pipes lose *more* heat than glass pipes?
pkg load statistics

x1=[4.6 0.7 4.2 1.9 4.8 6.1 4.7 5.5 5.4]
x2=[2.5 1.3 2.0 1.8 2.7 3.2 3.0 3.5 3.4]

n1=length(x1)
n2=length(x2)

alpha=0.05

%H0: miu= miu0
%H1: miu> miu0

disp("We are doing a right-tailed test fot the varience. sigma1!=sigma2, unknown\n")
[h,p,ci,stats]=ttest2(x1,x2,"alpha",alpha,"tail","right") %cazul 3.3

s1=var(x1) %deja e la patrat
s2=var(x2)

%ptc din a) =>ca variantele difera, std=sqrt(var)=> sigmaurile difera=> interval 3.3
c=(s1/n1)/(s1/n1+s2/n2)
n=(n1-1)*(n2-1)/(c^2*(n2-1)+(1-c)^2*(n1-1))

RR=[tinv(1-alpha,n),inf]

fprintf('\n h is %d', h)
if h == 1 % result of the test, h = 0, if H0 is NOT rejected, h = 1, if H0 IS rejected
    fprintf('\n So the null hypothesis is rejected,\n')
    fprintf('i.e. the data suggests that the steel pipes lose more heat.\n')
else
    fprintf('\n So the null hypothesis is not rejected,\n')
    fprintf('i.e. the data suggests that the steel pipe do not lose more heat.\n')
end 

fprintf('Rejection region RR is %4.4f\n',RR);
fprintf('the value of the test statistic z is %4.4f\n', stats.tstat)
fprintf('the P-value of the test is %4.4f\n\n', p)
