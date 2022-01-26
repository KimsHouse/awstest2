package com.ssafy.BackEnd.controller;

import com.ssafy.BackEnd.entity.Team;
import com.ssafy.BackEnd.repository.TeamRespository;
import com.ssafy.BackEnd.service.TeamMemberService;
import com.ssafy.BackEnd.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teammember")
@RequiredArgsConstructor
public class TeamMemberController {

    private final TeamRespository teamRespository;
    private final TeamMemberService teamMemberService;

    @PostMapping
    public ResponseEntity<Team> createTeamMember(@RequestParam String email, @RequestParam String teamName) {
        System.out.println("cont "+email+" "+teamName);
        Team team = teamMemberService.addTeamMember(email, teamName);

        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Team> addTeamMember(@RequestParam String email, @RequestParam String teamName) {
        Team team = teamMemberService.addTeamMember(email, teamName);

        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }
}
