package com.kauanProjects.projetoAplicado.repositories;

import com.kauanProjects.projetoAplicado.entities.Collect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectRepository extends JpaRepository<Collect, Long> {
}
