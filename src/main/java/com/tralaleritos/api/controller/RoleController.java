package com.tralaleritos.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tralaleritos.api.model.Role;
import com.tralaleritos.api.service.RoleService;

@RequestMapping("/api/v1/roles")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles = roleService.findAllRoles();

        if (!roles.isEmpty()) {
            return new ResponseEntity<>(roles, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Role>> getActiveRoles() {
        List<Role> roles = roleService.findActiveRoles();

        if (!roles.isEmpty()) {
            return new ResponseEntity<>(roles, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable UUID id) {
        Optional<Role> roleOptional = roleService.findRoleById(id);

        if (roleOptional.isPresent()) {

            Role role = roleOptional.get();
            return new ResponseEntity<>(role, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping
    public ResponseEntity<Role> createBrand(@RequestBody Role role) {
        if (role.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Role savedRole = roleService.saveRole(role);

        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateBrand(@PathVariable UUID id, @RequestBody Role roleDetails) {

        if (!id.equals(roleDetails.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Role updatedRole = roleService.updateRole(roleDetails);

        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBrand(@PathVariable UUID id) {
        roleService.deleteRole(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
