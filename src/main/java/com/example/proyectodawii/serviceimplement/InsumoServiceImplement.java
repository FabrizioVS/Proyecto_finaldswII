package com.example.proyectodawii.serviceimplement;

import com.example.proyectodawii.model.Animal;
import com.example.proyectodawii.model.Insumos;
import com.example.proyectodawii.model.Tipo;
import com.example.proyectodawii.repository.InsumoRepository;
import com.example.proyectodawii.repository.TipoRepository;
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
    
    @Autowired
    private TipoRepository daotip;

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
        Insumos insumo = new Insumos();
        Optional<Tipo> tipoOpt = daotip.findByIdAndEstado(insumos.getIdtipo(), "A");
        if (tipoOpt.isPresent()) {
            insumo.setTipo(tipoOpt.get());
        } else {
            respuesta.put("mensaje", "El tipo no existe o ha sido borrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
        dao.save(insumos);
        respuesta.put("insumos", insumos);
        respuesta.put("mensaje","Registro Agregado");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarInusmo(Insumos insumos, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Insumos> insumoExiste = dao.findById(id);

        if (insumoExiste.isPresent()) {
            
            Optional<Tipo> tipoOpt = daotip.findByIdAndEstado(insumos.getIdtipo(), "A");
            if (!tipoOpt.isPresent()) {
                respuesta.put("mensaje", "El tipo no existe o ha sido borrado.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
            }

           
            Insumos insumo = insumoExiste.get();
            insumo.setNombre(insumos.getNombre());
            insumo.setEstado(insumos.getEstado());
            insumo.setIdtipo(insumos.getIdtipo()); 
            
         
            dao.save(insumo);

            respuesta.put("insumo", insumo);
            respuesta.put("mensaje", "Datos del insumo modificados correctamente");
            respuesta.put("status", HttpStatus.CREATED);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } else {
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
    public ResponseEntity<Map<String, Object>> listarPorIdTipo(Long idTipo) {
        Map<String,Object> respuesta =  new HashMap<>();
        List<Insumos> ani = dao.findByTipo_Id(idTipo);

        if (!ani.isEmpty()) {
            respuesta.put("animales", ani);
            respuesta.put("mensaje", "Insumos encontrados");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.ok(respuesta);
        } else {
            respuesta.put("mensaje", "No se encontraron Insumos para el tipo ID: " + idTipo);
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
