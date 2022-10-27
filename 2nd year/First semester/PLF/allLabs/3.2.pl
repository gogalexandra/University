countOcc([], _, C, R):-
    R is C.
countOcc([H|T], E, C, R):-
    H =:= E,
    C1 is C+1,
    countOcc(T, E, C1, R).
countOcc([H|T], E, C, R):-
    H =\= E,
    countOcc(T, E, C, R).

rem3([], _, _,[]).
rem3([H|T], E, K, R):-
    H =:= E,
    K > 0,
    K1 is K - 1,
    rem3(T, E, K1, R).
rem3([H|T], E, K, [H|R]):-
    rem3(T, E, K,R).

removeDoubles([], []).
removeDoubles([H|T], R):-
    countOcc([H|T], H, 0, CC),
    CC = 2,
    rem3([H|T], H, 1, R1),
    removeDoubles(R1, R).
removeDoubles([H|T], [H|R]):-
    removeDoubles(T, R).

merge([], L2, R):-
    R = L2.
merge(L1, [], R):-
    R = L1.
merge([H1|T1], [H2|T2],[H2|R]):-
    H1 > H2,
    merge([H1|T1], T2, R).
merge([H1|T1], [H2|T2],[H1|R]):-
    H1 =< H2,
    merge(T1, [H2|T2], R).

mainA(L1, L2, R):-
    merge(L1, L2, R1),
    removeDoubles(R1, R).

mainB([], []).
mainB([H|T], R2):-
    is_list(H),
    mainB(T, R),
    merge(R, H, R1),
    removeDoubles(R1, R2).
mainB([H|T], R):-
    number(H),
    mainB(T, R).







    