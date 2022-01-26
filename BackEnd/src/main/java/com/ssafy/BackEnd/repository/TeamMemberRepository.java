package com.ssafy.BackEnd.repository;

import com.ssafy.BackEnd.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
}
