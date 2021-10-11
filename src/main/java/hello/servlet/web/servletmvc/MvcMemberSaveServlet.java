package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 회원 저장 컨트롤러
 *
 * HttpServletRequest 를 Model 로 사용
 *
 * request 가 제공하는 setAttribute() 를 사용하여 request 객체에 데이터를 보관해서 뷰에 전달 가능
 * => view 는 request.getAttribute() 를 사용해서 데이터를 꺼내서 사용
 */
@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 파라미터 조회
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // Member 객체 생성
        Member member = new Member(username, age);

        // Member 객체를 MemberRepository 를 통해서 저장
        memberRepository.save(member);

        // Model 에 데이터 보관
        request.setAttribute("member", member);

        String viewPath = "/WEB-INF/views/save-result.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
