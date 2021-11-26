package bo.edu.ucb.chatbot.api;

import bo.edu.ucb.chatbot.bl.FilmSearchBl;
import bo.edu.ucb.chatbot.dto.Film;
import bo.edu.ucb.chatbot.dto.Rental;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
public class FilmApi {

    FilmSearchBl filmSearchBl;

    @Autowired
    public FilmApi(FilmSearchBl filmSearchBl) {
        this.filmSearchBl = filmSearchBl;
    }

    @GetMapping(value = "/film/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> findBytTitle(@PathVariable(name = "title") String title) {
        System.out.println("Invocando al metodo GET!!!!!!!!!!!");
        return filmSearchBl.findByTitle(title);
    }

    @GetMapping(value = "/film/{actor}/{second}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> findByActor(@PathVariable(name = "actor") String actor, @PathVariable(name = "second") String second) {
        System.out.println("Invocando al metodo GET!!!!!!!!!!!");
        return filmSearchBl.findByActor(actor,second);
    }

    @GetMapping(value = "/film/{title}/{actor}/{second}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> findByTitleAndActor(@PathVariable(name = "title") String title, @PathVariable(name = "actor") String actor, @PathVariable(name = "second") String second) {
        System.out.println("Invocando al metodo GET!!!!!!!!!!!");
        return filmSearchBl.findByTitleAndActor(title,actor,second);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //HAY QUE MANDAR DIRECCION DE ENVIO???
    //HAY QUE MANDAR UN CORREO AL CLIENTE CON EL RESUMEN

    @PostMapping(value = "/rentals",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> addRental(@RequestBody Rental rental) {
        System.out.println("Invocando al metodo POST RENTAL!!!!!!!!!!!");
        return filmSearchBl.addRental(rental);
    }

    /*
    * El objeto json de este post seria:
    * rental_id (autoincrement)
    * rental_date datetime
    *inventory_id int (maximo 4 veces se ejecuta el insert en tabla rental)
    *customer_id int
    *return_date datetime
    *staff_id int (send country and FilmDao will assign staff accordingly)
    *last_update timestamp (default current)
    *
    *payment_id int (autoincrement)
    * customer_id int
    * staff_id int (1 if Canada; 2 if Australia)
    * rental_id int (hay que sacar de los inserts en rental asi que tambien seria un maximo de 4 veces este query)
    * amount decimal
    * payment_date datetime
    * last_update timestamp (default current)
    * */

    //este metodo seria para actualizar cuando se devuelve la pelicula. (talvez no se usa)
    /*@PutMapping(value = "/rentals", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateRental(@RequestBody JSONObject rental) {
        System.out.println("Invocando al metodo PUT RENTAL!!!!!!!!!!!");
        return filmSearchBl.updateRental(rental);
    }*/

    //https://picsum.photos/200 (mejor en frontend)
    @GetMapping(value = "/rentals/{country}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> getMovies(@PathVariable(name = "country") String country) {
        System.out.println("Invocando al metodo GET MOVIES!!!!!!!!!!!");
        return filmSearchBl.getMovies(country);
    }

    @GetMapping(value = "/rentals/{country}/{type}/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> getMoviesFiltered(@PathVariable(name = "country") String country, @PathVariable(name = "type") String type, @PathVariable(name = "value") String value) {
        System.out.println("Invocando al metodo GET MOVIES FILTERED!!!!!!!!!!!");
        return filmSearchBl.getMoviesFiltered(country, type, value);
    }

    //crear customer nombres, apellidos, email, direccion (direccion1,direccion2,distrito)
    //llenar la tabla customer y la tabla address

}
