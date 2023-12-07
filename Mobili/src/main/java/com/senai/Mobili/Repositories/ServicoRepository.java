package com.senai.Mobili.Repositories;


import com.senai.Mobili.Models.ServicoModel2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServicoRepository extends JpaRepository <ServicoModel2, UUID>{


}
