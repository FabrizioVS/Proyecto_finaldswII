package com.example.proyectodawii.controller;


import ch.qos.logback.core.joran.event.SaxEventRecorder;
import com.example.proyectodawii.model.Insumos;
import com.example.proyectodawii.service.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/insumo")
public class InsumoController {
    @Autowired
    private InsumoService service;


    @GetMapping
    public ResponseEntity<Map<String, Object>> listar()
    {
        return service.listarInusmo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> listarInsumoPorId(@PathVariable Long id)
    {
        return service.listarInusmoPorId(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregarInsumo(@RequestBody Insumos insumo)
    {
        return service.agregarInusmo(insumo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editarInsumo(@RequestBody Insumos insumo, @PathVariable Long id)
    {
        return service.editarInusmo(insumo, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarInsumo(@PathVariable Long id)
    {
        return service.eliminarInusmo(id);
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Object>> eliminarInusmoLogico(@PathVariable Long id)
    {
        return service.eliminarInusmoEstado(id);
    }

    @GetMapping("/estado")
    public ResponseEntity<Map<String, Object>> listarInsumoEstado()
    {
        return service.allInusmoEstado();
    }
}
