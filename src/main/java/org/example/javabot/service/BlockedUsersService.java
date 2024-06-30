package org.example.javabot.service;

import lombok.RequiredArgsConstructor;
import org.example.javabot.entity.BlockedUsers;
import org.example.javabot.repository.BlockedUsersRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BlockedUsersService {

    private final BlockedUsersRepository blockedUsersRepository;

    public boolean existByUserId(Long id) {
        return blockedUsersRepository.existsByUserId(id);
    }

    public void save(Long userId) {
        blockedUsersRepository.save(BlockedUsers.builder()
                .userId(userId)
                .build());
    }

    @Transactional
    public void deleteByUserId(Long userId) {
        blockedUsersRepository.removeBlockedUsersByUserId(userId);
    }
}
