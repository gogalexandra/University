x1 = [20, 21.6, 21.6, 21.7, 22.4,22.4, 23.3,23.4, 24.5, 24.8];
x2 = [12.1, 12.2, 12.6, 14, 14.8, 14.8, 15.4, 17.7,19.6, 19.6];


#FOR EQUAL STD DEV
alpha = input('Give significance level: ')

n1 = length(x1)
n2 = length(x2)
n = n1 + n2 - 2

mx1 = mean(x1)
mx2 = mean(x2)
vx1 = var(x1)
vx2 = var(x2)

z = tinv(1 - alpha/2, n)
rad = sqrt(1/n1+1/n2)
sp = sqrt(((n1-1)*vx1+(n2-1)*vx2)/n);

ciL = mx1- mx2- z*sp*rad;
ciR = mx1- mx2+ z*sp*rad;

fprintf('Cofidence Interval for difference of means (standard deviations unknown, equal)\n')
fprintf('(%.4f,%.4f)\n',ciL, ciR)

#FOR DIFF STD DEV
alpha = input("\n Give significance level: ")

n1 = length(x1)
n2 = length(x2)

mx1 = mean(x1)
mx2 = mean(x2)
vx1 = var(x1)
vx2 = var(x2)

c = (vx1/n1)/(vx1/n1+vx2/n2);
n = 1/(c^2/(n1-1)+(1-c)^2/(n2-1));

z = tinv(1 - alpha/2, n)
rad = sqrt(vx1/mx1 + vx2/mx2)


ciL = mx1 - mx2 - z*rad;
ciR = mx1 - mx2 + z*rad;

fprintf('Cofidence Interval for difference of means (standard deviations unknown, diffrent)\n')
fprintf('(%.4f,%.4f)\n',ciL, ciR)


#FOR RATIO OF VARIANCES
alpha = input("\n Give significance level: ")

n1 = length(x1)
n2 = length(x2)

vx1 = var(x1)
vx2 = var(x2)
rap=vx1/vx2;

f1 = finv(1 - alpha/2, n1 - 1, n2 - 1)
f2 = finv(alpha/2, n1 - 1, n2 - 1)

ciL = rap/f1
ciR = rap/f2

fprintf('Cofidence Interval for ratio of variances \n')
fprintf('(%.4f,%.4f)\n',ciL, ciR)

#FOR RATIO OF STANDARD DEVIATIONS
alpha = input("\n Give significance level: ")

n1 = length(x1)
n2 = length(x2)

vx1 = var(x1)
vx2 = var(x2)
rap=vx1/vx2;

f1 = finv(1 - alpha/2, n1 - 1, n2 - 1)
f2 = finv(alpha/2, n1 - 1, n2 - 1)

ciL = rap/f1
ciR = rap/f2

fprintf('Cofidence Interval for ratio of standard deviations \n')
fprintf('(%.4f,%.4f)\n',sqrt(ciL), sqrt(ciR))