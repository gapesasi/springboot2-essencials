package springboot.essencials.springboot2essencials.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.essencials.springboot2essencials.domain.Anime;
import springboot.essencials.springboot2essencials.requests.AnimePostRequestBody;
import springboot.essencials.springboot2essencials.requests.AnimePutRequestBody;
import springboot.essencials.springboot2essencials.service.AnimeService;
import springboot.essencials.springboot2essencials.util.DateUtil;

import java.util.List;

@RestController
@RequestMapping("animes")
@Log4j2
//@AllArgsConstructor
@RequiredArgsConstructor
public class AnimeController {
    //Injeção de dependências
//    @Autowired
    private final DateUtil dateUtil;
    private final AnimeService animeService;
//    public AnimeController(DateUtil dateUtil) {
//        this.dateUtil = dateUtil;
//    }

    //Duas formas de criar os endpoints: RequestMapping e GetMapping(Específico pro método)
    //@RequestMapping(method = RequestMethod.GET, path = "list")
    @GetMapping
    public ResponseEntity<Page<Anime>> list(Pageable pageable) {
        //       log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody) {
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Anime> delete(@PathVariable long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Anime> replace(@RequestBody AnimePutRequestBody animePutRequestBody){
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
