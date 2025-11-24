package com.tralaleritos.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tralaleritos.api.exception.ResourceNotFoundException;
import com.tralaleritos.api.model.Role;
import com.tralaleritos.api.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public List<Role> findActiveRoles() {
        return roleRepository.findByActiveTrue();
    }

    public Optional<Role> findRoleById(UUID id) {
        return roleRepository.findById(id);
    }

    public Role updateRole(Role role) {

        if (role.getId() == null || !roleRepository.existsById(role.getId())) {
            throw new ResourceNotFoundException("Role with ID " + role.getId() + " not found. Update failed.");
        }

        return roleRepository.save(role);
    }

    public void deleteRole(UUID id) {

        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Role with ID " + id + " not found. Delete failed.");
        }

        Role deactivatedRole = roleRepository.findById(id).get();
        deactivatedRole.setActive(false);

        roleRepository.save(deactivatedRole);
    }
}
