package com.ssafy.BackEnd.controller;

import com.ssafy.BackEnd.dto.UserDto;
import com.ssafy.BackEnd.entity.Request.RequestChangePassword1;
import com.ssafy.BackEnd.entity.Request.RequestChangePassword2;
import com.ssafy.BackEnd.entity.Request.RequestVerifyEmail;
import com.ssafy.BackEnd.entity.Response;
import com.ssafy.BackEnd.entity.User;
import com.ssafy.BackEnd.service.AuthService;
import com.ssafy.BackEnd.service.JwtServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final JwtServiceImpl jwtService;
    private final AuthService authService;


    @PostMapping("/signup")
    @ApiOperation(value = "회원가입", notes = "사용자의 정보를 입력 받고 'success'면 회원가입 or 'fail이면 에러메세지", response = String.class)
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody UserDto userDto) {
        Response response = new Response();
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        System.out.println("up : "+userDto.getEmail());
        try {
            User user = userDto.createUser();
            authService.signUp(user);
            response.setResponse("success");
            response.setMessage("회원가입을 성공적으로 완료했습니다.");
            response.setData(null);
            status = HttpStatus.ACCEPTED;
            resultMap.put("message", "success");

        }
        catch(Exception e) {
            response.setResponse("failed");
            response.setMessage("회원가입을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/password/{key}")
    @ApiOperation(value = "비밀번호 변경 인증 절차", response = String.class)
    public Response isPasswordUUIDValidate(@PathVariable String key) {
        Response response = new Response();
        try {
            if (authService.isPasswordUuidValidate(key)) {
                response.setResponse("success");
                response.setMessage("정상적인 접근입니다.");
                response.setData(null);
            }
            else {
                response.setResponse("error");
                response.setMessage("유효하지 않은 key값입니다.");
                response.setData(null);
            }
        } catch (Exception e) {
            response.setResponse("error");
            response.setMessage("유효하지 않은 key값입니다.");
            response.setData(null);
        }
        return response;
    }

    @PostMapping("/password")
    @ApiOperation(value = "사용자 비밀번호 변경요청", response = String.class)
    public Response requestChangePassword(RequestChangePassword1 requestChangePassword1) {
        Response response = new Response();
        try {
            User user = authService.findByEmail(requestChangePassword1.getEmail());
            if (!user.getEmail().equals(requestChangePassword1.getEmail())) throw new NoSuchFieldException("");
            authService.requestChangePassword(user);
            response.setResponse("success");
            response.setMessage("성공적으로 사용자의 비밀번호를 변경요청을 수행했습니다.");
            response.setData(null);
        } catch (NoSuchFieldException e) {
            response.setResponse("error");
            response.setMessage("사용자의 정보를 조회할 수 없습니다.");
            response.setData(null);
        } catch (Exception e) {
            response.setResponse("error");
            response.setMessage("비밀번호 변경 요청을 할 수 없습니다.");
            response.setData(null);
        }
        return response;
    }

    @PutMapping("/password")
    @ApiOperation(value = "비밀번호 변경", response = String.class)
    public Response changePassword(RequestChangePassword2 requestChangePassword2) {
        Response response = new Response();
        try {
            User user = authService.findByEmail(requestChangePassword2.getEmail());
            authService.changePassword(user, requestChangePassword2.getPassword());
            response.setResponse("success");
            response.setMessage("사용자의 비밀번호를 성공적으로 변경했습니다.");
            response.setData(null);
        } catch (Exception e) {
            response.setResponse("error");
            response.setMessage("사용자의 비밀번호를 변경할 수 없습니다.");
            response.setData(null);
        }
        return response;
    }


    @GetMapping("/signin")
    @ApiOperation(value = "로그인 페이지")
    public void signinPage() {
        System.out.println("sign in hi");
    }

    @PostMapping("/signin") // 스켈레톤이랑 연결할땐 signin 지우고 '/' 상태에서
    @ApiOperation(value = "로그인 환경", response = Map.class)
    public ResponseEntity<Map<String, Object>> signin(@RequestBody User users,
                                                      HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        System.out.println(users.getEmail()+" "+users.getPassword());
        try {
            final User user = authService.signIn(users.getEmail(), users.getPassword());
            //System.out.println(user.getEmail()+" "+user.getPassword());
            //System.out.println("user null ? "+user);
            if(user != null) {
                //System.out.println("1pass");
                String token = jwtService.create("email", user.getEmail(), "access-token");
                System.out.println("tk : "+token);
                resultMap.put("access-token", token);
                resultMap.put("message", "success");
                status = HttpStatus.ACCEPTED;
            } else {
                //System.out.println("error");
                resultMap.put("message", "fail");
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PostMapping("/verify")
    @ApiOperation(value = "회원가입 인증", response = String.class)
    public Response verify(RequestVerifyEmail requestVerifyEmail, HttpServletRequest req, HttpServletResponse res) {
        Response response = new Response();
        System.out.println("ve : "+requestVerifyEmail.getName());
        try {
            User user = authService.findByName(requestVerifyEmail.getName());
            System.out.println("u : "+user.getName());
            authService.sendVerificationMail(user);

            response.setResponse("success");
            response.setMessage("성공적으로 인증메일을 보냈습니다");
            response.setData(null);
        } catch (Exception exception) {
            //response = new Response("error", "인증메일을 보내는데 문제가 발생했습니다.", exception);
            response.setResponse("error");
            response.setMessage("인증메일을 보내는데 문제가 발생했습니다");
            response.setData(exception);
        }
        return response;
    }

    @GetMapping("/verify/{key}")
    @ApiOperation(value = "회원가입 인증 확인")
    public Response getVerify(@PathVariable String key) {
        Response response;
        try {
            authService.verifyEmail(key);
            response = new Response("success", "성공적으로 인증메일을 확인했습니다.", null);

        } catch (Exception e) {
            response = new Response("error", "인증메일을 확인하는데 실패했습니다.", null);
        }
        return response;
    }
}
