package org.hbrs.se1.ws26.exercises.uebung3;

import java.io.Serializable;

public class ConcreteMember implements Member, Serializable {
    private Integer id;
    public ConcreteMember(Integer ID) {
        this.id = ID;
    }
    public Integer getID() {
        return id;
    }

    public String toString(){
        return "Member (ID = " + id + ")";
    }
}
