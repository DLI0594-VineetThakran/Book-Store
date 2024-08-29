package com.book.store.repository.feedbackrepository;

import com.book.store.model.feedbackmodel.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository<Feedback,Long> {
    List<Feedback> findByProductId(Long productId);
}
