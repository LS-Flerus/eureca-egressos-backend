package com.eureca.egressos.repository;

import com.eureca.egressos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
