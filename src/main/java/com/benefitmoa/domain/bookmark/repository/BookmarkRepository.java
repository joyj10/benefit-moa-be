package com.benefitmoa.domain.bookmark.repository;

import com.benefitmoa.domain.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, BookmarkRepositoryCustom {

    @Query("SELECT b FROM Bookmark b JOIN FETCH b.policy p WHERE b.user.id = :userId")
    List<Bookmark> findAllWithPolicyByUserId(Long userId);
}
