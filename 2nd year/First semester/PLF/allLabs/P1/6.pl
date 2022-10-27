%a. Write a predicate to test if a list is a set.
search([], _, R):-
    R is 1.
search([H|_], E, R):-
    H =:= E,
    R is 0,
    !.
search([H|T], E, R):-
    H =\= E,
    search(T, E, R).

isSet([], R):-
    R is 0.
isSet([H|T], R):-
    search(T, H, FOUND),
    FOUND =:= 0,
   	R is 1,
    !.
isSet([_|T], R):-
    isSet(T, R).

%b. Write a predicate to remove the first three occurrences of an element in a list. 
%%If the element occurs less than three times, all occurrences will be removed.

countOcc([], _, C, R):-
    R is C.
countOcc([H|T], E, C, R):-
    H =:= E,
    C1 is C+1,
    countOcc(T, E, C1, R).
countOcc([H|T], E, C, R):-
    H =\= E,
    countOcc(T, E, C, R).


removeOcc([], _, []).
removeOcc([H|T], E, R):-
    H =:= E,
    removeOcc(T, E, R).
removeOcc([H|T], E, [H|R]):-
    H =\= E,
    removeOcc(T, E, R).

rem3([], _, _,[]).
rem3([H|T], E, K, R):-
    H =:= E,
    K > 0,
    K1 is K - 1,
    rem3(T, E, K1, R).
rem3([H|T], E, K, [H|R]):-
    rem3(T, E, K,R).


main([], _).
main([H|T], R):-
    countOcc([H|T], H, 0, CC),
    CC < 3,
    removeOcc([H|T], H, R1),
    main(R1, R).
main([H|T], [H|R]):-
    rem3([H|T], H, 3, R2),
    main(R2, R).



