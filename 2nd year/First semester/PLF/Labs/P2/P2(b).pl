listToNr([], NR, F):-
	F is NR.
listToNr([H|T], NR, F):-
    NR1 is NR*10 + H,
    listToNr(T, NR1, F).

mergeList([],L,L ).
mergeList([H|T],L,[H|M]):-
    mergeList(T,L,M).

nrToList(NR,[L|_]):-
    NR < 10,
    L is NR,
    !.
nrToList(NR,L):-
    NR1 is NR div 10,
    nrToList(NR1,L1),
    D is NR mod 10,
    mergeList(L1, [D], L).

main([], []).
main([H|T], [L1|R]):-
    is_list(H),
    listToNr(H, 0, R1),
    nrToList(R1-1, L1),
    main(T,R).
main([H|T], [H|R]):-
    number(H),
    main(T,R).















