package org.hbrs.se1.ws26.exercises.uebung3;

import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceStrategyStream;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
    Container container = Container.getInstance();
    Container container2 = Container.getInstance();
    ConcreteMember m1 = new ConcreteMember(1);
    ConcreteMember m2 = new ConcreteMember(2);

        try { container.addMember(m1);
              container.addMember(m2);

              container.setPersistenceStrategy(new PersistenceStrategyStream<>());
              container.store();

              System.out.println("Vor dem löschen: " + container.size());

              container.deleteAllMembers();
              System.out.println("Nach dem löschen: " + container.size());

              container.load();
              System.out.println("Nach dem laden: " + container.size());
        }
        catch (ContainerException | PersistenceException e) {
        System.out.println(e.getMessage());
        }

        List<Member> liste = container.getCurrentList();

        MemberView view = new MemberView();
        view.dump(liste);
        System.out.println(container.deleteMember(1));
        System.out.println(container.deleteMember(3));
        view.dump(liste);
        System.out.println(container == container2);


    }
}
