package com.ssafy.BackEnd.service;

import com.ssafy.BackEnd.entity.Team;

public interface TeamMemberService{

    Team addTeamMember(String email, String teamName);
}
