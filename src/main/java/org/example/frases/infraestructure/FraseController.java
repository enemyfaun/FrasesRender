package org.example.frases.infraestructure;

import org.example.frases.application.FraseService;
import org.example.frases.domain.entities.Frase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/frases")
public class FraseController {
    @Autowired
    private FraseService fraseService;

    @GetMapping("/frase")
    public List<Frase> findAll() {
        return fraseService.findAll();
    }

    @GetMapping("/frase/{id}")
    public Frase findById(@PathVariable Long id) {
        return fraseService.findById(id);
    }

    @PostMapping("/frase")
    @ResponseStatus(HttpStatus.CREATED)
    public Frase save(@RequestBody Frase f) {
        return fraseService.save(f);
    }

    @PutMapping("/frase/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Frase update(@PathVariable Long id, @RequestBody Frase f) {
        Frase fraseExistente = fraseService.findById(id);
        if (fraseExistente != null) {
            f.setId(id);
            return fraseService.save(f);
        } else {
            return null;
        }
    }

    @DeleteMapping("/frase/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        fraseService.deleteById(id);
    }

    @GetMapping("/frase/pdf")
    public ResponseEntity<InputStreamResource> getPdf(){
        List<Frase> frases = fraseService.findAll();
        ByteArrayInputStream bais = fraseService.reportePDF(frases);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=frases.pdf");
        return ResponseEntity.ok().headers(headers)
                             .contentType(MediaType.APPLICATION_PDF)
                             .body(new InputStreamResource(bais));
    }
}
