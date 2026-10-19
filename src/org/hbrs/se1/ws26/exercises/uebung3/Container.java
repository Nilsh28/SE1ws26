package org.hbrs.se1.ws26.exercises.uebung3;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private List<Member> members = new ArrayList<Member>();
    private static Container container;


    public void addMember (Member member) throws ContainerException {
        if(contains(member.getID())){throw new ContainerException
                ("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!" );}
        members.add(member);
    }

    public String deleteMember(Integer id) {
        for (int i = 0; i < members.size(); i++) {
            if(members.get(i).getID().equals(id)) {
                members.remove(i);
                return "Der Member wurde erfolgreich gelöscht";
            }
        }
        return "Member nicht gefunden und daher nicht gelöscht";
    }

    private boolean contains(Integer id) {
        for (int i = 0; i < members.size(); i++) {
            if(members.get(i).getID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void dump(){
        for (int i = 0; i < members.size(); i++) {
            System.out.println(members.get(i).toString());
        }
    }

    public int size(){
        return  members.size();
    }


}
