package com.goodkin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.ftp.Ftp;
import com.goodkin.model.Pagination;
import com.goodkin.model.ResponseDto;
import com.goodkin.model.customerInquiry.CustomerInquiry;
import com.goodkin.model.customerInquiry.CustomerInquiryFile;
import com.goodkin.repository.CustomerInquiryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerInquiryService {

    private final CustomerInquiryRepository customerInquiryRepository;
    private final ResponseService responseService;
    private final Ftp ftp;

    public ModelAndView list(ModelAndView mv, String kind, String keyword, int page, String type) {
        int count = customerInquiryRepository.listCount(kind, keyword, type);
        
        Pagination paging = new Pagination(page, count);

        List<CustomerInquiry> list = customerInquiryRepository.list(kind, keyword, type, paging);

        mv.addObject("list", list);
        mv.addObject("paging", paging);
        mv.addObject("page", page);
        mv.addObject("kind", kind);
        mv.addObject("keyword", keyword);
        mv.addObject("type", type);
        mv.setViewName("admin/customerInquiry/list");
        return mv;
    }

    public ModelAndView form(ModelAndView mv, String mode, Long customerInquiryNo) {
        CustomerInquiry customerInquiry = new CustomerInquiry();

        if(customerInquiryNo != null) {
            customerInquiry = customerInquiryRepository.getCustomerInquiry(customerInquiryNo);
            if(customerInquiry != null) {
                // 파일 정보
                customerInquiry.setFiles(customerInquiryRepository.getCustomerInquiryFiles(customerInquiryNo));
            }
        }

        mv.addObject("customerInquiry", customerInquiry);
        mv.addObject("mode", mode);
        mv.setViewName("admin/customerInquiry/form");
        return mv;
    }

    /**
     * 고객문의 저장
     * @param customerInquiry
     * @param files
     * @return
     */
    @Transactional
    public ResponseDto<?> insert(CustomerInquiry customerInquiry, List<MultipartFile> files) {

        // 고객문의 저장
        int insert = customerInquiryRepository.save(customerInquiry);

        // 파일 업로드
        if(files != null && !files.isEmpty()) {
            List<CustomerInquiryFile> customerInquiryFiles = new ArrayList<>();

            fileUpload(files, customerInquiryFiles, customerInquiry.getCustomerInquiryNo());

            // 파일 저장
            customerInquiryRepository.insertFiles(customerInquiryFiles);
        }

        return responseService.responseBuilder(insert > 0 ? "등록되었습니다." : "등록 실패하였습니다.", null);
    }


    /**
     * 고객문의 업데이트
     * @param customerInquiry
     * @param files
     * @param deleteFiles2
     * @return
     */
    @Transactional
    public ResponseDto<?> update(CustomerInquiry customerInquiry, List<MultipartFile> files) {

        // 파일 업로드
        if(files != null && !files.isEmpty()) {
            List<CustomerInquiryFile> customerInquiryFiles = new ArrayList<>();

            fileUpload(files, customerInquiryFiles, customerInquiry.getCustomerInquiryNo());

            // 파일 저장
            customerInquiryRepository.insertFiles(customerInquiryFiles);
        }

        int update = customerInquiryRepository.update(customerInquiry);
        
        return responseService.responseBuilder(update > 0 ? "수정되었습니다." : "수정 실패하였습니다.", null);
    }

    /*
     * 고객문의 삭제
     */
    @Transactional
    public ResponseDto<?> delete(Long customerInquiryNo) {
        
        List<CustomerInquiryFile> customerInquiryFiles = customerInquiryRepository.getCustomerInquiryFiles(customerInquiryNo);
        if(customerInquiryFiles != null && !customerInquiryFiles.isEmpty()) {

            List<String> names = new ArrayList<>();

            for (CustomerInquiryFile customerInquiryFile : customerInquiryFiles) {
                names.add(customerInquiryFile.getFile());
            }
            
            filedelete(names);
        }

        int delete = customerInquiryRepository.delete(customerInquiryNo);
        
        return responseService.responseBuilder(delete > 0 ? "삭제되었습니다." : "삭제 실패하였습니다.", null);
    }

    /*
     * 파일 삭제
     */
    @Transactional
    public ResponseDto<?> deleteFile(Long fileNo) {

        int delete = 0;

        // 파일 삭제
        if(fileNo != null) {

            CustomerInquiryFile customerInquiryFile = customerInquiryRepository.getFile(fileNo);

            if(customerInquiryFile != null) {
                filedelete(List.of(customerInquiryFile.getFile()));
            }

            // 파일 데이터 삭제
            delete = customerInquiryRepository.deleteFiles(List.of(fileNo));

            System.err.println(delete);
        }

        return responseService.responseBuilder(delete > 0 ? "삭제되었습니다." : "삭제 실패하였습니다.", null);
    }


    private void fileUpload(List<MultipartFile> files, 
        List<CustomerInquiryFile> CustomerInquiryFiles,
        Long customerInquiryNo) {
        String path = "/www/customerInquiry/";
    
        try {
            List<String> fileNames = ftp.uploadFile(files, path);
            if(!fileNames.isEmpty()) {
                for (String fileName : fileNames) {
                    CustomerInquiryFiles.add(new CustomerInquiryFile(
                        customerInquiryNo, fileName, 
                        "https://joeunfc2022.cdn1.cafe24.com/customerInquiry/"+fileName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filedelete(List<String> files) {
        String path = "/www/customerInquiry/";        

        try {
            ftp.deleteFile(files, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
