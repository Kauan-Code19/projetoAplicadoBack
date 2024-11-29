package com.kauanProjects.projetoAplicado.repositories;

import com.kauanProjects.projetoAplicado.entities.Collect;
import com.kauanProjects.projetoAplicado.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CollectRepository extends JpaRepository<Collect, Long> {
    List<Collect> findAllByDistrict(District district);
}
