package com.seomoon.base.initData;

import com.seomoon.boundedContext.cafe.model.form.CafeCreateForm;
import com.seomoon.boundedContext.cafe.service.CafeService;
import com.seomoon.boundedContext.member.model.form.MemberJoinForm;
import com.seomoon.boundedContext.member.service.MemberService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.MultipartFile;


@Configuration
@Profile({"dev", "test"})
public class NotProd {

    @Bean
    CommandLineRunner initData(
            MemberService memberService,
            CafeService cafeService
    ) {
        return new CommandLineRunner() {
            @Override
            @Transactional
            public void run(String... args) throws Exception {

/*
                //loginId, password1 and 2, nickname
                String[] loginIdList = {"user1", "user2", "user3", "user4", "admin"};
                String password = "1234";
                String[] nickNameList = {"유저1", "유저2", "유저3", "유저4", "어드민"};

                for(int i=0; i<loginIdList.length; i++) {
                    MemberJoinForm newJoinForm = MemberJoinForm.builder()
                            .loginId(loginIdList[i])
                            .password1(password)
                            .password2(password)
                            .nickname(nickNameList[i])
                            .build();

                    memberService.createMember(newJoinForm);
                }

 */

                /*
                //INTRODUCTION, OPENTYPE NAMETYPE SUBJECT IMG
                String[] cafeNameList = {"강아지 카페", "고양이 카페", "여행 카페", "JAVA 공부 카페", "성북구 맛집 카페"};
                String[] cafeIntroductionList = {
                        "우리집 강아지를 자랑하는 카페입니다",
                        "우리집 냥이님을 칭송하는 카페입니다",
                        "여행을 좋아하는 사람들의 카페입니다",
                        "주에 3번 자바 공부를 기록하는 카페입니다",
                        "성북구 맛집들을 공유하는 카페입니다"
                };
                String[] openTypeList = {"PUBLIC", "PUBLIC", "PRIVATE", "PRIVATE", "PUBLIC"};
                String[] nameTypeList = {"REAL_NAME", "NICK_NAME", "REAL_NAME", "NICK_NAME", "NICK_NAME"};
                String[] subjectList = { "STUDY", "STUDY", "TRAVEL", "STUDY", "FOOD"};
                String[] creatorList = { "user1", "user2", "user3", "user4", "user5"};

                //FIXME how resolve MultipartFile ?? => 곤란
                MultipartFile emptyFile = null;

                for(int i=0; i<5; i++) {

                    CafeCreateForm newCreateForm = CafeCreateForm.builder()
                            .cafeName(cafeNameList[i])
                            .introduction(cafeIntroductionList[i])
                            .openType(openTypeList[i])
                            .nameType(nameTypeList[i])
                            .subject(subjectList[i])
                            .cafeImg(emptyFile)
                            .build();

                    cafeService.createCafe(newCreateForm, creatorList[i]);
                }
                */
            }
        };
    }
}
