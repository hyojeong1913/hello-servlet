package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

/**
 * 회원 등록 컨트롤러 V3
 */
public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {

        // modelView 를 생성할 때 new-form 이라는 view 의 논리적인 이름 지정 (실제 물리적인 이름은 frontController 에서 처리)
        return new ModelView("new-form");
    }
}
