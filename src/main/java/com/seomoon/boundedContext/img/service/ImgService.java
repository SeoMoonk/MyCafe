package com.seomoon.boundedContext.img.service;

import com.seomoon.boundedContext.img.config.ImgConfigProps;
import com.seomoon.boundedContext.img.model.ImgTarget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImgService {

    private final ImgConfigProps imgConfigProps;

    public Map<String, String> imgSaveInLocal(MultipartFile uploadfile, ImgTarget imgTarget) throws IOException {

        String baseUrl = "";

        if (imgTarget.equals(ImgTarget.CAFE_IMG)) {
            baseUrl = "src/main/resources/static/img/cafeImg/";
        } else {
            baseUrl = "src/main/resources/static/img/postImg/";
        }

        Map<String, String> uploadResultMap = new HashMap<>();
        String code = "F-1";
        String msg = "이미지 파일이 아닙니다.";
        String result = imgConfigProps.getDefaultImg();

        if (!uploadfile.isEmpty()) {
            String fType = StringUtils.getFilenameExtension(uploadfile.getOriginalFilename());

            if (fType.equals("jpg") || fType.equals("png") || fType.equals("jpeg")
                    || fType.equals("JPG") || fType.equals("PNG") || fType.equals("JPEG")) {

                String projectRoot = System.getProperty("user.dir"); // 현재 작업 디렉토리(프로젝트 루트 디렉토리)

                File newFileName = new File(projectRoot + "/" + baseUrl + uploadfile.getOriginalFilename());

                uploadfile.transferTo(newFileName);

                String[] split = newFileName.getAbsolutePath().split("static");

                code = "S-1";
                msg = "이미지 업로드에 성공하였습니다";
                result = split[1]; // 상대 경로로 저장된 파일의 경로
            }
        }

        uploadResultMap.put("code", code);
        uploadResultMap.put("msg", msg);
        uploadResultMap.put("result", result);

        return uploadResultMap;
    }
}
