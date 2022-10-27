 listToNr([], NR, F):-
	F is NR.
listToNr([H|T], NR, F):-
    NR1 is NR*10 + H,
    listToNr(T, NR1, F).

mergeList([],L,L ).
mergeList([H|T],L,[H|M]):-
    mergeList(T,L,M).

nrToList(NR,[L|[]]):-
    NR < 10,
    L is NR,
    !.
nrToList(NR,L):-
    NR1 is NR div 10,
    nrToList(NR1,L1),
    D is NR mod 10,
    mergeList(L1, [D], L).

main([], []).
main(RL, L):-
    listToNr(RL, 0, R),
    nrToList(R-1,L).