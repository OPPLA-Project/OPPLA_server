package com.oppla.server.domain.review.repository;

import com.oppla.server.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Integer countByMemberIdAndInfoScore(Long id, int i);

    List<Review> findAllByMemberId(Long id);
}
