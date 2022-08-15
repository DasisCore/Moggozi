package com.JJP.restapiserver.service.challenge;

import com.JJP.restapiserver.domain.dto.challenge.ReviewRequestDto;
import com.JJP.restapiserver.domain.dto.challenge.ReviewResponseDto;
import com.JJP.restapiserver.domain.dto.challenge.ReviewUpdateRequestDto;
import com.JJP.restapiserver.domain.entity.challenge.Review;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {
    ReviewResponseDto registerReview(ReviewRequestDto reviewRequestDto);
    List<ReviewResponseDto> getReviewList(Long challenge_id);
    ReviewResponseDto updateReview(ReviewUpdateRequestDto reviewUpdateRequestDto);
    ResponseEntity deleteReview(Long review_id);
}
