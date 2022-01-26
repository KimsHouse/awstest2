package com.ssafy.BackEnd.controller;

import com.ssafy.BackEnd.dto.UserFeedDto;
import com.ssafy.BackEnd.entity.FileType;
import com.ssafy.BackEnd.entity.Profile;
import com.ssafy.BackEnd.repository.UserFeedRepository;
import com.ssafy.BackEnd.repository.UserRepository;
import com.ssafy.BackEnd.service.FileStore;
import com.ssafy.BackEnd.service.ProfileService;
import com.ssafy.BackEnd.service.UserFeedService;
import com.ssafy.BackEnd.util.UserFeedAddForm;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ssafy.BackEnd.entity.UserFeed;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/userfeed")
@RequiredArgsConstructor
@Slf4j
public class UserFeedController {

    private final UserFeedRepository userFeedRepository;
    private final UserFeedService userFeedService;
    private final ProfileService profileService;
    private final FileStore fileStore;

    // Create
    @PostMapping
    @ApiOperation(value = "유저 피드 생성")
    public ResponseEntity<String> createUserFeed(@RequestBody UserFeed userFeed) {
        String message = "";
        HttpStatus status;

        try{
            UserFeed feed = userFeedService.createUserFeed(userFeed);
            if(feed != null){
                message = "success";
                status = HttpStatus.ACCEPTED;
            } else {
                message = "fail";
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            message = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(message, status);
    }

    // Read
    @ApiOperation(value = "유저 피드 조회")
    @GetMapping
    public ResponseEntity<List<UserFeed>> findAllUserFeed() {
        List<UserFeed> userfeeds = userFeedService.showFindUserFeedList();

        return new ResponseEntity<List<UserFeed>>(userfeeds, HttpStatus.OK);
    }

    // Update
    @PutMapping("/userfeed_id")
    @ApiOperation(value = "유저 피드 수정")
    public ResponseEntity<UserFeed> modifyUserFeed(@RequestBody UserFeedDto userFeedDto) {
        UserFeed userFeed = userFeedDto.createUserFeed();
        userFeedService.modifyUserFeed(userFeed.getUserfeed_id(), userFeed);

        return new ResponseEntity<UserFeed>(userFeed, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/userfeed_id")
    @ApiOperation(value = "유저 피드 삭제")
    public ResponseEntity<Void>deleteUserFeed(@RequestBody UserFeedDto userFeedDto) {
        UserFeed userFeed = userFeedDto.createUserFeed();
        userFeedService.deleteUserFeed(userFeed.getUserfeed_id());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/post")
    public String post(String email, @Validated @ModelAttribute UserFeedAddForm userFeedAddForm, BindingResult bindingResult) throws IOException, NotFoundException {
        if (bindingResult.hasErrors()) {
            log.info("bindingResult : {}", bindingResult.getFieldError());
            return "post";
        }
        Profile profile = profileService.findProfile(email).get();
        UserFeedDto userFeedDto = userFeedAddForm.createUserFeedDto(profile);
        UserFeed post = userFeedService.post(userFeedDto);
        return "redirect:/main/board" + post.getProfile();
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public UrlResource processImg(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.createPath(filename, FileType.IMAGE));
    }

    @GetMapping("/files/{filename}")
    public ResponseEntity<UrlResource> processFiles(@PathVariable String filename, @RequestParam String originName) throws MalformedURLException {
        UrlResource urlResource = new UrlResource("file:" + fileStore.createPath(filename, FileType.GENERAL));
        String encodedUploadFileName = UriUtils.encode(originName, StandardCharsets.UTF_8);
        String contentDisposition = "files; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }
}
