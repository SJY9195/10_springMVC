package com.ohgiraffers.chap07fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {

    @PostMapping("/single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile, String singleFileDescription, Model model){
        System.out.println("singleFile = " + singleFile);     //singlefile 객체를 이용해서 사진을 가져와서 쓸 수 있다!!!

        /*
        * StandardMultipartHttpServletRequest
        *  - spring 에서 multipart 요청을 처리하기 위한 클래스
        * $StandardMultipartFile@27427370
        *  - 업로드된 파일을 나타내는 객체.
        *  - 실제 파일 데이터에 대한 접근을 제공한다.
        * */

        System.out.println("singleFileDescription = " + singleFileDescription);

        // 파일을 저장할 경로 설정
        String filePath = "C:/uploads/single";
        File fileDir = new File(filePath);
        if(!fileDir.exists()){
            fileDir.mkdirs();  // 경로가 없으면 생성한다.
        }

        // 사용자가 업로드한 원본 파일 이름.
        String originFileName = singleFile.getOriginalFilename();  //원본 이름과 겹치는 경우를 대비해서 이 객체가 고유한 파일 이름을 만들어서 원본이름과 합친다
                                                                   // 이코드는 원본 파일을 가져오는것.
        // 확장자 추출
        String ext = originFileName.substring(originFileName.lastIndexOf("."));

        System.out.println(originFileName);
        // UUID.randomUUID() 고유 식별자 생성
        String savedName = UUID.randomUUID().toString().replace("-", "") + ext; // 고유한 파일 이름 생성해줄 메소드! (-빼고 깔끔하게 만드려고)

        // 업로드된 파일을 지정된 경로와 고유한 파일 이름으로 저장한다.
        try {
            singleFile.transferTo(new File(filePath + "/" + savedName));

            // 여기에 DB 저장 로직 추가하면 됨.

            model.addAttribute("message","파일 업로드 성공!");

            model.addAttribute("img", "/img/single/" + savedName);

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "파일 업로드 실패!");
            throw new RuntimeException(e);
        }
        return "result";
    }

    @PostMapping("/multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multiFiles, String multiFileDescriptions, Model model) {

        String filePath = "C:/uploads/multiple";
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();  // 경로가 없으면 생성한다.

        }
//      String [] multiFileNames = multiFileDescription.split(","); //디스크립션은 나중에 파일 설명 만들 때 쓰는것이다!
        // 이걸 뿌려 줄때는 for 문으로 imgfiles와 multiFileNames까지

//        List<String> imgfiles = new ArrayList<>();
//
//        for (MultipartFile multiFile : multiFiles) {
//            String originFilename = multiFile.getOriginalFilename();
//            String ext = originFilename.substring(originFilename.lastIndexOf("."));
//            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
//
//            try {
//                multiFile.transferTo(new File(filePath + "/" + savedName)); // 이경로로 멀티파일저장!
//                imgfiles.add("/img/multiple/" + savedName);
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                model.addAttribute("messagemultiple", "파일 업로드 실패!");
//                throw new RuntimeException(e);
//            }
//        }
//
//        model.addAttribute("messagemultiple", "파일 업로드 성공!");
//
//        model.addAttribute("imgmultiple", imgfiles);
//
//        return "result";

        // 설명을 쉼표로 분리하여 리스트로 변환


        String[] descriptionsArray = multiFileDescriptions.split(",");
        List<String> descriptions = Arrays.asList(descriptionsArray);

        // 업로된 파일들과 경로를 저장할 리스트
        List<String> imgfiles = new ArrayList<>();
        List<String> imgDescriptions = new ArrayList<>();

        for (int i = 0; i < multiFiles.size(); i++) {
            MultipartFile multiFile = multiFiles.get(i);
            String originFileName = multiFile.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

            try {
                multiFile.transferTo(new File(filePath + "/" + savedName));
                imgfiles.add("/img/multiple/" + savedName);

                // 파일의 설명이 있을 경우 추가
                if (i < descriptions.size()) {
                    imgDescriptions.add(descriptions.get(i).trim());
                } else {
                    imgDescriptions.add("");
                }


            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("messagemultiple", "파일 업로드 실패!");
                throw new RuntimeException(e);
            }
        }
        model.addAttribute("messagemultiple", "파일 업로드 성공!");
        model.addAttribute("imgmultiple", imgfiles);
        model.addAttribute("imgDescriptions", imgDescriptions);

        return "result";
    }

}
