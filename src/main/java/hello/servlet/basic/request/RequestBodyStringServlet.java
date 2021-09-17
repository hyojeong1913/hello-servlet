package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * HTTP message body 에 데이터를 직접 담아서 요청
 *
 * HTTP API 에서 주로 사용, JSON, XML, TEXT
 * 데이터 형식은 주로 JSON 사용
 * POST, PUT, PATCH
 */
@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // HTTP 메시지 바디의 데이터를 InputStream 을 사용해서 직접 읽을 수 있다.
        ServletInputStream inputStream = request.getInputStream();

        // inputStream 은 byte 코드를 반환
        // byte 코드를 우리가 읽을 수 있는 문자 (String) 로 보려면 문자표 (Charset) 를 지정 필요
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("OK");
    }
}
