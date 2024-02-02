package com.senai.Mobili.Repositories;

import com.senai.Mobili.Models.MotoristaModel;
import com.senai.Mobili.Models.ParceiroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface MotoristaRepository extends JpaRepository<MotoristaModel, UUID> {
    UserDetails findByEmail(String email);
}
