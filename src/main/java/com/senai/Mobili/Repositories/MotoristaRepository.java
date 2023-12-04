package com.senai.Mobili.Repositories;


import com.senai.Mobili.Models.MotoristaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MotoristaRepository extends JpaRepository<MotoristaModel, UUID> {
    MotoristaModel findByEmail(String email);
}
