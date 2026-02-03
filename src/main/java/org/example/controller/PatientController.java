package org.example.controller;

import org.example.dao.PatientDao;
import org.example.model.Patient;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientDao patientDao;

    public PatientController(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @GetMapping
    public List<Patient> getAllPatients() throws SQLException {
        return patientDao.findAll();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable int id) throws SQLException {
        return patientDao.findById(id).orElse(null);
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) throws SQLException {
        int id = patientDao.create(patient);
        patient.setId(id);
        return patient;
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable int id, @RequestBody Patient patient) throws SQLException {
        boolean updated = patientDao.update(id, patient);
        if (!updated) {
            return null;
        }
        patient.setId(id);
        return patient;
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable int id) throws SQLException {
        boolean deleted = patientDao.delete(id);
        if (!deleted) {
            return "Patient with id=" + id + " not found";
        }
        return "Deleted patient id=" + id;
    }
}
