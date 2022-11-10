import java.util.ArrayList;

public class FamilyTree implements CloseRelative{
    private People Core;

    public People getCore() {
        return Core;
    }

    public void setCore(People core) {
        Core = core;
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
        return Core.getPartner();
    }
    public ArrayList<People> getParent() {
        return Core.getParent();
    }
    public ArrayList<People> getChildren() {
        return Core.getChildren();
    }
    public ArrayList<People> getSiblings() {
        ArrayList<People> siblings = null;
        for (People parent : Core.getParent()) {
            siblings = getUniquePeopleList(siblings, parent.getChildren());
        }
        siblings.remove(Core);
        return siblings;
    }
    public ArrayList<People> getSiblingsByGender(boolean gender) {
        ArrayList<People> siblings = getSiblings();
        siblings.removeIf(o -> o.getGender() != gender);
        siblings.remove(Core);
        return siblings;
    }
    public People getMotherInLaw() {
        People result = null;
        People partner = Core.getPartner();
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

    public FamilyTree (People core) {
        Core = core;
    }
}
