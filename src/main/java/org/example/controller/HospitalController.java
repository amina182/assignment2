package org.example.controller;

import org.example.dao.HospitalDao;
import org.example.model.Hospital;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final HospitalDao hospitalDao;

    public HospitalController(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @GetMapping
    public List<Hospital> getAll() throws SQLException {
        return hospitalDao.findAll();
    }

    @GetMapping("/{id}")
    public Hospital getById(@PathVariable int id) throws SQLException {
        return hospitalDao.findById(id).orElse(null);
    }

    @PostMapping
    public Hospital create(@RequestBody Hospital h) throws SQLException {
        int id = hospitalDao.create(h);
        h.setId(id);
        return h;
    }

    @PutMapping("/{id}")
    public Hospital update(@PathVariable int id, @RequestBody Hospital h) throws SQLException {
        boolean ok = hospitalDao.update(id, h);
        if (!ok) return null;
        h.setId(id);
        return h;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) throws SQLException {
        boolean ok = hospitalDao.delete(id);
        if (!ok) return "Hospital not found";
        return "Deleted hospital id=" + id;
    }
}

