package lesson1and2;

import lesson3.MyCollection;

import java.util.ArrayList;

public class FamilyTree implements CloseRelative{
    private int Core;
    private MyCollection<People> PeopleList;

    public People getCore() {
        return PeopleList.get(Core);
    }
    public void setCore(int core) {
        Core = core;
    }
    public void setCoreByPeople(People p) {
        Core = getIdByPeople(p);
    }
    public People add(People p) {
        PeopleList.add(p);
        return p;
    }

    public People getPeopleById(int id) {
        return PeopleList.get(id);
    }

    public int getIdByPeople(People people) {
        return PeopleList.indexOf(people);
    }

    private ArrayList<People> getUniquePeopleList(ArrayList<People> firstList, ArrayList<People> secondList) {
        ArrayList<People> result = new ArrayList<>();
        if (firstList != null) {
            result.addAll(firstList);
        }
        if (firstList == null || (secondList != null && firstList != secondList && !firstList.equals(secondList))) {
            for (People item : secondList) {
                if (!result.contains(item)) {
                    result.add(item);
                }
            }
        }
        return result;
    }
    public People getPartner() {
        return getCore().getPartner();
    }
    public ArrayList<People> getParent() {
        return getCore().getParent();
    }
    public ArrayList<People> getChildren() {
        return getCore().getChildren();
    }
    public ArrayList<People> getSiblings() {
        ArrayList<People> siblings = null;
        for (People parent : getCore().getParent()) {
            siblings = getUniquePeopleList(siblings, parent.getChildren());
        }
        siblings.remove(getCore());
        return siblings;
    }
    public ArrayList<People> getSiblingsByGender(boolean gender) {
        ArrayList<People> siblings = getSiblings();
        siblings.removeIf(o -> o.getGender() != gender);
        siblings.remove(getCore());
        return siblings;
    }
    public People getMotherInLaw() {
        People result = null;
        People partner = getCore().getPartner();
        if (partner != null) {
            for (People item : partner.getParent()) {
                if (item.getGender() == true) {
                    result = item;
                    break;
                }
            }
        }
        return result;
    }

    public FamilyTree (People p) {
        Core = 0;
        PeopleList = new MyCollection<>();
        PeopleList.add(p);
    }
}
