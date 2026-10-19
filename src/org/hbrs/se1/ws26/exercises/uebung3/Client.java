package org.hbrs.se1.ws26.exercises.uebung3;
import org.hbrs.se1.ws26.exercises.uebung3.ConcreteMember;
import org.hbrs.se1.ws26.exercises.uebung3.ContainerException;

import java.util.List;

public class Client {
    public static void main(String[] args) {
        Container container = Container.getInstance();
        ConcreteMember m1 = new ConcreteMember(1);
        ConcreteMember m2 = new ConcreteMember(2);

        try{
            container.addMember(m1);
            container.addMember(m2);
        } catch (ContainerException e){
            System.out.println(e.getMessage());
        }

        List<Member> liste = container.getCurrentList();

        MemberView view = new MemberView();
        view.dump(liste);
    }
}
