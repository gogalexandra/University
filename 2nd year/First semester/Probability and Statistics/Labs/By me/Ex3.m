%Nickel powders are used in coatings used to sheild electronic equimnent.
%A random sample is selected, and the sizes of nickel particles in each coating are recorded:

%3.26 1.89 2.42 2.03 3.07 2.95 1.39 3.06 2.46 3.35 1.56 1.79 1.76 3.82 2.42 2.96

%a) Find a 95% confidence interval for the average size of nickel particles
pkg load statistics

x=[3.26 1.89 2.42 2.03 3.07 2.95 1.39 3.06 2.46 3.35 1.56 1.79 1.76 3.82 2.42 2.96]
n=length(x)

confidenceLevel=0.95
alpha=1-confidenceLevel %signif level

xbar=mean(x)
s=std(x)
q=tinv(1-alpha/2,n-1)

v1=xbar-(s/sqrt(n))*q %interval 1.2
v2=xbar+(s/sqrt(n))*q

%b) at 1% significance level, on average, do these nickel particles seem to be smaller than 3?
pkg load statistics

x=[3.26 1.89 2.42 2.03 3.07 2.95 1.39 3.06 2.46 3.35 1.56 1.79 1.76 3.82 2.42 2.96]

%H0: miu=3
%H1: miu<3

m0=3

n=length(x)

alpha=0.01

disp("We are doing a left-tailed test fot the mean\n")
[h,p,ci,stats]=ttest(x,m0,"alpha",alpha,"tail","left") %cazul 3.3

RR=[-inf,tinv(alpha,n-1)]

fprintf('\n h is %d', h)
if h == 1 % result of the test, h = 0, if H0 is NOT rejected, h = 1, if H0 IS rejected
    fprintf('\n So the null hypothesis is rejected,\n')
    fprintf('i.e. the data suggests that the particles seem to be smaller than 3.\n')
else
    fprintf('\n So the null hypothesis is not rejected,\n')
    fprintf('i.e. the data suggests that the particles are not smaller than 3.\n')
end 

fprintf('Rejection region RR is %4.4f\n',RR);
fprintf('the value of the test statistic z is %4.4f\n', stats.tstat)
fprintf('the P-value of the test is %4.4f\n\n', p)