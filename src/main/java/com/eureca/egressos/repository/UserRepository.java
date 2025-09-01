package com.eureca.egressos.repository;

import com.eureca.egressos.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByEnrollment(String login);
    Optional<UserModel> findByName(String name);
    List<UserModel> getAllByCourseCode(String courseCode);
}