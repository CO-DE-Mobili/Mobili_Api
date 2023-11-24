package com.senai.Mobili.Repositories;

import com.senai.Mobili.Models.ServicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicoRepository extends JpaRepository<ServicoModel, UUID> {

}
