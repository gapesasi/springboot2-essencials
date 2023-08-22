package springboot.essencials.springboot2essencials.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnimePostRequestBody {

//    @NotEmpty
//    @NotNull
    @NotBlank(message = "Anime can not be null or empty")
    private String name;
}
