package se1.ws26.tests.uebung3;


import org.hbrs.se1.ws26.exercises.uebung3.ConcreteMember;
import org.hbrs.se1.ws26.exercises.uebung3.Container;
import org.hbrs.se1.ws26.exercises.uebung3.ContainerException;
import org.hbrs.se1.ws26.exercises.uebung3.Member;
import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceException;
import static org.junit.jupiter.api.Assertions.*;

import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContainerTest {
    private Container container;

    @BeforeEach
    void setUp() {
        // Hier muss der Container einmalig als Singleton instanziiert werden.
        container = Container.getInstance();
    }

    @Test
    void testMongoDBNotImplementedSolution() {
        container.setPersistenceStrategy(new PersistenceStrategyMongoDB<>());

        PersistenceException exception = assertThrows(PersistenceException.class, () -> container.store());
        assertEquals("MongoDB is not implemented!", exception.getMessage());
        assertEquals(PersistenceException.ExceptionType.ImplementationNotAvailable, exception.getExceptionType());
        // Set a strategy, which has not been implemented
        // container.setPersistenceStrategie( new PersistenceStrategyMongoDB<Member>() );

        // Testing store
        // Hinweis: Beim Aufruf der Methoden store() oder load() muss eine Exception vom Typ
        // PersistenceException zurückgegeben werden. Der ExceptionType lautet ImplementationNotAvailable
        // Die Message (abrufbar mit der Methode e.getMessage() ) muss einen eindeutigen Text haben, z.B.:
        // assertEquals( e.getMessage() , "MongoDB is not implemented!"  );
    }

    @Test
    void testNoStrategeySet() {
        container.setPersistenceStrategy(null);

        PersistenceException exception = assertThrows(PersistenceException.class, () -> container.store());

        assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet, exception.getExceptionType());

        //
            // container.setPersistenceStrategie(null);
            // container.store();
        // } catch (PersistenceException e) {
            // Bitte auch hier die Message und den ExceptionType prüfen.
        // }
    }

    @Test
    void testWrongLocationOfFile() {
        PersistenceStrategyStream<Member> strat = new PersistenceStrategyStream<>();

        strat.setLocation("C:\\");

        container.setPersistenceStrategy(strat);

        PersistenceException exception = assertThrows(PersistenceException.class, () -> container.store());

        assertEquals(PersistenceException.ExceptionType.ConnectionNotAvailable, exception.getExceptionType());
        // try {
           //  PersistenceStrategyStream<Member> strat = new PersistenceStrategyStream<Member>();
            // FileStreams do not like directories, so try this out ;-)
            // strat.setLocation("/Users/saschaalda/tmp");
            // container.setPersistenceStrategie( strat );
            // container.store();

        // } catch (PersistenceException e) {
            // Auch hier diverse Assertions bringen, um die Nachricht und den ExceptionType zu testen.
        // }
    }

    @Test
    void testStoreDeleteAndLoad() throws PersistenceException {
        container.deleteAllMembers();
        assertEquals(0, container.size());

        container.setPersistenceStrategy(new PersistenceStrategyStream<>());

        Member m1 = new ConcreteMember(1);

        try {
            container.addMember(m1);
        } catch (ContainerException e) {
            fail(e.getMessage());
        }
        assertEquals(1, container.size());

        System.out.println(container.size());
        container.store();

        container.deleteAllMembers();
        assertEquals(0, container.size());

        container.load();
        System.out.println(container.getCurrentList());

        assertEquals(1, container.size());
        // Testen Sie folgenden RoundTrip:
        // 1. Lösche alle Member-Objekte (Sicher ist sicher! Dazu die Methode deleteAllMember implementieren!)
        // 2. Setzen der Strategie
        // 3. Hinzufügen eines Member-Objekts
        // 4. Abspeichern
        // 5. Löschen des Member-Objekts
        // 6. Laden
        // Die Zustandsänderungen mittels der size() bitte stets testen!
    }

}