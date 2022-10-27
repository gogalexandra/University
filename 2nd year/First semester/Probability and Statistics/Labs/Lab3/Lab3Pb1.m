
clc
clear
close all

%Normal:norm
%Student:t
%Chi2Square: chi2
%Fischer: f

alpha=input('alpha=');
beta=input('beta=');
Type=input('Choose Normal Student Chisquare Fischer=','s');


%a)
%P(X<=a)=F_X(a)=cdf(...,a,...)
%P(X>=a)=1-P(X<a)=1-F_X(a)=1-cdf(...,a,...)
%b)
%P(a<=X<=b)=F_X(b)-F_X(a)
%P(X<=a OR X>=b)=1-P(a<X<b)=1-(F_X(b)-F_X(a))=1-F_X(b)+F_X(a)
%c) 
%P(X<xa)=F_X(xa); P(X<xa)=a <==> F_X(xa)=a <==> xa=F^(-1)_X(a); icdf(...,a,...
%d
%P(X>xb)=b <==>1-P(X<=xb)=b<==>1-F_X(xb)=b <==> F_X(xb)=1-b <==> xb=F^(-1)_X(1-b);

switch Type
    case 'Normal'
%Normal
fprintf('a)\n')
mu=input('mu=');
sigma=input('sigma=');
Pa1=cdf('norm',0,mu,sigma);
fprintf('Probability at a) 1 is %f\n',Pa1)
Pa2=1-Pa1;
fprintf('Probability at a) 2 is %f\n',Pa2)

fprintf('b)\n')
Pb1=cdf('norm',1,mu,sigma)-cdf('norm',-1,mu,sigma);
fprintf('Probability at b) 1 is %f\n',Pb1)
Pb2=1-Pb1;
fprintf('Probability at b) 2 is %f\n',Pb2)

fprintf('c)\n')
xa=icdf('norm',alpha,mu,sigma);
fprintf('Quantile of order alpha=%f\n',xa)

fprintf('d)\n')
xb=icdf('norm',1-beta,mu,sigma);
fprintf('Quantile of order alpha=%f\n',xb)


x1=mu-3*sigma-1;
x2=mu+3*sigma+1;
x=x1:0.01:x2;

figure(1)
hold on
box on
plot(x,pdf('norm',x,mu,sigma))
line([xa,xa],[0,pdf('norm',xa,mu,sigma)],'Color', 'k','LineStyle',':')


figure(2)
hold on
box on
plot(x,cdf('norm',x,mu,sigma))
line([x1,x2],[alpha,alpha],'Color','k')
plot(xa,alpha,'r*')
line([xa,xa],[0,1],'Color', 'k','LineStyle',':')

    case 'Student'

%Student
fprintf('a)\n')
nu=input('nu=');
Pa1=cdf('t',0,nu);
fprintf('Probability at a) 1 is %f\n',Pa1)
Pa2=1-Pa1;
fprintf('Probability at a) 2 is %f\n',Pa2)

fprintf('b)\n')
Pb1=cdf('t',1,nu)-cdf('t',-1,nu);
fprintf('Probability at b) 1 is %f\n',Pb1)
Pb2=1-Pb1;
fprintf('Probability at b) 2 is %f\n',Pb2)

fprintf('c)\n')
xa=icdf('t',alpha,nu);
fprintf('Quantile of order alpha=%f\n',xa)

fprintf('d)\n')
xb=icdf('t',1-beta,nu);
fprintf('Quantile of order alpha=%f\n',xb)

    case 'Chi2Square'

%ChiSquare
fprintf('a)\n')
n=input('n=');
Pa1=cdf('chi2',0,n);
fprintf('Probability at a) 1 is %f\n',Pa1)
Pa2=1-Pa1;
fprintf('Probability at a) 2 is %f\n',Pa2)

fprintf('b)\n')
Pb1=cdf('chi2',1,n)-cdf('chi2',-1,n);
fprintf('Probability at b) 1 is %f\n',Pb1)
Pb2=1-Pb1;
fprintf('Probability at b) 2 is %f\n',Pb2)

fprintf('c)\n')
xa=icdf('chi2',alpha,n);
fprintf('Quantile of order alpha=%f\n',xa)

fprintf('d)\n')
xb=icdf('chi2',1-beta,n);
fprintf('Quantile of order alpha=%f\n',xb)

 case 'Fischer'

%Fischer
fprintf('a)\n')
n=input('n=');
m=input('m=');
Pa1=cdf('f',0,n,m);
fprintf('Probability at a) 1 is %f\n',Pa1)
Pa2=1-Pa1;
fprintf('Probability at a) 2 is %f\n',Pa2)

fprintf('b)\n')
Pb1=cdf('f',1,n,m)-cdf('chi2',-1,n,m);
fprintf('Probability at b) 1 is %f\n',Pb1)
Pb2=1-Pb1;
fprintf('Probability at b) 2 is %f\n',Pb2)

fprintf('c)\n')
xa=icdf('f',alpha,n,m);
fprintf('Quantile of order alpha=%f\n',xa)

fprintf('d)\n')
xb=icdf('f',1-beta,n,m);
fprintf('Quantile of order alpha=%f\n',xb)
 end
