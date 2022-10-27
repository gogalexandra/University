alpha = input('Give significance level: ')
x = [ 7, 7, 4, 5, 9, 9, 4, 12, 8, 1, 8, 7, 3, 13, 2, 1, 17, 7, 12, 5, 6, 2, 1, 13, 14, 10, 2, 4, 9, 11, 3, 5, 12, 6, 10, 7];
n = length(x)
sigma = 5
disp ("We are using test ")
m0 = 9 
#[H, PVAL, CI, Z, ZCRIT] = ztest (x, m0, sigma)
#norminv of alpha
tt = norminv(alpha)
rr = [-inf, tt]
[h, p, ci, zstat] = ztest(x, m0, sigma, "alpha", alpha, "tail", "left")
 
if h == 1 % result of the test, h = 0, if H0 is NOT rejected, h = 1, if H0 IS rejected
    fprintf('\n So the null hypothesis is rejected,\n')
    fprintf('i.e. the data suggests that the standard IS NOT met.\n')
else
    fprintf('\n So the null hypothesis is not rejected,\n')
    fprintf('i.e. the data suggests that the standard IS  met.\n')
end   

fprintf('the rejection region is (%4.4f, %4.4f)\n', rr)
fprintf('the value of the test statistic z is %4.4f\n', zstat)
fprintf('the P-value of the test is %4.4f\n\n', p)