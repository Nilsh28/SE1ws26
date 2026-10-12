package org.hbrs.se1.ws26.exercises.uebung2;

public class MainTest {
    public static void main(String[] args) {
    Container container = new Container();
    ConcreteMember m1 = new ConcreteMember(1);
    ConcreteMember m2 = new ConcreteMember(2);

        try { container.addMember(m1); container.addMember(m2); container.addMember(m1); }
        catch (ContainerException e) {
        System.out.println(e.getMessage());
        }
        container.dump();
        System.out.println(container.deleteMember(1));
        System.out.println(container.deleteMember(3));
        container.dump();
    }
}
