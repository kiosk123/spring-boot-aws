package com.hjt.web.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import com.hjt.domain.posts.PostsRepository;
import com.hjt.domain.posts.Posts;


@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void postSaveAndGet() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        Posts post = Posts.builder().title(title).content(content).build();
        postsRepository.save(post);

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
    
}
