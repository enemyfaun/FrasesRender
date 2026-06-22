package org.example.frases.application;

import org.example.frases.domain.entities.Frase;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface FraseService {
    public List<Frase> findAll();
    public Frase findById(Long id);
    // este metodo se usara para guardar o actualizar
    public Frase save(Frase f);
    public void deleteById(Long id);

    public ByteArrayInputStream reportePDF(List<Frase> frases);
}
