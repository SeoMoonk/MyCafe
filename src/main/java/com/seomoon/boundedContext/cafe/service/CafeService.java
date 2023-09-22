package com.seomoon.boundedContext.cafe.service;

import com.seomoon.boundedContext.cafe.config.CafeConfigProperties;
import com.seomoon.boundedContext.cafe.model.entity.Cafe;
import com.seomoon.boundedContext.cafe.model.cafeEnum.CafeSubject;
import com.seomoon.boundedContext.cafe.model.cafeEnum.NameType;
import com.seomoon.boundedContext.cafe.model.cafeEnum.OpenType;
import com.seomoon.boundedContext.cafe.model.form.CafeCreateForm;
import com.seomoon.boundedContext.cafe.repository.CafeRespository;
import com.seomoon.boundedContext.cafeMember.model.role.Grade;
import com.seomoon.boundedContext.cafeMember.service.CafeMemberService;
import com.seomoon.boundedContext.member.model.entity.Member;
import com.seomoon.boundedContext.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeService {

    private final CafeRespository cafeRespository;
    private final CafeConfigProperties cafeConfigProps;
    private final MemberService memberService;
    private final CafeMemberService cafeMemberService;

    @Transactional
    public Map<String, String> createCafe(CafeCreateForm cafeCreateForm, String loginId) {

        String cafeName = cafeCreateForm.getCafeName();
        String introduction = cafeCreateForm.getIntroduction();
        OpenType openType = OpenType.valueOf(cafeCreateForm.getOpenType());
        NameType nameType = NameType.valueOf(cafeCreateForm.getNameType());
        CafeSubject subject = CafeSubject.valueOf(cafeCreateForm.getSubject());

        Map<String, String> checkResultMap = checkCreateCafe(cafeName);

        Member memberByLoginId = memberService.getMemberByLoginId(loginId);

        if (checkResultMap.get("code").startsWith("S")) {
            Cafe newCafe = Cafe.builder()
                    .cafeName(cafeName)
                    .introduction(introduction)
                    .isOpen(openType)
                    .nameType(nameType)
                    .subject(subject)
                    .memberLimit(cafeConfigProps.getMemberLimit())
                    .build();

            cafeRespository.save(newCafe);
            cafeMemberService.joinCafe(memberByLoginId, newCafe, Grade.ADMIN);
        }

        return checkResultMap;
    }

    public Map<String, String> checkCreateCafe(String cafeName) {

        Map<String, String> checkResultMap = new HashMap<>();

        String code = "S-1";
        String msg = "유효한 폼 입니다.";

        Optional<Cafe> ObyCafeName = cafeRespository.findByCafeName(cafeName);

        if (ObyCafeName.isPresent()) {
            code = "F-1";
            msg = "이미 사용중인 카페명 입니다.";
        }

        checkResultMap.put("code", code);
        checkResultMap.put("msg", msg);

        return checkResultMap;
    }

    public List<Cafe> getAllCafeList() {

        return cafeRespository.findAll();
    }

    public Map<String, Object> getCafeById(Long id) {

        Map<String, Object> getResultMap = new HashMap<>();

        Optional<Cafe> OCafeById = cafeRespository.findById(id);

        if (OCafeById.isPresent()) {
            getResultMap.put("code", "S-1");
            getResultMap.put("result", OCafeById.get());
        } else {
            getResultMap.put("code", "F-1");
            getResultMap.put("result", null);
        }

        return getResultMap;
    }



}
