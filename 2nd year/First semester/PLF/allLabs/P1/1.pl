%a. Write a predicate to determine the lowest common multiple of a list formed 
%from integer numbers.

%gcd:(NR1, NR2, R)
gcd(NR1, NR2, R):- 
    NR1 = NR2, 
    R = NR1.
gcd(NR1, NR2, R):- 
    NR1 < NR2,
    NR is NR2-NR1,
    gcd(NR1, NR, R).
gcd(NR1, NR2, R):- 
    NR1 > NR2,
    gcd(NR2, NR1, R).

main([], R, R).
main([H|T], NR, _):-
    gcd(H, NR, VAL),
    REZ is H*NR/VAL,
    main(T, REZ, REZ).

insert_pos([], _, _, _, []).
insert_pos([H|T], V, POS, INDEX, [H, V|R]) :- 
    POS =:= INDEX,
    newPos is POS * 2,
    newIndex is INDEX + 1,
    insert_pos(T, V, newPos, newIndex, R).
insert_pos([H|T], V, POS, INDEX, [H|R]) :- 
    POS =\= INDEX,
    newIndex is INDEX + 1,
    insert_pow(T, V, POS, newIndex, R).


insert(L, V, R) :- insert_pow(L, V, 1, 1, R).
