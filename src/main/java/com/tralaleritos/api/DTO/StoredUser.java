package com.tralaleritos.api.DTO;

import java.util.UUID;

import com.tralaleritos.api.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoredUser {

    private UUID id;

    private String name;

    private String last_name;

    private String email;

    private Role role;

    private boolean isDuoc;

}
