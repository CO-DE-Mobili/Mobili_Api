package com.senai.Mobili.Repositories;


import com.senai.Mobili.Models.ParceiroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParceiroRepository extends JpaRepository<ParceiroModel, UUID> {
    UserDetails findByEmail (String email);
}
