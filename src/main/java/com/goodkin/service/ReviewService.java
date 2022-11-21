package com.goodkin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.ftp.Ftp;
import com.goodkin.model.ResponseDto;
import com.goodkin.model.review.Review;
import com.goodkin.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final ResponseService responseService;
    private final Ftp ftp;

    public ModelAndView list(ModelAndView mv) {
        
        List<Review> list = reviewRepository.getReviews();

        mv.addObject("list", list);
        mv.setViewName("admin/review/list");

        return mv;
    }

    @Transactional
    public ResponseDto<?> insert(List<MultipartFile> files) {
        List<Review> reviews = new ArrayList<>();
        // 파일 업로드
        if(files != null && !files.isEmpty()) {
            fileUpload(files, reviews);
        }

        // 리뷰 저장
        int insert = reviewRepository.saveAll(reviews);

        return responseService.responseBuilder(insert > 0 ? "등록되었습니다." : "등록 실패하였습니다.", null);
    }

    @Transactional
    public ResponseDto<?> delete(Long reviewNo) {
        
        String path = "/www/review/";        

        Review review = reviewRepository.getReview(reviewNo);

        try {
            ftp.deleteFile(Arrays.asList(review.getFile()), path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        int delete = reviewRepository.delete(reviewNo);
        
        return responseService.responseBuilder(delete > 0 ? "삭제되었습니다." : "삭제 실패하였습니다.", null);
    }


    private void fileUpload(List<MultipartFile> files, List<Review> reviews) {
        String path = "/www/review/";
    
        try {
            List<String> fileNames = ftp.uploadFile(files, path);
            if(!fileNames.isEmpty()) {
                for (String fileName : fileNames) {
                    reviews.add(new Review(fileName, 
                        "https://joeunfc2022.cdn1.cafe24.com/review/"+fileName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
