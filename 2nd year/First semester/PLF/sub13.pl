%SUBIECTUL 13



%	insert(l1...ln, e) =	e U l1l2...ln
% 							l1 U insert(l2l3...ln, e), otherwise

% 	insert(L:list, E:number, R:result list)
% 	insert(i, i, o)

insert(L, E, [E|L]).
insert([H|T], E, [H|R]):- 
         insert(T, E, R).

%	arr(l1l2...ln, K) = 	l1, 								k=1 and n>=1
%							arr(l2l3...ln, K), 					k>=1
%							insert(l1, arr(l2l3...ln, K-1)),	k>1

%	arr(L-list, K-nr of elements, R- resulted list)
%	arr(i,i,o)

arr([E|_], 1, [E]).
arr([_|T], K, R):-
    arr(T, K, R).
arr([H|T], K, R1):-
    K > 1,
    K1 is K-1,
    arr(T, K1, R),
    insert(R, H,R1).

    

%	myLen(l1l2...ln) =	1 + myLen(l2l3..ln),		n >= 0
%						0,						n = 0			
%	myLen(L - int input list, R- result )
%	flow model(i; o)

checkCond([_], _).
checkCond([H1,H2|T], V):-
    D = H2 - H1,
    D =:= V,
    H2 > H1,
    checkCond([H2|T], V).

check([H1,H2|T]):-
    D is H2 - H1,
    checkCond([H1,H2|T], D).


oneSolution(L, K, R):-
    arr(L,K, S),
    check(S),
    R = S.

%	allSolutions(l1l2...ln) = findall(oneSolution(l1l2...ln))

%	allSolutions(L- int input list, R- resulted list)
%	flow model (i, o)
allSolutions(L, K, R):-
    findall(X, oneSolution(L, K, X), R).