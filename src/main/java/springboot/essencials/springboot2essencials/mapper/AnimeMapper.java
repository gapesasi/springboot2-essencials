package springboot.essencials.springboot2essencials.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import springboot.essencials.springboot2essencials.domain.Anime;
import springboot.essencials.springboot2essencials.requests.AnimePostRequestBody;
import springboot.essencials.springboot2essencials.requests.AnimePutRequestBody;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);
    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);
}
