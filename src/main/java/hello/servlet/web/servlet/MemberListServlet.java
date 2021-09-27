package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 회원 목록 조회
 *
 * 1. memberRepository.findAll() 을 통해 모든 회원을 조회
 * 2. 회원 목록 HTML 을 for 루프를 통해서 회원 수만큼 동적으로 생성하고 응답
 *
 * 단점)
 * 화면이 계속 달라지는 회원의 저장 결과라던가, 회원 목록 같은 동적인 HTML 을 만드는 일은 불가능하므로 매우 복잡하고 비효율적이다.
 *  => 템플릿 엔진을 사용하여 HTML 문서에서 필요한 곳만 코드를 적용해서 동적으로 변경 가능
 *     ( 템플릿 엔진 : JSP, Thymeleaf, Freemarker, Velocity 등이 있다. )
 */
@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();

        w.write("<html>");
        w.write("   <head>");
        w.write("       <meta charset=\"UTF-8\">");
        w.write("       <title>Title</title>");
        w.write("   </head>");
        w.write("   <body>");
        w.write("       <a href=\"/index.html\">메인</a>");
        w.write("       <table>");
        w.write("           <thead>");
        w.write("               <th>id</th>");
        w.write("               <th>username</th>");
        w.write("               <th>age</th>");
        w.write("           </thead>");
        w.write("           <tbody>");

        for (Member member : members) {

            w.write("               <tr>");
            w.write("                   <td>" + member.getId() + "</td>");
            w.write("                   <td>" + member.getUsername() + "</td>");
            w.write("                   <td>" + member.getAge() + "</td>");
            w.write("               </tr>");
        }

        w.write("           </tbody>");
        w.write("       </table>");
        w.write("   </body>");
        w.write("</html>");
    }
}
