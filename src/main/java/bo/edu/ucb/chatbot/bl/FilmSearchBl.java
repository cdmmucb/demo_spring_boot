package bo.edu.ucb.chatbot.bl;

import bo.edu.ucb.chatbot.dao.FilmDao;
import bo.edu.ucb.chatbot.dto.Film;
import bo.edu.ucb.chatbot.dto.Rental;
import bo.edu.ucb.chatbot.exception.SakilaException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmSearchBl {

    private final FilmDao filmDao;

    @Autowired
    public FilmSearchBl(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    public List<Film> findByTitle(String title) {
        if (title == null || title.trim().equals("")) {
            throw new SakilaException(403, "Bad request: The title parameter is mandatory and can't be null or empty");
        }
        return filmDao.findByTitle(title);
    }

    public List<Film> findByActor(String actor, String second) {
        if (actor == null || actor.trim().equals("") || second == null || second.trim().equals("")) {
            throw new SakilaException(403, "Bad request: The actor parameter is mandatory and can't be null or empty");
        }
        return filmDao.findByActor(actor, second);
    }

    public List<Film> findByTitleAndActor(String title, String actor, String second) {
        if (title == null || title.trim().equals("")||actor == null || actor.trim().equals("") || second == null || second.trim().equals("")) {
            throw new SakilaException(403, "Bad request: The title and actor parameters are mandatory and can't be null or empty");
        }
        return filmDao.findByTitleAndActor(title,actor, second);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<String> addRental(Rental obj) {
        if (obj == null || obj.getRentalDate().trim().equals("") || obj.getReturnDate().trim().equals("")
        || obj.getPaymentDate().trim().equals("") || obj.getCountry().trim().equals("")
                || obj.getFilms().size() > 4 || obj.getCustomerId()<1
        || obj.getAmount()<0 /*|| obj.getString("last_update")==null (dont know if this parameter is an option)*/
                ) {
            throw new SakilaException(403, "Bad request: Some parameter is null or empty");
        }
        for(int i=0;i<obj.getFilms().size();i++){
            if (obj.getFilms().get(i)==null
            ) {
                throw new SakilaException(403, "Bad request: A film_id is null or empty");
            }
        }
        return filmDao.addRental(obj);
    }

    public List<Film> getMovies(String country) {
        if (!country.trim().toUpperCase().equals("CANADA") && !country.trim().toUpperCase().equals("AUSTRALIA")) {
            throw new SakilaException(403, "Bad request: The country parameter is mandatory and can only be CANADA or AUSTRALIA");
        }
        return filmDao.getMovies(country);
    }

    public List<Film> getMoviesFiltered(String country, String type, String value) {
        if ((!country.trim().toUpperCase().equals("CANADA") && !country.trim().toUpperCase().equals("AUSTRALIA"))
        ||(!type.trim().toUpperCase().equals("TITULO") && !type.trim().toUpperCase().equals("ACTOR")) || value == null || value.trim().equals("")) {
            throw new SakilaException(403, "Bad request: The country, type and value parameters are mandatory and can't be null or empty");
        }
        return filmDao.getMoviesFiltered(country, type, value);
    }
}
