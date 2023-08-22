package springboot.essencials.springboot2essencials.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springboot.essencials.springboot2essencials.domain.Anime;
import springboot.essencials.springboot2essencials.exception.BadRequestException;
import springboot.essencials.springboot2essencials.mapper.AnimeMapper;
import springboot.essencials.springboot2essencials.repository.AnimeRepository;
import springboot.essencials.springboot2essencials.requests.AnimePostRequestBody;
import springboot.essencials.springboot2essencials.requests.AnimePutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable){
        return animeRepository.findAll(pageable);
    }

    public Anime findByIdOrThrowBadRequestException(long id){
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not Found"));
    }

    @Transactional
//    @Transactional(rollbackOn = Exception.class)
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));

//        Anime save = animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
//        if(true){
//            throw new Exception("BAD CODE");
//        }
//        return save;
    }

    public void delete(long id){
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody){
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());

        animeRepository.save(anime);
    }

    public List<Anime> findByName(String name){
        return animeRepository.findByName(name);
    }
}
