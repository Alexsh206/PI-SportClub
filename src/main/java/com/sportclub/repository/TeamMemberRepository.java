package com.sportclub.repository;

import com.sportclub.model.TeamMember;
import com.sportclub.model.TeamMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMember, TeamMemberId> {
}
