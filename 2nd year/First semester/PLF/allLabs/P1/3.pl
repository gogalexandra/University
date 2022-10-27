%a. Define a predicate to remove from a list all repetitive elements.
%Eg.: l=[1,2,1,4,1,3,4] => l=[2,3])

removeOcc([], _, []).
removeOcc([H|T], E, R):-
    H =:= E,
    removeOcc(T, E, R).
removeOcc([H|T], E, [H|R]):-
    H =\= E,
    removeOcc(T, E, R).

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
main([H|T], [H|R]):-
    countOcc([H|T], H, 0, C),
    C =:= 1,
    main(T, R).
main([H|T], R):-
    countOcc([H|T], H, 0, C),
    removeOcc([H|T], H, R1),
    C =\= 1,
    main(R1, R).

%b. Remove all occurrence of a maximum value from a list on integer numbers

findMax([], E, MAX):-
    MAX is E.
findMax([H|T], E,MAX):-
    H > E,
    findMax(T, H, MAX).
findMax([H|T], E,MAX):-
    H =< E,
    findMax(T, E, MAX).

removeMaximum([], _).
removeMaximum([H|T], R):-
    findMax([H|T], H, MAX),
    removeOcc([H|T], MAX, R).












