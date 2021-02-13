package com.hjt.service.posts;

import com.hjt.domain.posts.Posts;
import com.hjt.domain.posts.PostsRepository;
import com.hjt.web.dto.PostsResponseDto;
import com.hjt.web.dto.PostsSaveRequestDto;
import com.hjt.web.dto.PostsUpdateRequestDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsSerivce {

    private final PostsRepository postsRepository;

    @Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}

    @Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
		posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
	}

    @Transactional
	public PostsResponseDto findByid(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
		return new PostsResponseDto(posts);
	}
    
}
