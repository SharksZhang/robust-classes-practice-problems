package test;

import model.Movie;
import model.MovieGoer;
import model.Ticket;
import model.TicketKiosk;

import model.exceptions.ShowingFullException;
import model.exceptions.UnderAgeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieGoerTest {

    private Movie m1, m2;
    private MovieGoer mg1, mg2;
    private Ticket t1, t2;
    private TicketKiosk tk;

    @Before
    public void setUp() {
        tk = new TicketKiosk("Scotiabank Theatre");
        mg1 = new MovieGoer("Tom Hanks", 28, tk);
        mg2 = new MovieGoer("Sarah Johnson", 17, tk);
        m1 = new Movie("Arrival", 19, 50);
        m2 = new Movie("Ratatouille", 0, 100);
        t1 = new Ticket(m1);
        t2 = new Ticket(m2);
    }

    @Test
    public void testGetters() {
        assertEquals(mg1.getName(),"Tom Hanks");
        assertEquals(mg2.getName(),"Sarah Johnson");
        assertEquals(mg1.getAge(),28);
        assertEquals(mg2.getAge(),17);
        assertEquals(mg1.getTicketKiosk(), tk);
        assertEquals(mg2.getTicketKiosk(), tk);
    }

    @Test
    public void testBuyTicketNoUnderAgeException() {
       // TODO: implement this test method where you do NOT expect the buyTicket() method to throw UnderAgeException
        try {
            mg1.buyTicket(m1);
        } catch (Exception e) {
            fail();
        }

    }

    @Test(expected = UnderAgeException.class)
    public void testBuyTicketUnderAgeException() throws ShowingFullException, UnderAgeException {
        // TODO: implement this test method where you DO expect the buyTicket() method to throw UnderAgeException
        mg2.buyTicket(m1);

    }


    @Test
    public void testBuyTicketNoShowingFullException() {
        // TODO: implement this test method where you do NOT expect the buyTicket() method to throw ShowingFullException
        try {
            mg1.buyTicket(m1);
        } catch (Exception e) {
            fail();
        }

    }

    @Test(expected = ShowingFullException.class)
    public void testBuyTicketShowingFullException() throws ShowingFullException, UnderAgeException {
        // TODO: implement this test method where you DO expect the buyTicket() method to throw ShowingFullException

        for (int i =0; i <50; i ++){
            mg1.buyTicket(m1);
        }
        mg1.buyTicket(m1);
    }


}