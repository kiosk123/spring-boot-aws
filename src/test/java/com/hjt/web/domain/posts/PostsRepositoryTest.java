package com.hjt.web.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.swing.Spring;

import com.hjt.domain.posts.PostsRepository;
import com.hjt.domain.posts.Posts;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = {"test"})
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    @Transactional
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
