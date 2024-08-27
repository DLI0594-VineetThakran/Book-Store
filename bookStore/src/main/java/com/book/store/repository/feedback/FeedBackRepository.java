package com.book.store.repository.feedback;

import com.book.store.model.feedbackmodel.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<Feedback,Long> {
}
