package org.example.javabot.repository;

import org.example.javabot.entity.ChannelPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelPostsRepository extends JpaRepository<ChannelPost, Long> {

    ChannelPost findChannelPostByPostId(Long postId);
}
