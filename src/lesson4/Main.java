package lesson4;

import lesson1and2.FamilyTree;
import lesson1and2.People;
import lesson3.MyCollection;

public class Main {
    public static void main(String[] args) {
        FamilyTree searching = new FamilyTree(new People("Anya", true));
        searching.getCore().addParent(searching.add(new People("Marya", true)));
        searching.getCore().addParent(searching.add(new People("Anton", false)));
        searching.getCore().setPartner(searching.add(new People("Dima", false)));
        searching.setCoreByPeople(searching.getCore().getPartner());
        searching.getCore().addParent(searching.add(new People("Anastasiya", true)));
        searching.getCore().addParent(searching.add(new People("Aleksey", false)));
        searching.setCore(1);
        searching.getCore().addChild(new People("Loh Bolotny", false));
        searching.setCore(0);

        System.out.println("getFullInfo():");
        System.out.println(searching.getCore().getFullInfo());
        System.out.println("searching.getSiblings():");
        System.out.println(searching.getSiblings());
        System.out.println("searching.getSiblingsByGender(true):");
        System.out.println(searching.getSiblingsByGender(true));
        System.out.println("searching.getSiblingsByGender(false):");
        System.out.println(searching.getSiblingsByGender(false));
        System.out.println("searching.getMotherInLaw():");
        System.out.println(searching.getMotherInLaw());
    }
}
