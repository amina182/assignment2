package org.example.controller;

import org.example.dao.DoctorDao;
import org.example.model.Doctor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorDao doctorDao;

    public DoctorController(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @GetMapping
    public List<Doctor> getAll() throws SQLException {
        return doctorDao.findAll();
    }

    @GetMapping("/{id}")
    public Doctor getById(@PathVariable int id) throws SQLException {
        return doctorDao.findById(id).orElse(null);
    }

    @PostMapping
    public Doctor create(@RequestBody Doctor d) throws SQLException {
        int id = doctorDao.create(d);
        d.setId(id);
        return d;
    }

    @PutMapping("/{id}")
    public Doctor update(@PathVariable int id, @RequestBody Doctor d) throws SQLException {
        boolean updated = doctorDao.update(id, d);
        if (!updated) return null;
        d.setId(id);
        return d;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) throws SQLException {
        boolean deleted = doctorDao.delete(id);
        if (!deleted) return "Doctor not found";
        return "Deleted doctor id=" + id;
    }
}
