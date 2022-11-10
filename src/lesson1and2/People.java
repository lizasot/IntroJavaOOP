package lesson1and2;

import java.util.ArrayList;

public class People implements CloseRelative{
    private String Name;
    private Boolean Gender; //false - мужчина, true - женщина
    private People Partner;
    private ArrayList<People> Parent;
    private ArrayList<People> Children;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Boolean getGender() {
        return Gender;
    }

    public void setGender(Boolean gender) {
        Gender = gender;
    }

    public People getPartner() {
        return Partner;
    }

    public void setPartner(People p) {
        Partner = p;
        if (p.getPartner() != this) {
            p.setPartner(this);
        }
    }

    public ArrayList<People> getParent() {
        return Parent;
    }

    public void addParent(People p) {
        if (Parent == null) {
            Parent = new ArrayList<People>();
        }
        Parent.add(p);
        if (p.getChildren() == null || !p.getChildren().contains(this)) {
            p.addChild(this);
        }
    }

    public ArrayList<People> getChildren() {
        return Children;
    }

    public void addChild(People p) {
        if (Children == null) {
            Children = new ArrayList<People>();
        }
        Children.add(p);
        if (p.getParent() == null || !p.getParent().contains(this)) {
            p.addParent(this);
        }
    }

    public String getFullInfo() {
        StringBuilder result = new StringBuilder(20);
        result.append(this.toString());
        result.append("\nПартнёр: ");
        if (Partner == null) {
            result.append("—");
        } else {
            result.append(Partner.toString());
        }
        result.append("\nРодители: ");
        if (Parent == null) {
            result.append("—");
        } else {
            for (People people: Parent) {
                result.append(people.toString());
                result.append("; ");
            }
        }
        result.append("\nДети: ");
        if (Children == null) {
            result.append("—");
        } else {
            for (People people: Children) {
                result.append(people.toString());
                result.append("; ");
            }
        }
        result.append("\n");
        return result.toString();
    }

    @Override
    public String toString() {
        return String.format("Имя: %s, Пол: %s", Name, (Gender ?  "Ж" : "М"));
    }

    public People(String name) {
        Name = name;
        Gender = false;
        Partner = null;
        Parent = null;
        Children = null;
    }

    public People(String name, Boolean gender) {
        Name = name;
        Gender = gender;
        Partner = null;
        Parent = null;
        Children = null;
    }

    public People(String name, Boolean gender, People partner, ArrayList<People> parent, ArrayList<People> children) {
        Name = name;
        Gender = gender;
        Partner = partner;
        Parent = parent;
        Children = children;
    }
}
