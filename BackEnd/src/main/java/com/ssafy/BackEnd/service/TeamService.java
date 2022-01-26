package com.ssafy.BackEnd.service;

import com.ssafy.BackEnd.entity.Team;
import javassist.NotFoundException;

import java.util.List;

public interface TeamService {

    Team findByTeam(Long team_id) throws NotFoundException;

    Team createTeam(Team team);

    void modifyTeam(long team_id, Team team);

    void deleteTeam(long team_id);

    List<Team> showTeamList();

    void modifyTeamProfile(long team_id, Team team);

    List<Team> showFindTeamList(String keyword);
}
