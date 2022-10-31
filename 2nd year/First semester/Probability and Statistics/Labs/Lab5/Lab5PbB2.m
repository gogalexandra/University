
clc
clear 
close all

% x1 = [20, 21.6, 21.6, 21.7, 22.4,22.4, 23.3,23.4, 24.5, 24.8];
% x2 = [12.1, 12.2, 12.6, 14, 14.8, 14.8, 15.4, 17.7,19.6, 19.6];
% 

x1= [4.6, 0.7, 4.2, 1.9, 4.8, 6.1 ,4.7, 5.5, 5.4];
x2= [2.5, 1.3, 2.0, 1.8, 2.7, 3.2, 3.0, 3.5, 3.4];

alpha=0.05;


% [li,ri]=ConfIntDifMeanVar(x1,x2,sig1,sig2,alpha);
% fprintf('Cofidence Interval for difference of the mean (standard deviations known)\n')
% fprintf('(%.4f,%.4f)\n',li,ri)

[li,ri]=ConfIntDifMeanNotVarEq(x1,x2,alpha);
fprintf('Cofidence Interval for difference of means (standard deviations unknown, equal)\n')
fprintf('(%.4f,%.4f)\n',li,ri)

[li,ri]=ConfIntDifMeanNotVarDif(x1,x2,alpha);
fprintf('Cofidence Interval for difference of means (standard deviations unknown, not equal)\n')
fprintf('(%.4f,%.4f)\n',li,ri)


[li,ri]=ConfIntVarRation(x1,x2,alpha);
fprintf('Cofidence Interval for ratio of variances \n')
fprintf('(%.4f,%.4f)\n',li,ri)

fprintf('Cofidence Interval for ratio of standard deviations \n')
fprintf('(%.4f,%.4f)\n',sqrt(li),sqrt(ri))