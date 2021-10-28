package bo.edu.ucb.chatbot.bl;

import bo.edu.ucb.chatbot.dto.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Procesar la lógica de negocio de las conversaciones del bo.
 * Recibe los mensajes enviados por el usuario como String.
 * Y restorna una List<String> como respuesta a estos mensajes
 * Con el proposito de hacer High Cohesion esta clase no debería utilizar ningun API de Telegram
 */

@Component
public class BotFilmSearchBl {

    private FilmSearchBl filmSearchBl;

    @Autowired
    public BotFilmSearchBl(FilmSearchBl filmSearchBl) {
        this.filmSearchBl = filmSearchBl;
    }

    public List<String> processMessage(String message) {
        List<String> result = new ArrayList<>();
        List<Film> filmList = null;
        String aux = message.toUpperCase();
        boolean empiezaT = false;
        if (aux.charAt(0)=='T'){
            empiezaT=true;
        }
        aux = aux.replace("TITULO=",",");
        aux = aux.replace("ACTOR=",",");
        aux = aux.replace(" ",",");
        aux = aux.replace("|",",");
        int count = 0;
        for (int i = 0; i < aux.length(); i++) {
            if (aux.charAt(i) == ',') {
                count++;
            }
        }
        List<String> items = Arrays.asList(aux.split(","));

     if (count ==4 && empiezaT==true){
            filmList =  filmSearchBl.findByTitleAndActor(items.get(1),items.get(3),items.get(4));
        }else{
            if(count == 1 && empiezaT==true){
                filmList =  filmSearchBl.findByTitle(items.get(1));

            }else{
                if(count==2 && empiezaT==false){
                    filmList =  filmSearchBl.findByActor(items.get(1),items.get(2));
                }else{
                    if(count==4 && empiezaT==false){
                        filmList = filmSearchBl.findByTitleAndActor(items.get(4),items.get(1),items.get(2));
                    }else{
                        result.add("Formato incorrecto " + message);
                        return result;
                    }

                }
            }
        }


       if (!filmList.isEmpty()) {
            result.add("Encontré las siguientes películas:");
            for (Film film : filmList) {
                result.add(film.toString());
            }
        } else {
            result.add("No encontré ninguna película para: " + message);
        }

        return result;
    }
}
