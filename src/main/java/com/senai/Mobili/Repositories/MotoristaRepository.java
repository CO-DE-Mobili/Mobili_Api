package com.senai.Mobili.Repositories;


import com.senai.Mobili.Models.MotoristaModel2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MotoristaRepository extends JpaRepository<MotoristaModel2, UUID> {
}
