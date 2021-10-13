package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 회원 저장 컨트롤러 V2
 */
public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 파라미터 조회
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // Member 객체 생성
        Member member = new Member(username, age);

        // Member 객체를 MemberRepository 를 통해서 저장
        memberRepository.save(member);

        // Model 에 데이터 보관
        request.setAttribute("member", member);

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
