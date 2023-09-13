package springboot.essencials.springboot2essencials.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import springboot.essencials.springboot2essencials.domain.Anime;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate()
                .getForEntity("http://localhost:8080/animes/{id}", Anime.class, 3);
        log.info(entity);

        Anime object = new RestTemplate()
                .getForObject("http://localhost:8080/animes/{id}", Anime.class, 3);
        log.info(object);

        Anime[] animes = new RestTemplate()
                .getForObject("http://localhost:8080/animes/all", Anime[].class);
        log.info(Arrays.toString(animes));

        ResponseEntity<List<Anime>> exchange = new RestTemplate()
                .exchange("http://localhost:8080/animes/all", HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        }
                );
        log.info(exchange.getBody());

        //Aula 28 - RestTemplate POST
//        Anime kingdom = Anime.builder().name("kigndom").build();
//        Anime kingdomSaved = new RestTemplate()
//                .postForObject("http://localhost:8080/animes", kingdom, Anime.class);
//        log.info("Saved anime {}", kingdomSaved);

        Anime samuraiChamploo = Anime.builder().name("Samurai Champloo").build();
        ResponseEntity<Anime> samuraiChamplooSaved = new RestTemplate()
                .exchange("http://localhost:8080/animes",
                        HttpMethod.POST,
                        //Método Post requer o HttpEntity
                        new HttpEntity<>(samuraiChamploo, createJsonHeader()),
                        Anime.class);
        log.info("Saved anime {}", samuraiChamplooSaved);
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
