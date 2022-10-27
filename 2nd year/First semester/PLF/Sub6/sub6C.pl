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


%	prod(l1l2...ln) =	l1 * prod(l2l3..ln),		n >= 0
%						1,							n = 0			
%	prod(L - int input list, R- result sum)
%	flow model(i; o)

prod([], 1).
prod([H|T], S):-
   	prod(T, S1),
    S is S1 * H.

checkCond(L, V):-
    prod(L, S),
    S < V.

% 	oneSolution(l1l2..ln) = S,	subsets(l1l2...ln) and checkCond(S)

%	oneSolution(L- int input list, R- resulted list)
%	flow model (i; o)

oneSolution(L, K, V, R):-
    arr(L, K,S),
    checkCond(S, V),
    R = S.

%	allSolutions(l1l2...ln) = findall(oneSolution(l1l2...ln))

%	allSolutions(L- int input list, R- resulted list)
%	flow model (i, o)

allSolutions(L, K, V,R):-
    findall(X, oneSolution(L,K, V, X), R).
