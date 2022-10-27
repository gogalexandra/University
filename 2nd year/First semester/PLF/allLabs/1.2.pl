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


pivoting(_,[],[],[]).
pivoting(H,[X|T],[X|L],G):-X>H,pivoting(H,T,L,G).
pivoting(H,[X|T],L,[X|G]):-X=<H,pivoting(H,T,L,G).

q_sort([],Acc,Acc).
q_sort([H|T],Acc,Sorted):-
    pivoting(H,T,L1,L2),
    q_sort(L1,Acc,Sorted1),q_sort(L2,[H|Sorted1],Sorted).

 
quick_sort2(List,Sorted):-
    q_sort(List,[],Sorted).   


mainA(L, R1):-
    quick_sort2(L, R),
    removeDoubles(R, R1).
    
mainB([], []).
mainB([H|T], [R2|R]):-
    is_list(H),
    quick_sort2(H, R1),
    removeDoubles(R1, R2),
    mainB(T, R).
mainB([H|T], [H|R]):-
    number(H),
    mainB(T, R).






