package com.upiiz.materias.controllers;

import com.upiiz.materias.dto.MateriaDTO;
import com.upiiz.materias.models.Materia;
import com.upiiz.materias.services.MateriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/materias")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    // Listado de materias
    @GetMapping
    public String listadoMaterias(Model model) {
        model.addAttribute("materias", materiaService.obtenerTodas());
        return "listado";
    }

    // Pantalla independiente: Crear
    @GetMapping("/nueva")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("materia", new MateriaDTO());
        return "formulario-crear";
    }

    // Acción de guardar
    @PostMapping("/guardar")
    public String guardarMateria(@ModelAttribute("materia") MateriaDTO materiaDTO) {
        materiaService.guardar(materiaDTO);
        return "redirect:/materias";
    }

    // Pantalla independiente: Editar (precargada)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Materia materia = materiaService.obtenerPorId(id);
        MateriaDTO dto = new MateriaDTO();
        dto.setId(materia.getId());
        dto.setNombre(materia.getNombre());
        dto.setCreditos(materia.getCreditos());

        model.addAttribute("materia", dto);
        return "formulario-actualizar";
    }

    // Acción de actualizar
    @PostMapping("/actualizar/{id}")
    public String actualizarMateria(@PathVariable Long id, @ModelAttribute("materia") MateriaDTO materiaDTO) {
        materiaService.actualizar(id, materiaDTO);
        return "redirect:/materias";
    }

    // Pantalla independiente: Confirmar Eliminación
    @GetMapping("/eliminar/{id}")
    public String mostrarFormularioEliminar(@PathVariable Long id, Model model) {
        model.addAttribute("materia", materiaService.obtenerPorId(id));
        return "formulario-eliminar";
    }

    // Acción de borrar
    @PostMapping("/borrar/{id}")
    public String borrarMateria(@PathVariable Long id) {
        materiaService.eliminar(id);
        return "redirect:/materias";
    }
}