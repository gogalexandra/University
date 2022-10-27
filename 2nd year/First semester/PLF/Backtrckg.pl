%	insert(l1...ln, e) =	e U l1l2...ln
% 							l1 U insert(l2l3...ln, e), otherwise

% 	insert(L:list, E:number, R:result list)
% 	insert(i, i, o)

insert(L, E, [E|L]).
insert([H|T], E, [H|R]):- 
         insert(T, E, R).

% 	perm(l1l2...ln) =	[], 						n = 0
% 						insert(perm(l2...ln), l1),	otherwise

% 	perm(L:list, R:result list)
% 	perm(i, o)

perm([], []).
perm([H|T], R) :-
    perm(T, RP),
    insert(RP, H, R).


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



%	comb(l1l2...ln, K) =	l1,							k = 1 and n >= 1
%							comb(l2l3...ln, K),			k>=1
%							l1 U comb(l2l3...ln, K-1),	k>1

%	comb(L-list, K-nr of elements, R- resulted list)
%	comb(i,i,o)

comb([E|_], 1, [E]).
comb([_|T], K, R):-
    comb(T, K, R).
comb([H|T], K, [H|R]):-
    K > 1,
    K1 is K-1,
    comb(T, K1, R).

allsolutions(L,K, R) :-
    findall(RPartial, arr(L, K,RPartial), R).