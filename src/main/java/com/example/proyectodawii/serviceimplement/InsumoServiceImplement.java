package com.example.proyectodawii.serviceimplement;

import com.example.proyectodawii.model.Insumos;
import com.example.proyectodawii.repository.InsumoRepository;
import com.example.proyectodawii.service.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InsumoServiceImplement implements InsumoService {

    @Autowired
    private InsumoRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarInusmo() {
        Map<String,Object> respuesta = new HashMap<>();
        List<Insumos> insumos = dao.findAll();
        if(!insumos.isEmpty())
        {
            respuesta.put("mensaje","Lista de Insumos");
            respuesta.put("insumos", insumos);
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }
        else
        {
            respuesta.put("mensaje", "No existen registros");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarInusmoPorId(Long id) {
        Map<String,Object> respuesta = new HashMap<>();
        Optional<Insumos> insumoExiste = dao.findById(id);

        if(insumoExiste.isPresent())
        {
            respuesta.put("insumos",insumoExiste);
            respuesta.put("mensaje", "Busqueda Corrrecta");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.ok().body(respuesta);
        }
        else
        {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

    }

    @Override
    public ResponseEntity<Map<String, Object>> agregarInusmo(Insumos insumos) {
        Map<String,Object> respuesta = new HashMap<>();
        dao.save(insumos);
        respuesta.put("insumos", insumos);
        respuesta.put("mensaje","Registro Agregado");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarInusmo(Insumos ins, Long id) {
        Map<String,Object> respuesta =  new HashMap<>();
        Optional<Insumos> insumoExiste = dao.findById(id);
        if(insumoExiste.isPresent())
        {
            Insumos insumo = insumoExiste.get();
            insumo.setNombre(ins.getNombre());
            insumo.setNombre(ins.getNombre());
            insumo.setEstado(ins.getEstado());
            dao.save(insumo);
            respuesta.put("insumo", insumo);
            respuesta.put("mensaje", "Datos del tipo de insumo modificado");
            respuesta.put("status", HttpStatus.CREATED);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }
        else
        {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarInusmo(Long id) {
        Map<String,Object> respuesta =  new HashMap<>();
        Optional<Insumos> insumoExiste = dao.findById(id);
        if(insumoExiste.isPresent())
        {
            Insumos insumo = insumoExiste.get();
            dao.delete(insumo);
            respuesta.put("mensaje", "Eliminado correctamente");
            respuesta.put("status", HttpStatus.NO_CONTENT);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
        }
        else
        {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarInusmoEstado(Long id) {
        Map<String,Object> respuesta =  new HashMap<>();
        Optional<Insumos> insumoExiste = dao.findById(id);
        if(insumoExiste.isPresent())
        {
            Insumos insumo = insumoExiste.get();
            insumo.setEstado("E");
            dao.save(insumo);
            respuesta.put("mensaje", "Se eliminado correctamente");
            respuesta.put("status", HttpStatus.NO_CONTENT);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
        }
        else
        {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

    }

    @Override
    public ResponseEntity<Map<String, Object>> allInusmoEstado() {
        Map<String,Object> respuesta =  new HashMap<>();
        List<Insumos> insumos = dao.findAllByEstado("A");
        if(!insumos.isEmpty())
        {
            respuesta.put("mensaje", "Lista de insumos");
            respuesta.put("insumos", insumos);
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }
        else
        {
            respuesta.put("mensaje", "No existen registros");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }
}
