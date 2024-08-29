package com.book.store.service.feedbackservice;

import com.book.store.dto.feedbackdto.FeedBackSaveDTO;
import com.book.store.model.feedbackmodel.Feedback;
import com.book.store.model.productmodel.Product;
import com.book.store.model.usermodel.User;
import com.book.store.repository.feedbackrepository.FeedBackRepository;
import com.book.store.repository.productrepository.ProductRepository;
import com.book.store.repository.userrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedBackService implements FeedBackServiceInterface {
    @Autowired
    private FeedBackRepository feedBackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String addFeedback(FeedBackSaveDTO feedBackSaveDTO){
        Optional<User> user =userRepository.findById(feedBackSaveDTO.getUserId());
        Optional<Product> product=productRepository.findById(feedBackSaveDTO.getProductId());
        Feedback feedback=new Feedback(
                user.orElse(null),
                product.orElse(null),
                feedBackSaveDTO.getRating(),
                feedBackSaveDTO.getComment(),
                feedBackSaveDTO.getCreatedAt()
        );
        feedBackRepository.save(feedback);
        return null;
    }

    @Override
    public List<Feedback> getAllFeedbacks(Long product_id) {
        return feedBackRepository.findByProductId(product_id);
    }


}
