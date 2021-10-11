package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 회원 등록 폼 컨트롤러
 *
 * dispatcher.forward() : 다른 서블릿이나 JSP 로 이동할 수 있는 기능. 서버 내부에서 다시 호출이 발생
 *
 * /WEB-INF : 이 경로안에 JSP 가 있으면 외부에서 직접 JSP 를 호출 불가능
 *
 * 참고) redirect vs forward
 * redirect
 * : 실제 클라이언트(웹 브라우저)에 응답이 나갔다가, 클라이언트가 redirect 경로로 다시 요청하여 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경됨.
 * forward
 * : 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지 불가능
 */
@WebServlet(name = "mcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String viewPath = "/WEB-INF/views/new-form.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
