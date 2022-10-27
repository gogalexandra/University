%a. Write a predicate to determine the difference of two sets.

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
    
   
%b. Write a predicate to add value 1 after every even element from a list.

insertPos([], _, []).
insertPos([H|T], E, [H, E|R]):-
    H mod 2 =:= 0,
	insertPos(T, E, R).
insertPos([H|T], E, [H|R]):-
    H mod 2 =:= 1,
	insertPos(T, E, R).

main([], _).
main([H|T], R):-
    insertPos([H|T], 1, R).