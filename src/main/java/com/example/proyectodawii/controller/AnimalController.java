package com.example.proyectodawii.controller;

import com.example.proyectodawii.model.Animal;
import com.example.proyectodawii.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/animal")
public class AnimalController {
    @Autowired
    private AnimalService serviceAni;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarAll() {
        return serviceAni.ListarAnimales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> LisAniPorId(@PathVariable Long id) {
        return serviceAni.listarAnimalesPorId(id);
    }

    @GetMapping("/tipo/{idTipo}")
    public ResponseEntity<Map<String, Object>> listarAnimalesPorIdTipo(@PathVariable Long idTipo) {
        return serviceAni.listarPorIdTipo(idTipo);
    }

    @PostMapping
    ResponseEntity<Map<String, Object>> AgregarAnimales(@RequestBody Animal animal) {
        return serviceAni.agregarAnimales(animal);
    }

    @PutMapping("/{id}")
    ResponseEntity<Map<String, Object>> editarAnimales(@RequestBody Animal animal, @PathVariable Long id) {
        return serviceAni.editarAnimales(animal, id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Object>> eliminarAnimales(@PathVariable Long id) {
        return serviceAni.eliminarAnimales(id);
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Object>> eliminarAnimalesLogico(@PathVariable Long id)
    {
        return serviceAni.eliminarAnimalEstado(id);
    }

    @GetMapping("/estado")
    public ResponseEntity<Map<String, Object>> listarAnimalesEstado()
    {
        return serviceAni.allAnimalEstado();
    }


}
