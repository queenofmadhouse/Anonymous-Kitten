package org.example.javabot.repository;

import org.example.javabot.entity.BlockedUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockedUsersRepository extends JpaRepository<BlockedUsers, Long> {

    boolean existsByUserId(Long id);

    void removeBlockedUsersByUserId(Long id);
}
