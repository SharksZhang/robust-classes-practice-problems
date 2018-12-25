package model;
import model.exceptions.ShowingFullException;
import model.exceptions.UnderAgeException;

import java.util.LinkedList;
import java.util.List;

public class TicketKiosk {

    private String name;
    private List<Movie> movies;

    // EFFECTS: TicketKiosk with name (name) is created
    public TicketKiosk(String name) {
        this.name = name;
        movies = new LinkedList<>();
    }

    // getters
    public String getName() {
        return name;
    }
    public List<Movie> getMovies() {
        return movies;
    }

    //EFFECTS:  adds the movie to movies, unless it is already in movies.
    //          if add is successful return true, otherwise return false.
    public boolean addMovie(Movie m) {
        if(!movies.contains(m)){
            movies.add(m);
            return true;
        }
        return false;
    }

    //EFFECTS: calls MovieGoer's buyTicket method, passing m as a parameter.
    public boolean sellTicket(MovieGoer mg, Movie m) {
        try {
            mg.buyTicket(m);
        } catch (ShowingFullException e) {
            return false;
        } catch (UnderAgeException e) {
            return false;
        }
        return true;
    }


}
