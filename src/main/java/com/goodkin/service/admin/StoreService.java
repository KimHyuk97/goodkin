package com.goodkin.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.model.Pagination;
import com.goodkin.model.ResponseDto;
import com.goodkin.model.store.Store;
import com.goodkin.repository.StoreRepository;
import com.goodkin.service.ResponseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final ResponseService responseService;

    public ModelAndView list(ModelAndView mv, String kind, String keyword, int page, String address, String subAddress) {

        int count = storeRepository.listCount(kind, keyword, address, subAddress);
        
        Pagination paging = new Pagination(page, count);

        List<Store> list = storeRepository.list(kind, keyword, address, subAddress, paging);

        mv.addObject("list", list);
        mv.addObject("paging", paging);
        mv.addObject("page", page);
        mv.addObject("kind", kind);
        mv.addObject("keyword", keyword);
        mv.addObject("address", address);
        mv.addObject("subAddress", subAddress);
        mv.setViewName("admin/store/list");
        return mv;
    }

    public ModelAndView form(ModelAndView mv, String mode, Long storeNo) {

        Store store = new Store();

        if(storeNo != null) {
            System.err.println(storeNo);
            store = storeRepository.getStore(storeNo);
        }

        mv.addObject("store", store);
        mv.addObject("mode", mode);
        mv.setViewName("admin/store/form");
        return mv;
    }

    @Transactional
    public ResponseDto<?> insert(Store store) {

        Store validNameStore = storeRepository.findByName(store.getName());

        if(validNameStore != null) {
            return responseService.responseBuilder("이미 등록된 가맹점 입니다.", null);
        }

        int insert = storeRepository.save(store);

        return responseService.responseBuilder(insert > 0 ? "등록되었습니다." : "등록 실패하였습니다.", null);
    }

    public ResponseDto<?> update(Store store) {

        Store validNameStore = storeRepository.storeNameValidation(store.getStoreNo(), store.getName());

        if(validNameStore != null) {
            return responseService.responseBuilder("이미 등록된 가맹점 입니다.", null);
        }

        int update = storeRepository.update(store);
        
        return responseService.responseBuilder(update > 0 ? "수정되었습니다." : "수정 실패하였습니다.", null);
    }

    public ResponseDto<?> delete(Long storeNo) {
        int delete = storeRepository.delete(storeNo);

        return responseService.responseBuilder(delete > 0 ? "삭제되었습니다." : "삭제 실패하였습니다.", null);
    }
    


}
