package se1.ws26.tests.uebung3;

import org.hbrs.se1.ws26.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws26.exercises.uebung2.Container;
import org.hbrs.se1.ws26.exercises.uebung2.ContainerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ContainerTest {

    private Container container;
    private ConcreteMember member1;
    private ConcreteMember member2;

    @BeforeEach
    public void setUp() {
        container = new Container();
        member1 = new ConcreteMember(1);
        member2 = new ConcreteMember(2);
    }

    @Test
    public void testAddMember() throws ContainerException {

        assertEquals(0, container.size());
        container.addMember(member1);
        assertEquals(1, container.size());
        container.addMember(member2);
        assertEquals(2, container.size());
        String ergebnis = container.deleteMember(1);
        assertEquals("Der Member wurde erfolgreich gelöscht", ergebnis);
        assertEquals(1, container.size());
        String ergebnis2 = container.deleteMember(99);
        assertEquals("Member nicht gefunden und daher nicht gelöscht", ergebnis2);
        assertEquals(1, container.size());
        container.deleteMember(2);
        assertEquals(0, container.size());
    }

}
