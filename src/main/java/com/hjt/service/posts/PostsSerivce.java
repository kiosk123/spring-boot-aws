package com.hjt.service.posts;

import com.hjt.domain.posts.PostsRepository;
import com.hjt.web.dto.PostsSaveRequestDto;

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
    
}
