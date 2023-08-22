package springboot.essencials.springboot2essencials.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnimePutRequestBody {

    @NotBlank(message = "Id can not be null or empty")
    private Long id;

    @NotBlank(message = "Name can not be null or empty")
    private String name;
}
