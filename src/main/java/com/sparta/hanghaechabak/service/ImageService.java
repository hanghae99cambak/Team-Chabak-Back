//package com.sparta.hanghaechabak.service;
//
//import com.sparta.hanghaechabak.repository.BoardRepository;
//import com.sparta.hanghaechabak.security.UserDetailsImpl;
//import com.sparta.hanghaechabak.utils.S3Uploader;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//@Service
//public class ImageService {
//
//    private final BoardRepository boardRepository;
//    private final S3Uploader s3Uploader;
//
//    private final String imageDirName = "static";
//
//    public String imageUpload(
//            MultipartFile multipartFile
//    ) throws IOException {
//        if(multipartFile == null) {
//            throw new NullPointerException("등록하려는 게시글에 이미지가 없습니다.");
//        }
//        String imageUrl = s3Uploader.upload(multipartFile, imageDirName);
//
//        return imageUrl;
//    }
//}
