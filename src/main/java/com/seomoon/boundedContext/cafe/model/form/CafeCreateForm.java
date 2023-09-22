package com.seomoon.boundedContext.cafe.model.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CafeCreateForm {

    @Size(min=3, max=25)
    @NotEmpty(message="카페 이름은 3자 이상 25자 미만으로 필수로 입력되어야 합니다.")
    private String cafeName;

    @Size(min=100, max=500)
    @NotEmpty(message="카페 소개는 100자 이상 500자 미만으로 필수로 입력되어야 합니다.")
    private String introduction;

    @NotEmpty
    private String openType;

    @NotEmpty
    private String nameType;

    @NotEmpty
    private String subject;

    private MultipartFile cafeImg;
}



