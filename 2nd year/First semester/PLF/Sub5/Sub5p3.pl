%   subsets(l1l2..ln) =		l1 U subsets(l2l3...ln),	n >= 1
%   						subsets(l2l3..ln),			n >= 1
%   							[],							n = 0

%   subsets(L- int list input; R- resulted list)
%   flow model (i; o)

subsets([], []).
subsets([H|T], [H|R]):-
    subsets(T, R).
subsets([_|T], R):-
    subsets(T, R).

%	myLen(l1l2...ln) =	1 + myLen(l2l3..ln),		n >= 0
%						0,						n = 0			
%	myLen(L - int input list, R- result )
%	flow model(i; o)

myLen([], 0).
myLen([_|T], R):-
    myLen(T, R1),
    R is R1 +1.


countEvenNr([], 0).
countEvenNr([H|T], CC):-
    countEvenNr(T, CC1),
    H mod 2 =:= 0,
    CC is CC1 +1.
countEvenNr([H|T], CC):-
    countEvenNr(T, CC1),
    H mod 2 =:= 1,
    CC is CC1.

%	sum(l1l2...ln) =	l1 + sum(l2l3..ln),		n >= 0
%						0,						n = 0			
%	sum(L - int input list, R- result sum)
%	flow model(i; o)

sum([], 0).
sum([H|T], S):-
   	sum(T, S1),
    S is S1 + H.

checkCond(L):-
    sum(L, S),
    S mod 2 =:= 1.


% 	oneSolution(l1l2..ln) = S,	subsets(l1l2...ln) and checkCond(S)

%	oneSolution(L- int input list, R- resulted list)
%	flow model (i; o)

oneSolution(L, R):-
    subsets(L, S),
    checkCond(S),
    countEvenNr(S,CC),
    CC > 0,
    CC mod 2 =:= 0,
    R = S.

%	allSolutions(l1l2...ln) = findall(oneSolution(l1l2...ln))

%	allSolutions(L- int input list, R- resulted list)
%	flow model (i, o)

allSolutions(L, R):-
    findall(X, oneSolution(L, X), R).
