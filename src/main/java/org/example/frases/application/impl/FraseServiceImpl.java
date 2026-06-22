package org.example.frases.application.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.frases.application.FraseService;
import org.example.frases.domain.entities.Frase;
import org.example.frases.domain.repository.FraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FraseServiceImpl implements FraseService {
    @Autowired
    private FraseRepository dao;

    @Override
    @Transactional(readOnly = true)
    public List<Frase> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Frase findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Frase save(Frase f) {
        return dao.save(f);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public ByteArrayInputStream reportePDF(List<Frase> frases) {
        Document documento = new Document();
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        try{
            PdfWriter.getInstance(documento, salida);
            documento.open();
            Font tipoLetra = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLUE);
            Paragraph parrafo = new Paragraph("Lista de frases", tipoLetra);
            documento.add(parrafo);
            documento.add(Chunk.NEWLINE);

            Font tipoLetraTexto = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLUE);
            PdfPTable tabla = new PdfPTable(3);
            Stream.of("ID", "FRASE", "AUTOR").forEach(titulo -> {
                PdfPCell encabezadosTabla = new PdfPCell(
                        new Phrase(titulo, tipoLetraTexto)
                );
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,9, BaseColor.BLUE);
                encabezadosTabla.setHorizontalAlignment(Element.ALIGN_CENTER);
                encabezadosTabla.setVerticalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(encabezadosTabla);
            });
            for(Frase f : frases){
                PdfPCell celdaIdFrase = new PdfPCell(
                        new Phrase(String.valueOf(f.getId()), tipoLetraTexto)
                );
                celdaIdFrase.setHorizontalAlignment(Element.ALIGN_LEFT);
                celdaIdFrase.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabla.addCell(celdaIdFrase);

                PdfPCell celdaFrase = new PdfPCell(
                        new Phrase(f.getTexto(), tipoLetraTexto)
                );
                celdaFrase.setHorizontalAlignment(Element.ALIGN_LEFT);
                celdaFrase.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabla.addCell(celdaFrase);

                PdfPCell celdaAutor = new PdfPCell(
                        new Phrase(f.getAutor(), tipoLetraTexto)
                );
                celdaAutor.setHorizontalAlignment(Element.ALIGN_LEFT);
                celdaAutor.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabla.addCell(celdaAutor);
            }

            documento.add(tabla);
            documento.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
            return new ByteArrayInputStream(salida.toByteArray());
    }

}
