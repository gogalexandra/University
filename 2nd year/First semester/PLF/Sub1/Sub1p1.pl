f([], 0).
f([H|T],S):-
    f(T,S1),
    S1<H,!,
    S is H.
f([_|T],S):-
    f(T,S1),
    S is S1.

g([], 0).
g([H|T], S):-
    g(T, S1),
    aux(H, S, S1).

aux(H, S, S1):-
    S1<H,!,
    S is H.
aux(_, S, S).