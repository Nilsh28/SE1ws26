package org.hbrs.se1.ws26.exercises.uebung2;

public class ConcreteMember implements Member {
    private Integer ID;
    public ConcreteMember(Integer ID) {
        this.ID = ID;
    }
    public Integer getID() {
        return ID;
    };

    public String toString(){
        return "Member (ID = " + ID + ")";
    }
}
