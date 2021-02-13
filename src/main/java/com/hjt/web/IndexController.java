package com.hjt.web;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import com.hjt.config.oauth.LoginUser;
import com.hjt.config.oauth.dto.SessionUser;
import com.hjt.service.posts.PostsSerivce;
import com.hjt.web.dto.PostsListResponseDto;
import com.hjt.web.dto.PostsResponseDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final PostsSerivce postsSerivce;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        List<PostsListResponseDto> postsList = postsSerivce.findAllDesc();
        model.addAttribute("posts", postsList);

        // CustomOAuth2UserService에서 로그인 성공시 SessionUser에서 값을 가져올 수 있음
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if ( !Objects.isNull(user) ) {
            // log.info("session user name is " + user.getName());
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping(value="/posts/save")
    public String postsSave() {
        return "posts-save";
    }
    
    @GetMapping(value="/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsSerivce.findByid(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
