package com.ssafy.BackEnd.repository;

import com.ssafy.BackEnd.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, String> {
    Optional<Profile> findByName(String name);
    Profile findByEmail(String email);
    List<Profile> findByNameContaining(String keyword);
}
