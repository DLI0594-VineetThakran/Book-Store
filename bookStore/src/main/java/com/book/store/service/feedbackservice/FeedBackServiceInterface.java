package com.book.store.service.feedbackservice;

import com.book.store.dto.feedbackdto.FeedBackSaveDTO;
import com.book.store.model.feedbackmodel.Feedback;

import java.util.List;

public interface FeedBackServiceInterface {
    public String addFeedback(FeedBackSaveDTO feedBackSaveDTO);
    public List<Feedback> getAllFeedbacks(Long product_id);
}
