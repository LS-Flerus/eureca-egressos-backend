package com.eureca.egressos.repository;

import com.eureca.egressos.model.PhotoModel;
import com.eureca.egressos.model.PlaqueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlaqueRepository extends JpaRepository<PlaqueModel, UUID> {
    List<PlaqueModel> findByCourseCode(String courseCode);
}
