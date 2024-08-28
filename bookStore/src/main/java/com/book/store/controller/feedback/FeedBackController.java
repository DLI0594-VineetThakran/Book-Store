package com.book.store.controller.feedback;


import com.book.store.dto.feedback.FeedBackDTO;
import com.book.store.dto.feedback.FeedBackSaveDTO;
import com.book.store.model.feedbackmodel.Feedback;
import com.book.store.service.feedback.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore_user")
public class FeedBackController {
    @Autowired
    FeedBackService feedBackService;

    @PostMapping("/add/feedback/{product_id}")
    public String addFeedback(@PathVariable Long product_id,@RequestBody FeedBackSaveDTO feedBackSaveDTO){
        feedBackSaveDTO.setProductId(product_id);
        String feedback=feedBackService.addFeedback(feedBackSaveDTO);
        return "added feedback";
    }

    @GetMapping("/get/feedback/{product_id}")
    public Feedback getFeedback(@PathVariable Long id){
        return feedBackService.getFeedback(id);
    }
}
