package com.ssafy.BackEnd.service;

import com.ssafy.BackEnd.entity.Team;
import com.ssafy.BackEnd.entity.TeamMember;
import com.ssafy.BackEnd.entity.User;
import com.ssafy.BackEnd.repository.TeamMemberRepository;
import com.ssafy.BackEnd.repository.TeamRespository;
import com.ssafy.BackEnd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService{

    private final UserRepository userRepository;

    private final TeamRespository teamRespository;

    private final TeamMemberRepository teamMemberRepository;

    @Override
    public Team addTeamMember(String email, String teamName) { //팀에 팀원 추가하는 기능
        System.out.println("add "+email);
        List<User> user = userRepository.findByEmail(email); //해당 유저정보 가져오기
        for(User u : user){
            System.out.println(u.getEmail()+" hihi");
        }
        //System.out.println(user.getEmail()+" "+teamName+" hihi");
        Team team = teamRespository.findByTitle(teamName); //팀이름으로 해당 팀정보 가

        TeamMember teamMember = new TeamMember();
        teamMember.setTeam(team);
        //teamMember.setUser(user);

        teamMemberRepository.save(teamMember);
        return team;
    }

}
