package com.hjt.web;

import java.util.List;

import com.hjt.service.posts.PostsSerivce;
import com.hjt.web.dto.PostsListResponseDto;
import com.hjt.web.dto.PostsResponseDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsSerivce postsSerivce;

    @GetMapping("/")
    public String index(Model model) {
        List<PostsListResponseDto> postsList = postsSerivce.findAllDesc();
        model.addAttribute("posts", postsList);
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
