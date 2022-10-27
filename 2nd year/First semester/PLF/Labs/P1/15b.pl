%Mergelist is transforming the 2 lists recieved as params into one
%flow(i,i,o)
mergeList([],L,L ).
mergeList([H|T],L,[H|M]):-
    mergeList(T,L,M).

%Splits the list into 2 list:one for even and one for odd and also counts the elems.
%flow (i,o,o,o,o)
split([],[],[],0,0).
split([H|T],[H|Odd],Even,O,E):-
    H mod 2 =:= 1,
    split(T, Odd, Even, O1, E),
    O is O1+1.
split([H|T],Odd,[H|Even],O,E):-
    H mod 2 =:= 0,
    split(T, Odd, Even, O, E1),
    E is E1+1.

%In the main function is called split function and the merge lists to get the final result.
%flow(i,o,o,o)
main([],[],0,0).
main([H|T],R, C1, C2):-
    split([H|T], L1, L2, C1, C2),
    mergeList(L2,L1,R).
    