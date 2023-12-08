package com.senai.Mobili.Repositories;

import com.senai.Mobili.Models.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, UUID> {


}

