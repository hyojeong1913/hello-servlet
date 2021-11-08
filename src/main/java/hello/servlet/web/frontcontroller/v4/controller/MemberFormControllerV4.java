package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

/**
 * 회원 등록 컨트롤러 V4
 */
public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // new-form 이라는 뷰의 논리 이름만 반환
        return "new-form";
    }
}
