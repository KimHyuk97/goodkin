package com.goodkin.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.model.ResponseDto;
import com.goodkin.model.store.Store;
import com.goodkin.service.StoreService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/api/stores")
    @ResponseBody
    public ResponseDto<?> getStores() {
        return storeService.getStores();
    }

    @PostMapping("/api/store/map")
    @ResponseBody
    public ResponseDto<?> getStoreMap(@RequestBody Map<String, String> map) {
        return storeService.getStoresMap(map.get("siDo"), 
            map.get("guGun"), 
            map.get("dong"),
            Integer.parseInt(map.get("page")));
    }
    
    @GetMapping("/admin/store/list")
    public ModelAndView list(ModelAndView mv,
        @RequestParam(required = false, defaultValue = "name") String kind,
        @RequestParam(required = false, defaultValue = "") String keyword,
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "") String address,
        @RequestParam(required = false, defaultValue = "") String subAddress) {

        return storeService.list(mv, kind, keyword, page, address, subAddress);
    }

    @GetMapping("/admin/store/form")
    public ModelAndView form(ModelAndView mv, @RequestParam String mode,
                             @RequestParam(required = false) Long storeNo) {

        return storeService.form(mv, mode, storeNo);
    }

    @PostMapping("/admin/store/insert")
    @ResponseBody
    public ResponseDto<?> insert(@RequestBody Store store) {

        return storeService.insert(store);
    }

    @PostMapping("/admin/store/update")
    @ResponseBody
    public ResponseDto<?> update(@RequestBody Store store) {

        return storeService.update(store);
    }

    @PostMapping("/admin/store/delete/{storeNo}")
    @ResponseBody
    public ResponseDto<?> delete(@PathVariable Long storeNo) {

        return storeService.delete(storeNo);
    }
    
}
