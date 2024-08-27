package com.book.store.service.feedback;

import com.book.store.dto.feedback.FeedBackSaveDTO;
import com.book.store.model.feedbackmodel.Feedback;

public interface FeedBackServiceImplementation {
    public String addFeedback(FeedBackSaveDTO feedBackSaveDTO);
    public Feedback getFeedback(Long id);
}
