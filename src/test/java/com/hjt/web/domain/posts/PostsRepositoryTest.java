package com.hjt.web.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import com.hjt.domain.posts.Posts;
import com.hjt.domain.posts.PostsRepository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    public void baseTimeEntityTest() {
        LocalDateTime now = LocalDateTime.of(2021, 2, 12, 0, 0, 0);
        Posts newPosts = Posts.builder().title("title").content("content").author("author").build();
        postsRepository.save(newPosts);

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);
        
        System.out.println(">>>>>>>>>>>>>> createDate = " + posts.getCreatedDate() + ", modifiedDate = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
