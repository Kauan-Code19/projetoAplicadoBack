package com.kauanProjects.projetoAplicado.repositories;

import com.kauanProjects.projetoAplicado.entities.District;
import com.kauanProjects.projetoAplicado.entities.Ecopoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EcopointRepository extends JpaRepository<Ecopoint, Long> {
    List<Ecopoint> findAllByDistrict(District district);
}
