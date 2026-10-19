package org.hbrs.se1.ws26.exercises.uebung3;

import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private List<Member> members = new ArrayList<>();
    private static Container container;
    private PersistenceStrategy <Member> persistenceStrategy;

    private Container(){

    }


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
        for (Member member : members) {
            if (member.getID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public int size(){
        return  members.size();
    }

    public static Container getInstance(){
        if(container == null){
            container =  new Container();
        }
        return container;
    }

    public void setPersistenceStrategy(PersistenceStrategy <Member> persistenceStrategy){
        this.persistenceStrategy = persistenceStrategy;
    }

    public void store() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es wurde keine Strategie gesetzt.");
        }
        persistenceStrategy.save(members);

    }

    public void load() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es wurde keine Strategie gesetzt.");
        }
        members = persistenceStrategy.load();
    }

    public void deleteAllMembers(){
        members.clear();
    }

    public List<Member> getCurrentList(){
        return members;
    }

}
