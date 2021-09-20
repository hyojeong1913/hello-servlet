package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 편의 기능 제공
 * - Content-Type, 쿠키, Redirect
 * 
 * http://localhost:8080/response-header
 */
@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // status-line
        response.setStatus(HttpServletResponse.SC_OK);

        // response-headers
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        // Header 편의 메서드
        content(response);
        cookie(response);
        redirect(response);

        PrintWriter writer = response.getWriter();

        writer.println("OK! 안녕하세요!");
    }

    /**
     * Content 편의 메서드
     *
     * response.setHeader("Content-Type", "text/plain;charset=utf-8");
     * response.setContentLength(2); // 생략 시 자동 생성
     *
     * 와 같은 개념
     *
     * @param response
     */
    private void content(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
    }

    /**
     * 쿠키 편의 메서드
     *
     * Set-Cookie: myCookie=good; Max-Age=600;
     * response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
     *
     * 와 같은 개념
     *
     * @param response
     */
    private void cookie(HttpServletResponse response) {

        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 600초

        response.addCookie(cookie);
    }

    /**
     * redirect 편의 메서드
     *
     * Status Code 302
     * Location: /basic/hello-form.html 로 리다이렉트
     *
     * @param response
     * @throws IOException
     */
    private void redirect(HttpServletResponse response) throws IOException {

//        response.setStatus(HttpServletResponse.SC_FOUND); // 302 코드
//        response.setHeader("Location", "/basic/hello-form.html");

        // 위 두 줄을 한 줄로도 가능
        response.sendRedirect("/basic/hello-form.html");
    }
}
