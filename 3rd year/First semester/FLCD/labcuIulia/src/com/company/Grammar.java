package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Grammar {
    private final List<String> nonTerminals;
    private final Set<String> terminals;
    private final List<Production> productions;
    private String startingSymbol;

    public Grammar() {
        nonTerminals = new LinkedList<>();
        terminals = new HashSet<>();
        productions = new ArrayList<>();
        getGrammarFromFile();
    }

    private void getGrammarFromFile() {
        try {
            int i = 0;
            for (String line : Files.readAllLines(Paths.get("C:\\UBB Sem 5\\FLAC\\labcuIulia\\src\\data\\g1.txt"))) {
                if (i <= 2){
                    String[] tokens = line.split(" ");
                    for (String token : tokens) {
                        if (i == 0) {
                            nonTerminals.add(token);
                        }
                        if (i == 1) {
                            terminals.add(token);
                        }
                        if (i == 2) {
                            startingSymbol = token;
                        }

                    }
                }

                if (i > 2) {
                    String[] tokens = line.split(" -> ");
                    List<List<String>> rules = new ArrayList<>();

                    for ( String rule: tokens[1].split(" \\| "))
                        rules.add(Arrays.asList(rule.split(" ")));
                    productions.add(new Production(tokens[0], rules));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Set<Production> getProductionsContainingNonterminal(String nonterminal) {
        Set<Production> productionsForNonterminal = new HashSet<>();
        for (Production production : productions) {
            for (List<String> rule : production.getRules())
                if (rule.contains(nonterminal))
                    productionsForNonterminal.add(production);
        }
        return productionsForNonterminal;
    }

    public List<Production> getProductionsForNonterminal(String nonterminal) {
        List<Production> productionsForNonterminal = new LinkedList<>();
        for (Production production : productions) {
            if (production.getStart().equals(nonterminal)) {
                productionsForNonterminal.add(production);
            }
        }
        return productionsForNonterminal;
    }

    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    public Set<String> getTerminals() {
        return terminals;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public boolean checkIfCFG(){
        var checkStartingSymbol = false;
        //List<String> lhs:  lista cu toate starturile din lista de production
        for(List<String> lhs : productions.iterator().next().getRules())
            if (lhs.contains(startingSymbol)) {
                checkStartingSymbol = true;
                break;
            }
        if(!checkStartingSymbol)
            return false;

        for(List<String> lhs : productions.iterator().next().getRules()){
            if(lhs.size()>1)
                return false;
            else if(!nonTerminals.contains(lhs.iterator().next()))
                return false;

            //rhs = ?? lista cu rules pentru un start din production
            List<List<String>> rhs = productions.listIterator().next().getRules().iterator().findAny(lhs);

            for(List<String> rh : rhs) {
                for (String r : rh) {
                    if(!(nonTerminals.contains(r) || terminals.contains(r) || r.equals("epsilon")))
                        return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        return "G =( " + nonTerminals.toString() + ", " + terminals.toString() + ", " +
                productions.toString() + ", " + startingSymbol + " )";

    }
}
