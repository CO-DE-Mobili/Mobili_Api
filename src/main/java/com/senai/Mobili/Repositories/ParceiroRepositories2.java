package com.senai.Mobili.Repositories;


import com.senai.Mobili.Models.ParceiroModel2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParceiroRepositories2 extends JpaRepository<ParceiroModel2, UUID> {
}
