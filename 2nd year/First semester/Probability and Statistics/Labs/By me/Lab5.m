x= [7, 7, 4, 5, 9, 9, 4, 12, 8, 1, 8, 7, 3, 13, 2, 1, 17, 7,...
   12, 5, 6, 2, 1, 13, 14, 10, 2, 4, 9, 11, 3, 5, 12, 6, 10, 7];

   
#FOR KNOWN SIGMA
alpha =input('Give significance level: ')
sigma = 5

n = length(x)
z = norminv(1- alpha /2)
mx = mean(x)

ciL = mx - z*sigma/ sqrt(n)
ciR = mx + z*sigma/ sqrt(n)

fprintf('Cofidence Interval for the mean (standard deviation known)\n')
fprintf('(%.4f,%.4f)\n', ciL, ciR)

#FOR UNKNOWN SIGMA

alpha =input("\n Give significance level: ")

n = length(x)
z =  tinv(1- alpha /2, n- 1)
mx = mean(x)
s = std(x)

ciL = mx - z*s/ sqrt(n)
ciR = mx + z*s/ sqrt(n)

fprintf('Cofidence Interval for the mean (standard deviation unknown)\n')
fprintf('(%.4f,%.4f)\n', ciL, ciR)

#FOR THE VARIANCE

alpha =input("\n Give significance level: ")

n = length(x)
q1 = chi2inv(1- alpha/2, n-1)
q2 = chi2inv(alpha/2, n-1)
v = var(x)

ciL = (n-1)*v/q1
ciR = (n-1)*v/q2

fprintf('Cofidence Interval for the variance\n')
fprintf('(%.4f,%.4f)\n', ciL, ciR)


#FOR THE STANDARD DEVIATIONS

alpha =input("\n Give significance level: ")

n = length(x)
q1 = chi2inv(1- alpha/2, n-1)
q2 = chi2inv(alpha/2, n-1)
v = var(x)

ciL = (n-1)*v/q1
ciR = (n-1)*v/q2

fprintf('Cofidence Interval for the variance\n')
fprintf('(%.4f,%.4f)\n', sqrt(ciL), sqrt(ciR))