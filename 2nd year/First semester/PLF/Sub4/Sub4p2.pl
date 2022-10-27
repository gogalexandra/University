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


genArr(_, 0, []).
genArr(L, K, [R1|R]):-
    arr(L, K, R1),
    K1 is K-1,
    genArr(L, K1, R).
    

%	myLen(l1l2...ln) =	1 + myLen(l2l3..ln),		n >= 0
%						0,						n = 0			
%	myLen(L - int input list, R- result )
%	flow model(i; o)

myLen([], 0).
myLen([_|T], R):-
    myLen(T, R1),
    R is R1 +1.

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

oneSolution(L, R, K):-
    arr(L, K, S),
    checkCond(S),
    myLen(S, CC1),
    CC1 mod 2 =:= 0,
    R = S.


%insertList(l1...ln, list) =	list, n = 0
% 			l1 + insertList(l2...ln, list), otherwise
insertList([], L, L).
insertList([H|T], L, [H|R]):-
    insertList(T, L, R).
%insertList(L:list, List:list, R:list)
%insertList(i, i, o)


%	allSolutions(l1l2...ln) = findall(oneSolution(l1l2...ln))

%	allSolutions(L- int input list, R- resulted list)
%	flow model (i, o)



allSolutions(_,[], 0).
allSolutions(L, R2, K):-
    K1 is K-1,
    findall(X, oneSolution(L, X, K), R1),
    insertList(R1, R, R2),
    allSolutions(L, R, K1).


all(L, L1):-
    myLen(L, CC),
    allSolutions(L,L1, CC).

