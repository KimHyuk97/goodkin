package com.goodkin.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class Ftp {

    @Value("${cdn.ip}")
    private String serverIp;

    @Value("${cdn.port}")
    private int serverPort;

    @Value("${cdn.username}")
    private String user;

    @Value("${cdn.password}")
    private String password;

    /**
     * FTP 연결, 로그인
     *
     * @param ftpClient
     * @throws Exception
     */
    private void ftpConnection(FTPClient ftpClient) throws Exception {

        ftpClient.connect(serverIp, serverPort); // FTP 연결
        ftpClient.setControlEncoding("UTF-8"); // FTP 인코딩설정
        int reply = ftpClient.getReplyCode(); // 응답 코드 받기

        // 응답 false 일 때 연결해제 exception 발생
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            throw new Exception(serverIp + " FTP 서버 연결 실패");
        }

        ftpClient.setSoTimeout(1000 * 10); // Timeout 설정
        ftpClient.login(user, password); // FTP 로그인
    }

    /**
     * 파일 업로드
     *
     * @param multipartFile
     * @param path
     * @return boolean
     * @throws SocketException
     * @throws IOException
     * @throws Exception
     */
    public List<String> uploadFile(List<MultipartFile> files, String path) throws IOException, Exception {

        FileInputStream fileInputStream = null;
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8"); // FTP 인코딩 설정

        try {
            ftpConnection(ftpClient);

            boolean dir = ftpClient.changeWorkingDirectory(path);

            // 디렉토리가 없으면 생성
            if (!dir) {
                ftpClient.makeDirectory(path);
            }

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // 파일 타입 설정
            ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalActiveMode(); // Active 모드 설정

            List<String> fileNames = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    // 고유한 파일명 생성
                    String fileName = createFileName(file.getOriginalFilename());
                    // 파일 업로드
                    boolean upload = ftpClient.storeFile(path + fileName, file.getInputStream());

                    if (upload)
                        fileNames.add(fileName);
                }
            }

            return fileNames;

        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }

    }

    /**
     * 파일명 생성
     *
     * @param fileName
     * @return String
     */
    private String createFileName(String fileName) {

        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    /**
     * 파일 확장자 추출
     *
     * @param fileName
     * @return String
     */
    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }

    /**
     * 파일 삭제
     *
     * @param fileNames
     * @param path
     * @return boolean
     * @throws Exception
     */
    public boolean deleteFile(List<String> fileNames, String path) throws Exception {

        FileInputStream fis = null;
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8"); // FTP 인코딩 설정

        try {
            ftpConnection(ftpClient);

            Boolean result = false;

            for (String fileName : fileNames) {
                result = ftpClient.deleteFile(path + fileName);
            }

            return result;
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }

    /**
     * 파일 다운로드
     * @param file
     * @return
     * @throws Exception
     */
    // 단일 파일 다운로드 
    public InputStream download(String path, String file) throws Exception{

        FileInputStream fis = null;
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");  // FTP 인코딩 설정

        InputStream in = null;
        try{

            ftpConnection(ftpClient);

            
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
            if (fis != null) {
                fis.close();
            }
        }

        return in;
    }

    

}
