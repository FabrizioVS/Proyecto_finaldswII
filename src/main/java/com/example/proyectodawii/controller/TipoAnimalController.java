package com.example.proyectodawii.controller;

import com.example.proyectodawii.model.tipo_animal;
import com.example.proyectodawii.service.TipoAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/tipoAnimal")
public class TipoAnimalController {

    @Autowired
    private TipoAnimalService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listar()
    {
        return service.listarTipoAnimal();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> listarPorId(@PathVariable Long id)
    {
        return  service.listarTipoanimalPorId(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregarTipoAnimal(@RequestBody tipo_animal tipoAnimal)
    {
        return  service.agregarTipoAnimal(tipoAnimal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editarTipoAnimal(@RequestBody tipo_animal tipoAnimal, @PathVariable Long id)
    {
        return  service.editarTipoAnimal(tipoAnimal, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarTipoAnimal(@PathVariable Long id)
    {
        return  service.eliminarTipoAnimal(id);
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Object>> eliminarTipoAnimalLogico(@PathVariable Long id)
    {
        return  service.eliminarTipoAnimalEstado(id);
    }

    @GetMapping("/estado")
    public ResponseEntity<Map<String, Object>> listarTipoAnimalEstado()
    {
        return service.allTipoAnimalEstado();
    }
}
