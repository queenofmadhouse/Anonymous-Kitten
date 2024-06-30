package org.example.javabot.service;

import lombok.RequiredArgsConstructor;
import org.example.javabot.entity.ChannelPost;
import org.example.javabot.repository.ChannelPostsRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChannelPostsService {

    private final ChannelPostsRepository channelPostsRepository;

    public void save(ChannelPost channelPost) {
        channelPostsRepository.save(channelPost);
    }

    public ChannelPost findByPostId(Long postId) {
        return channelPostsRepository.findChannelPostByPostId(postId);
    }
}
