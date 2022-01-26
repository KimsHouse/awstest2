package com.ssafy.BackEnd.controller;

import com.ssafy.BackEnd.dto.TeamDto;
import com.ssafy.BackEnd.entity.Response;
import com.ssafy.BackEnd.repository.TeamRespository;
import com.ssafy.BackEnd.service.TeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ssafy.BackEnd.entity.Team;
import java.util.List;

@RestController
@Api(tags = "팀 컨트롤러 API")
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    TeamRespository teamRespository;

    @GetMapping
    @ApiOperation(value = "팀 목록 가져오기")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.showTeamList();
        return new ResponseEntity<List<Team>>(teams, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "팀 만들기")
    public ResponseEntity<Team> createTeam(@RequestBody TeamDto teamDto) {
        Team team = teamDto.createTeam();
        return new ResponseEntity<Team>(teamService.createTeam(team), HttpStatus.OK);

    }

    @GetMapping("/{team_id}")
    @ApiOperation(value = "팀 조회")
    public ResponseEntity<Team> findTeam(@RequestBody TeamDto teamDto) throws NotFoundException {
        Team team = teamDto.createTeam();
        return new ResponseEntity<Team>(teamService.findByTeam(team.getTeam_id()), HttpStatus.OK);
    }


    @PutMapping("/{team_id}")
    @ApiOperation(value = "팀 수정") //팀 수정이 무엇에 대한 수정인가(name과 content에 대한 수정??)
    public ResponseEntity<Team> modifyTeam(@RequestBody TeamDto teamDto) {
        Team team = teamDto.createTeam();
        teamService.modifyTeam(team.getTeam_id(), team);

        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }

    @DeleteMapping("/{team_id}")
    @ApiOperation(value = "팀 삭제")
    public ResponseEntity<Void> deleteTeam(@RequestBody TeamDto teamDto){
        Team team = teamDto.createTeam();

        teamService.deleteTeam(team.getTeam_id());
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // ------ team manage

    @PostMapping("/profile")
    @ApiOperation(value = "팀 프로필 만들기")
    public void createTeamProfile(){

    }

    @PutMapping("/profile/{team_id}")
    @ApiOperation(value = "팀 프로필 수정")
    public ResponseEntity<Team> modifyTeamProfile(@RequestBody TeamDto teamDto) {
        Team team = teamDto.createTeam();
        teamService.modifyTeamProfile(team.getTeam_id(), team);

        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }

    @GetMapping("/search") //팀 키워드도 검색 가능하게 해야함 !!!
    public ResponseEntity<List<Team>> searchTeam(@RequestParam String keyword) {
        List<Team> search_teams = teamService.showFindTeamList(keyword);

        return new ResponseEntity<List<Team>>(search_teams, HttpStatus.OK);
    }
}
