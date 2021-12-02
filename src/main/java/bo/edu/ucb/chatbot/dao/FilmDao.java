package bo.edu.ucb.chatbot.dao;

import bo.edu.ucb.chatbot.dto.Film;
import bo.edu.ucb.chatbot.dto.Rental;
import bo.edu.ucb.chatbot.dto.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmDao {

    private DataSource dataSource;

    @Autowired
    public FilmDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Film> findByTitle(String title) {
        List<Film> result = new ArrayList<>();
        String query = "SELECT f.film_id, " +
                "   f.title, " +
                "   f.description, " +
                "   f.release_year, " +
                "   l.name as language , " +
                "   ol.name as original_language, " +
                "   f.length, " +
                "   f.rating, " +
                "   f.special_features, " +
                "   f.last_update " +
                " FROM film f " +
                "     LEFT JOIN language l ON ( f.language_id = l.language_id) " +
                "     LEFT JOIN language ol ON ( f.original_language_id = ol.language_id) " +
                " WHERE " +
                "   UPPER(title) LIKE ( ? )" ;

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
                ) {
            
            pstmt.setString(1, "%"+title.toUpperCase()+ "%");
            System.out.println(query);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setLanguage("language");
                film.setOriginalLanguage("original_language");
                film.setLength(rs.getInt("length"));
                film.setRating(rs.getString("rating"));
                film.setSpecialFeatures(rs.getString("special_features"));
                java.sql.Date lastUpdate = rs.getDate("last_update");
                film.setLastUpdate(new java.util.Date(lastUpdate.getTime()));
                result.add(film);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }

    public List<Film> findByActor(String actor, String second) {
        List<Film> result = new ArrayList<>();
        String query = "SELECT f.film_id, " +
                "   f.title, " +
                "   f.description, " +
                "   f.release_year, " +
                "   l.name as language , " +
                "   ol.name as original_language, " +
                "   f.length, " +
                "   f.rating, " +
                "   f.special_features, " +
                "   f.last_update " +
                " FROM film f " +
                "     LEFT JOIN language l ON ( f.language_id = l.language_id) " +
                "     LEFT JOIN language ol ON ( f.original_language_id = ol.language_id) " +
                "     JOIN film_actor ll ON ( f.film_id = ll.film_id) " +
                "     JOIN actor olol ON ( ll.actor_id = olol.actor_id) " +
                " WHERE " +
                "   UPPER(olol.first_name) LIKE ( ? ) and UPPER(olol.last_name) LIKE ( ? )" ;

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println(query);
            pstmt.setString(1, "%"+actor.toUpperCase()+ "%");
            pstmt.setString(2, "%"+second.toUpperCase()+ "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setLanguage("language");
                film.setOriginalLanguage("original_language");
                film.setLength(rs.getInt("length"));
                film.setRating(rs.getString("rating"));
                film.setSpecialFeatures(rs.getString("special_features"));
                java.sql.Date lastUpdate = rs.getDate("last_update");
                film.setLastUpdate(new java.util.Date(lastUpdate.getTime()));
                result.add(film);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }

    public List<Film> findByTitleAndActor(String title, String actor, String second) {
        List<Film> result = new ArrayList<>();
        String query = "SELECT f.film_id, " +
                "   f.title, " +
                "   f.description, " +
                "   f.release_year, " +
               "   l.name as language , " +
               "   ol.name as original_language, " +
                "   f.length, " +
                "   f.rating, " +
                "   f.special_features, " +
                "   f.last_update " +
                " FROM film f " +
                "     LEFT JOIN language l ON ( f.language_id = l.language_id) " +
                "     LEFT JOIN language ol ON ( f.original_language_id = ol.language_id) " +
                "     JOIN film_actor ll ON ( f.film_id = ll.film_id) " +
                "     JOIN actor olol ON ( ll.actor_id = olol.actor_id) " +
                " WHERE " +
                "   UPPER(title) LIKE ( ? ) and UPPER(olol.first_name) LIKE ( ? ) and UPPER(olol.last_name) LIKE ( ? )" ;

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println(query);
            pstmt.setString(1, "%"+title.toUpperCase()+ "%");
            pstmt.setString(2, "%"+actor.toUpperCase()+ "%");
            pstmt.setString(3, "%"+second.toUpperCase()+ "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setLanguage("language");
                film.setOriginalLanguage("original_language");
                film.setLength(rs.getInt("length"));
                film.setRating(rs.getString("rating"));
                film.setSpecialFeatures(rs.getString("special_features"));
                java.sql.Date lastUpdate = rs.getDate("last_update");
                film.setLastUpdate(new java.util.Date(lastUpdate.getTime()));
                result.add(film);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public JSONObject addRental(Rental obj) {
        //check if not agotado -> film_id has a lot of inventory_ids; film_id is movie name, inventory_id is one copy of the film
        //List<String> result = new ArrayList<>();
        //insert en rental para cada pelicula (inventory_id varia)
        //String query = "INSERT INTO RENTAL VALUES (null,rental_date,inventory_id,customer_id,return_date,staff_id,last_update o null)";
        String query = "INSERT INTO rental(rental_date, inventory_id, customer_id, staff_id) VALUES ((?),(?),(?),(?))";
        //luego recuperar los rental_id de todos los inserts y aniadir un registro a payment para cada uno
        //String query2 = "INSERT INTO PAYMENT VALUES (null,customer_id,staff_id,rental_id,amount,payment_date,last_update o null),";
        String query2 = "INSERT INTO payment(customer_id, staff_id, rental_id, amount,  payment_date) VALUES ((?),(?),LAST_INSERT_ID(),(?),(?))";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
                PreparedStatement pstmt2 =  conn.prepareStatement(query2);
        ) {
            System.out.println(query);
            if(obj.getCountry().toUpperCase()=="Canada"){
                //mandar staff id 1
                pstmt.setInt(4, 1);
                pstmt2.setInt(2, 1);
            }else{
                //mandar staff id 2 (Australia)
                pstmt.setInt(4, 2);
                pstmt2.setInt(2, 2);
            }
            pstmt.setString(1, obj.getRentalDate().toUpperCase());
            pstmt.setInt(3, obj.getCustomerId());


            pstmt2.setInt(1, obj.getCustomerId());
            pstmt2.setFloat(3, obj.getAmount());
            pstmt2.setString(4, obj.getPaymentDate().toUpperCase());
          
            if(obj.getFilm1()!=-1){
                pstmt.setInt(2, obj.getFilm1());
                pstmt.executeUpdate();
                    pstmt2.executeUpdate();
            }
            if(obj.getFilm2()!=-1){
                pstmt.setInt(2, obj.getFilm2());
                pstmt.executeUpdate();
                    pstmt2.executeUpdate();
            }
            if(obj.getFilm3()!=-1){
                pstmt.setInt(2, obj.getFilm3());
                pstmt.executeUpdate();
                    pstmt2.executeUpdate();
            }
            if(obj.getFilm4()!=-1){
                pstmt.setInt(2, obj.getFilm4());
                pstmt.executeUpdate();
                    pstmt2.executeUpdate();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        //JSONObject a = new JSONObject();
        return new JSONObject();
    }


// DO NOT FORGET TO RETURN MOVIE STATUS (AVAILABLE OR NOT) DEPENDING OF INVENTORY TABLE IN THE TWO FOLLOWING METHODS.
    // MUESTRA LAS ULTIMAS 10 PELICULAS INCORPORADAS AL CATALOGO???
    //SECCIONES: ESTRENOS, LAS MAS RENTADAS EN LA ULTIMA SEMANA Y LAS MAS RENTADAS DE TODOS LOS TIEMPOS???
    public List<Film> getMovies(String country) {
        List<Film> result = new ArrayList<>();
        //SELECT distinct f.inventory_id,inventory_in_stock(f.inventory_id) from inventory f;
        String query = "SELECT distinct inv.inventory_id,inventory_in_stock(inv.inventory_id) as disponible,f.film_id, " +
                "   f.title, " +
                "   f.description, " +
                "   f.release_year, " +
                "   l.name as language , " +
                "   ol.name as original_language, " +
                "   f.length, " +
                "   f.rating, " +
                "   f.special_features, " +
                "   f.last_update " +
                " FROM film f " +
                "     LEFT JOIN language l ON ( f.language_id = l.language_id) " +
                "     LEFT JOIN language ol ON ( f.original_language_id = ol.language_id) " +
                "     LEFT JOIN film_actor ll ON ( f.film_id = ll.film_id) " +
                "     LEFT JOIN actor olol ON ( ll.actor_id = olol.actor_id) " +
                "     LEFT JOIN inventory inv ON ( f.film_id = inv.film_id) " +
                "     LEFT JOIN store st ON ( st.store_id = inv.store_id) " +
                "     LEFT JOIN address ad ON ( ad.address_id = st.address_id) " +
                "     LEFT JOIN city ci ON ( ci.city_id = ad.city_id) " +
                "     LEFT JOIN country co ON ( co.country_id = ci.country_id) " +
                " WHERE " +
                "   UPPER(co.country) LIKE ( ? ) ";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            
            pstmt.setString(1, "%" + country.toUpperCase() + "%");
            System.out.println(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Film film = new Film();
                film.setInventoryId(rs.getInt("inventory_id"));
                film.setDisponible(rs.getBoolean("disponible"));
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setLanguage("language");
                film.setOriginalLanguage("original_language");
                film.setLength(rs.getInt("length"));
                film.setRating(rs.getString("rating"));
                film.setSpecialFeatures(rs.getString("special_features"));
                java.sql.Date lastUpdate = rs.getDate("last_update");
                film.setLastUpdate(new java.util.Date(lastUpdate.getTime()));
                result.add(film);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }

    public List<Film> getMoviesFiltered(String country, String type, String value) {
        List<Film> result = new ArrayList<>();
        String query = "SELECT distinct inv.inventory_id,inventory_in_stock(inv.inventory_id) as disponible, f.film_id, " +
                "   f.title, " +
                "   f.description, " +
                "   f.release_year, " +
                "   l.name as language , " +
                "   ol.name as original_language, " +
                "   f.length, " +
                "   f.rating, " +
                "   f.special_features, " +
                "   f.last_update " +
                " FROM film f " +
                "     LEFT JOIN language l ON ( f.language_id = l.language_id) " +
                "     LEFT JOIN language ol ON ( f.original_language_id = ol.language_id) " +
                "     LEFT JOIN film_actor ll ON ( f.film_id = ll.film_id) " +
                "     LEFT JOIN actor olol ON ( ll.actor_id = olol.actor_id) " +
                "     LEFT JOIN inventory inv ON ( f.film_id = inv.film_id) " +
                "     LEFT JOIN store st ON ( st.store_id = inv.store_id) " +
                "     LEFT JOIN address ad ON ( ad.address_id = st.address_id) " +
                "     LEFT JOIN city ci ON ( ci.city_id = ad.city_id) " +
                "     LEFT JOIN country co ON ( co.country_id = ci.country_id) " +
                " WHERE " +
                " UPPER(co.country) LIKE ( ? ) ";
        if(type.trim().toUpperCase().equals("TITULO")){
            query+="and UPPER(f.title) LIKE ( ? )";
        }else{
            query+="and UPPER(olol.first_name) LIKE ( ? ) or UPPER(olol.last_name) LIKE ( ? )";
        }
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            pstmt.setString(1, "%"+country.toUpperCase()+ "%");
            pstmt.setString(2, "%"+value.toUpperCase()+ "%");
            if(type.trim().toUpperCase().equals("TITULO")){
                //DO NOTHING
            }else{
                pstmt.setString(3, "%"+value.toUpperCase()+ "%");
            }
            System.out.println(country.toUpperCase()+type.toUpperCase()+value.toUpperCase());
            System.out.println(query);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setInventoryId(rs.getInt("inventory_id"));
                film.setDisponible(rs.getBoolean("disponible"));
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setLanguage("language");
                film.setOriginalLanguage("original_language");
                film.setLength(rs.getInt("length"));
                film.setRating(rs.getString("rating"));
                film.setSpecialFeatures(rs.getString("special_features"));
                java.sql.Date lastUpdate = rs.getDate("last_update");
                film.setLastUpdate(new java.util.Date(lastUpdate.getTime()));
                result.add(film);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }

    public User getUser(String mail) {
        User user = new User();
        String query = "SELECT f.customer_id, " +
                "   f.store_id, " +
                "   f.first_name, " +
                "   f.last_name, " +
                "   f.email, " +
                "   f.address_id, " +
                "   f.active " +
                " FROM customer f " +
                " WHERE " +
                "   UPPER(f.email) LIKE ( ? )" ;
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println(query);
            pstmt.setString(1, mail.toUpperCase());
            ResultSet rs = null;
                rs=pstmt.executeQuery();
                while(rs.next()) {
                    user.setActive(rs.getBoolean("active"));
                    user.setAddressId(rs.getInt("address_id"));
                    user.setEmail(rs.getString("email"));
                    user.setCustomerId(rs.getInt("customer_id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setStoreId(rs.getInt("store_id"));
                }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return user;
    }
}
