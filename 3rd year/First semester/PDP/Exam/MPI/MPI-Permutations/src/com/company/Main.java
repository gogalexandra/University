package com.company;


import mpi.MPI;

import java.util.*;

public class Main {

    public static boolean check(List<Integer> vec){
        return vec.get(0)%2==0;
    }

    public static void back(List<Integer> sol, int n, List<List<Integer>> sols){
        if(sol.size()==n){
            if(check(sol)){
                sols.add(new ArrayList<>(sol));
            }
            return;
        }

        for (int i = 0; i <= n ; i++) {
            if(sol.contains(i)) continue;
            sol.add(i);
            back(sol, n, sols);
            sol.remove(sol.size()-1);

        }
    }

    private static void worker() {
        int[] n = new int[1];
        int[] parameters = new int[2];
        MPI.COMM_WORLD.Recv(parameters, 0, 2, MPI.INT, 0, 0);
        MPI.COMM_WORLD.Recv(n, 0, 1, MPI.INT, 0, 0);


        List<List<Integer>> localSols = new ArrayList<>(n[0]);
        for (int i = parameters[0]; i < parameters[1]; i++) {
            List<Integer> sol = new ArrayList<>();
            sol.add(i);
            back(sol, n[0], localSols);
        }
        System.out.println("i did this" + localSols);
        MPI.COMM_WORLD.Send(new Object[]{localSols}, 0,1,MPI.OBJECT, 0,0);
    }

    private static void master(int n, int nrOfProcesses) {
        int toShare = nrOfProcesses - 1;
        int length = (int) ( n / (nrOfProcesses - 1));

        int start = 0, end = 0;
        List<List<Integer>> solutions = new ArrayList<>();
        for (int i = 1; i <= toShare; i++) {
            start = end;
            end = start + length;
            if (i == nrOfProcesses - 1) {
                end = n -1 ;
            }

            int[] parameters = new int[]{start, end};
            MPI.COMM_WORLD.Send(parameters, 0, 2, MPI.INT, i, 0);
            MPI.COMM_WORLD.Send(new int[]{n}, 0, 1, MPI.INT, i, 0);
        }

        for (int i = 1; i <= nrOfProcesses - 1 ; i++) {
            Object[] objects = new Object[1];

            MPI.COMM_WORLD.Recv(objects, 0, 1, MPI.OBJECT, i, 0);
            solutions.addAll((List<List<Integer>>) objects[0]);
        }
        System.out.println(solutions);

    }

    public static void main(String[] args) {
        MPI.Init(args);
        int me = MPI.COMM_WORLD.Rank();
        int nrOfProcesses = MPI.COMM_WORLD.Size();
        int n = 4;
        if (me==0){
            master(n, nrOfProcesses);
        }
        else{
            worker();
        }
        MPI.Finalize();
    }



}