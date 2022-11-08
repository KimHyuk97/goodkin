package com.goodkin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.model.Pagination;
import com.goodkin.model.ResponseDto;
import com.goodkin.model.store.StoreInquiry;
import com.goodkin.repository.StoreInquiryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreInquiryService {

    private final StoreInquiryRepository storeInquiryRepository;
    private final ResponseService responseService;

    /**
     * 관리자 가맹문의 리스트 페이지
     * 
     */
    public ModelAndView list(ModelAndView mv, String kind, String keyword, int page, String address,
            String subAddress) {
        int count = storeInquiryRepository.listCount(kind, keyword, address, subAddress);

        Pagination paging = new Pagination(page, count);

        List<StoreInquiry> list = storeInquiryRepository.list(kind, keyword, address, subAddress, paging);

        mv.addObject("list", list);
        mv.addObject("paging", paging);
        mv.addObject("page", page);
        mv.addObject("kind", kind);
        mv.addObject("keyword", keyword);
        mv.addObject("address", address);
        mv.addObject("subAddress", subAddress);
        mv.setViewName("admin/storeInquiry/list");
        return mv;
    }

    /**
     * 관리자 가맹문의 상세페이지
     * 
     */
    public ModelAndView form(ModelAndView mv, String mode, Long storeInquiryNo) {
        StoreInquiry storeInquiry = new StoreInquiry();

        if(storeInquiryNo != null) {
            System.err.println(storeInquiryNo);
            storeInquiry = storeInquiryRepository.getStoreInquiry(storeInquiryNo);
        }

        mv.addObject("storeInquiry", storeInquiry);
        mv.addObject("mode", mode);
        mv.setViewName("admin/storeInquiry/form");
        return mv;
    }

    /**
     * 가맹문의 등록
     * 
     */
    @Transactional
    public ResponseDto<?> insert(StoreInquiry storeInquiry) {
        System.err.println("storeInquiry : "+storeInquiry);
        int insert = storeInquiryRepository.save(storeInquiry);

        return responseService.responseBuilder(insert > 0 ? "등록되었습니다." : "등록 실패하였습니다.", null);
    }

    /**
     * 관리자 가맹문의 업데이트
     * 
     */
    @Transactional
    public ResponseDto<?> update(StoreInquiry storeInquiry) {
        System.err.println("storeInquiry : "+storeInquiry);
        int update = storeInquiryRepository.update(storeInquiry);
        
        return responseService.responseBuilder(update > 0 ? "수정되었습니다." : "수정 실패하였습니다.", null);
    }

    /**
     * 관리자 가맹문의 삭제
     * 
     */
    @Transactional
    public ResponseDto<?> delete(Long storeInquiryNo) {
        int delete = storeInquiryRepository.delete(storeInquiryNo);

        return responseService.responseBuilder(delete > 0 ? "삭제되었습니다." : "삭제 실패하였습니다.", null);
    }

}
