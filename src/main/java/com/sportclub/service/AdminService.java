package com.sportclub.service;

import com.sportclub.model.Admin;
import com.sportclub.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Optional<Admin> login(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password);
    }

    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin getById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    public Admin update(Long id, Admin updated) {
        Admin admin = getById(id);
        admin.setEmail(updated.getEmail());
        admin.setPassword(updated.getPassword());
        admin.setFullName(updated.getFullName());
        return adminRepository.save(admin);
    }

    public void delete(Long id) {
        adminRepository.deleteById(id);
    }
}
