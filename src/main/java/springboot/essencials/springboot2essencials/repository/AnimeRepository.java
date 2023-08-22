package springboot.essencials.springboot2essencials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.essencials.springboot2essencials.domain.Anime;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

    List<Anime> findByName(String name);
}
