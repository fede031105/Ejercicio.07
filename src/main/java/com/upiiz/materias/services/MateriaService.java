package com.upiiz.materias.services;

import com.upiiz.materias.dto.MateriaDTO;
import com.upiiz.materias.models.Materia;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MateriaService {
    // Almacenamiento en memoria
    private final List<Materia> materias = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Materia> obtenerTodas() {
        return materias;
    }

    public Materia obtenerPorId(Long id) {
        return materias.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void guardar(MateriaDTO materiaDTO) {
        Materia materia = new Materia(idCounter++, materiaDTO.getNombre(), materiaDTO.getCreditos());
        materias.add(materia);
    }

    public void actualizar(Long id, MateriaDTO materiaDTO) {
        Materia materiaExistente = obtenerPorId(id);
        if (materiaExistente != null) {
            materiaExistente.setNombre(materiaDTO.getNombre());
            materiaExistente.setCreditos(materiaDTO.getCreditos());
        }
    }

    public void eliminar(Long id) {
        materias.removeIf(m -> m.getId().equals(id));
    }
}