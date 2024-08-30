package com.book.store.controller.feedbackcontroller;


import com.book.store.dto.feedbackdto.FeedBackSaveDTO;
import com.book.store.model.feedbackmodel.Feedback;
import com.book.store.service.feedbackservice.FeedBackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore_user")
public class FeedBackController {
    @Autowired
    FeedBackService feedBackService;

    @PostMapping("/add/feedback/{product_id}")
    public String addFeedback(@PathVariable Long product_id,@Valid @RequestBody FeedBackSaveDTO feedBackSaveDTO){
        feedBackSaveDTO.setProductId(product_id);
        String feedback=feedBackService.addFeedback(feedBackSaveDTO);
        return "Added Feedback!";
    }


    @GetMapping("/get/feedback/{product_id}")
    public List<Feedback> getAllFeedbacks(@PathVariable Long product_id) {
        return feedBackService.getAllFeedbacks(product_id);
    }

}
