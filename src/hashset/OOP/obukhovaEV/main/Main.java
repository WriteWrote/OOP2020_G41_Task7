package hashset.OOP.obukhovaEV.main;

import hashset.OOP.obukhovaEV.main.set.ISet;
import hashset.OOP.obukhovaEV.main.set.SimpleHashSet;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("Hello World!");
        Set<Integer> set = new HashSet<Integer>();
        ISet<String> mySet = new SimpleHashSet<>();
        mySet.add("A");
        mySet.add("B");
        mySet.add("S");
        mySet.add("Ab");
        mySet.add("Bc");

        mySet.remove("Ab");
        mySet.remove("S");
        mySet.remove("Bc");
    }
}