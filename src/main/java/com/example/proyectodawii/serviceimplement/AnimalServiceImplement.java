package com.example.proyectodawii.serviceimplement;

import com.example.proyectodawii.model.Animal;
import com.example.proyectodawii.model.Tipo;
import com.example.proyectodawii.repository.AnimalRepository;
import com.example.proyectodawii.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnimalServiceImplement implements AnimalService {

    @Autowired
    private AnimalRepository daoAnimal;

    @Override
    public ResponseEntity<Map<String, Object>> ListarAnimales() {
        Map<String, Object> res = new HashMap<>();
        List<Animal> ani = daoAnimal.findAll();
        if (!ani.isEmpty()) {
            res.put("mensaje", "Lista de Animales");
            res.put("Animales", ani);
            res.put("status", HttpStatus.OK);
            res.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } else {
            res.put("mensaje", "No existen registros");
            res.put("status", HttpStatus.NOT_FOUND);
            res.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarAnimalesPorId(Long id) {
        Map<String, Object> res = new HashMap<>();
        Optional<Animal> aniExist = daoAnimal.findById(id);

        if (aniExist.isPresent()) {
            res.put("Animales", aniExist);
            res.put("mensaje", "Busqueda Corrrecta");
            res.put("status", HttpStatus.OK);
            res.put("fecha", new Date());
            return ResponseEntity.ok().body(res);
        } else {
            res.put("mensaje", "Sin registros con ID: " + id);
            res.put("status", HttpStatus.NOT_FOUND);
            res.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> agregarAnimales(Animal animal) {
        Map<String, Object> res = new HashMap<>();
        daoAnimal.save(animal);
        res.put("animal", animal);
        res.put("mensaje", "Registro Agregado");
        res.put("status", HttpStatus.CREATED);
        res.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarAnimales(Animal animal, Long id) {
       Map<String, Object> res = new HashMap<>();
       Optional<Animal> aniExist = daoAnimal.findById(id);
       if (aniExist.isPresent()) {

           Animal ani = aniExist.get();
           ani.setNombre(animal.getNombre());
           ani.setUrl(animal.getUrl());
           ani.setEdad(animal.getEdad());
           ani.setEstado(animal.getEstado());
           ani.setCantCuidadores(animal.getCantCuidadores());
           Tipo nuevoTipo = new Tipo();
           nuevoTipo.setId(animal.getTipo().getId());
           ani.setTipo(nuevoTipo);

           daoAnimal.save(ani);
           res.put("animal", ani);
           res.put("mensaje", "Datos del tipo de insumo modificado");
           res.put("status", HttpStatus.CREATED);
           res.put("fecha", new Date());
           return ResponseEntity.status(HttpStatus.CREATED).body(res);
       } else {
           res.put("mensaje", "Sin registros con ID: " + id);
           res.put("status", HttpStatus.NOT_FOUND);
           res.put("fecha", new Date());
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
       }

    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarAnimales(Long id) {
        Map<String, Object> res = new HashMap<>();
        Optional<Animal> aniExist = daoAnimal.findById(id);

        if (aniExist.isPresent()) {
            Animal ani = aniExist.get();
            daoAnimal.delete(ani);
            res.put("mensaje", "Eliminado correctamente");
            res.put("status", HttpStatus.NO_CONTENT);
            res.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(res);
        } else {
            res.put("mensaje", "Sin registros con ID: " + id);
            res.put("status", HttpStatus.NOT_FOUND);
            res.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarAnimalEstado(Long id) {
        Map<String, Object> res = new HashMap<>();
        Optional<Animal> aniExist = daoAnimal.findById(id);
        if(aniExist.isPresent())
        {
            Animal animal = aniExist.get();
            animal.setEstado("E");
            daoAnimal.save(animal);
            res.put("mensaje", "Se eliminado correctamente");
            res.put("status", HttpStatus.NO_CONTENT);
            res.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(res);
        }
        else
        {
            res.put("mensaje", "Sin registros con ID: " + id);
            res.put("status", HttpStatus.NOT_FOUND);
            res.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

    }

    @Override
    public ResponseEntity<Map<String, Object>> listarPorIdTipo(Long idTipo) {
        Map<String, Object> res = new HashMap<>();
        List<Animal> ani = daoAnimal.findByTipo_Id(idTipo);

        if (!ani.isEmpty()) {
            res.put("animales", ani);
            res.put("mensaje", "Animales encontrados");
            res.put("status", HttpStatus.OK);
            res.put("fecha", new Date());
            return ResponseEntity.ok(res);
        } else {
            res.put("mensaje", "No se encontraron animales para el tipo ID: " + idTipo);
            res.put("status", HttpStatus.NOT_FOUND);
            res.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

    }

    @Override
    public ResponseEntity<Map<String, Object>> allAnimalEstado() {

        Map<String, Object> res = new HashMap<>();
        List<Animal> ani = daoAnimal.findAllByEstado("A");
        if(!ani.isEmpty())
        {
            res.put("mensaje", "Lista de insumos");
            res.put("animal", ani);
            res.put("status", HttpStatus.OK);
            res.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        else
        {
            res.put("mensaje", "No existen registros");
            res.put("status", HttpStatus.NOT_FOUND);
            res.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }


    }
}
