%a. Write a predicate to remove all occurrences of a certain atom from a list.

removeOcc([], _, []).
removeOcc([H|T], E, R):-
    H =:= E,
    removeOcc(T, E, R).
removeOcc([H|T], E, [H|R]):-
    H =\= E,
    removeOcc(T, E, R).

%b. Define a predicate to produce a list of pairs (atom n) from an initial list of atoms. 
%In this initial list atom has n occurrences.
%Eg.: numberatom([1, 2, 1, 2, 1, 3, 1], X) => X = [[1, 4], [2, 2], [3, 1]].

countOcc([], _, C, R):-
    R is C.
countOcc([H|T], E, C, R):-
    H =:= E,
    C1 is C+1,
    countOcc(T, E, C1, R).
countOcc([H|T], E, C, R):-
    H =\= E,
    countOcc(T, E, C, R).

main([], []).
main([H|T], [[H,C]|R]):-
    countOcc([H|T], H, 0, C),
    removeOcc([H|T], H, R1),
    main(R1, R).
    