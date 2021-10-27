package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

/**
 * 회원 저장 컨트롤러 V3
 */
public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    /**
     * 입력한 회원 정보 저장 처리 로직
     *
     * @param paramMap 파라미터 정보
     * @return
     */
    @Override
    public ModelView process(Map<String, String> paramMap) {

        // map 에서 필요한 요청 파라미터를 조회
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // modelView 를 생성할 때 save-result 이라는 view 의 논리적인 이름 지정
        ModelView mv = new ModelView("save-result");

        // 모델은 단순한 map 이므로 모델에 뷰에서 필요한 member 객체를 담고 반환
        mv.getModel().put("member", member);

        return mv;
    }
}
