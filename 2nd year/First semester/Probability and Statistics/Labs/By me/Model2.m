x1 = [46, 37, 39, 48, 47, 44, 35, 31, 44, 37]
x2 = [35, 33, 31, 35, 34, 30, 27, 32, 31, 31]


n1 = length(x1)
n2 = length(x2)


#a.At 5% significance level, do the population variances seems to differ
alpha = 0.05

%h0: sigma1  = sigma2
%h1: sigma1 =! sigma2

%tail values: -1 for left tailed
%           : 0 for both, default
%           : 1 for rigth tailed
[h, p, ci, stats] = vartest2(x1,x2,"alpha" ,alpha,"tail","both");


%p-P value; ci = confidence level

if h == 0
    fprintf('H0 is not rejected, i.e, sigmas are equal\n');
else 
    fprintf('H0 is rejected, population variances differ \n');
end

q1 = finv(alpha/2, stats.df1, stats.df2);
q2 = finv(1-alpha/2, stats.df2, stats.df1);
fprintf('Observed value is %1.4f\n', stats.fstat);
fprintf('P-value is %1.4f\n', p);
fprintf('Rejection region R is (-inf, %3.4f) U (%3.4f, inf)\n', q1, q2);

fprintf('\n\npoint b !\n');