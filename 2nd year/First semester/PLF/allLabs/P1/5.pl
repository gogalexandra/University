%a. Write a predicate to compute the union of two sets.


mergeList([],L,L ).
mergeList([H|T],L,[H|M]):-
    mergeList(T,L,M).

removeOcc([], _, []).
removeOcc([H|T], E, R):-
    H =:= E,
    removeOcc(T, E, R).
removeOcc([H|T], E, [H|R]):-
    H =\= E,
    removeOcc(T, E, R).


diffSet(L1, [], R):-
    R = L1.
diffSet([], _, R):-
    R = [].
diffSet([H1|T1], [H2|T2], R):-
    removeOcc([H1|T1], H2, R1),
    diffSet(R1, T2, R).

main(L1, L2, R):-
    diffSet(L1, L2, R1),
    mergeList(R1, L2, R).

%b. Write a predicate to determine the set of all the pairs of elements in a list.
%Eg.: L = [a b c d] => [[a b] [a c] [a d] [b c] [b d] [c d]].

pairs([], _, []).
pairs([H|T], E, [[E,H]|R]):-
    pairs(T, E, R).

main([], _).
main([H|T], [R1,R]):-
    pairs(T, H, R1),
    main(T, R).



