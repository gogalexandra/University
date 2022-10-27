decomposition(N, E, R) :- E < N,
    onesolution(N, E, R).
decomposition(N, E, R) :- E < N,
    NE is E + 1,
    decomposition(N, NE, R).

% onesolution(n, e) =
% 	[], n = 0
% 	e + onesolution(n - e, e + 1), otherwise
 
% onesolution(N:number, E:number, R:list)
% onesolution(i, i, o)

onesolution(0, _, []).
onesolution(N, E, [E|R]) :- N >= E,
    NN is N - E,
    NE is E + 1,
    onesolution(NN, NE, R).

% allsolutions(N:number, R:list)
% allsolutions(i, o)

allsolutions(N, R) :-
    findall(RPartial, decomposition(N, 1, RPartial), R).