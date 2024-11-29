package com.kauanProjects.projetoAplicado.repositories;

import com.kauanProjects.projetoAplicado.entities.Ecopoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcopointRepository extends JpaRepository<Ecopoint, Long> {
}
