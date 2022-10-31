package com.goodkin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodkin.model.review.Review;

@Mapper
public interface ReviewRepository {
    
    // 리뷰 리스트
    public List<Review> getReviews();

    // 리뷰 단일 조회
    public Review getReview(Long reviewNo);
    
    // 리뷰 등록
    public int save(Review review);

    // 리뷰 업데이트
    public int update(Long reviewNo);

    // 리뷰 삭제
    public int delete(Long reviewNo);
}
