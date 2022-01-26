package com.ssafy.BackEnd.service;


import com.ssafy.BackEnd.entity.Profile;
import com.ssafy.BackEnd.entity.Request.RequestModifyProfile1;
import com.ssafy.BackEnd.entity.Request.RequestModifyProfile2;
import com.ssafy.BackEnd.entity.User;
import javassist.NotFoundException;
import org.apache.coyote.Request;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

public interface ProfileService {

    Optional<Profile> findProfile(String email) throws NotFoundException;

    Profile modifyProfile(Profile findProfile, RequestModifyProfile2 requestModifyProfile2) throws NotFoundException;

    void deleteUser(String email);

    List<Profile> showFindTeamList(String keyword);
}
