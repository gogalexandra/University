%removes every occurence of the elem EL
%flow: (i,i,o)
%removeOcc(L-list of elements, E-element to remove,R-result list)

removeOcc([], _, []).
removeOcc([H|T], E, R) :-
    H =:= E,  % in this case the head is the element we want to remove, so we won't add it to the result
    removeOcc(T, E, R).
removeOcc([H|T], E, [H|R]) :-
    H =\= E, % here, we want to add the head to the result, because it's not the element we're looking to remove
    removeOcc(T, E, R).


% Call removeOcc for all elems and adds them to result list
% transformToSet(L-list, R-result list)
% flow: (i, o)

transformToSet([], []).
transformToSet([H|T], [H|R]) :- 
    removeOcc(T, H, R1),
    transformToSet(R1, R).