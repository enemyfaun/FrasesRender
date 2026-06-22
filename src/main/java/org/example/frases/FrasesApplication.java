package org.example.frases;

import org.example.frases.domain.entities.Frase;
import org.example.frases.domain.repository.FraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FrasesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FrasesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Frase f = new Frase();
        f.setId(1L);
//        f.setAutor("Uzumaki Naruto");
//        f.setTexto("No vivas con falsedades ni miedos, porque terminarás odiandote a ti mismo");
//        dao.save(f); //sirve para crear o actualizar
//        System.out.println(f);
//        dao.save(f);
//        IO.println(dao.findById(1L));
//        IO.println(dao.findAll());
//        dao.delete(f);

//        Frase f2 = Frase.builder()
//                .id(2L)
//                .autor("Yo")
//                .texto("Ya me quiero ir")
//                .build();
//        IO.println(f2);
    }
}

