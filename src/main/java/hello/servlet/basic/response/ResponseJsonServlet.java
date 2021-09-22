package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MessageBody JSON 응답
 *
 * HTTP 응답으로 JSON 을 반환할 때는 content-type 을 application/json 로 지정해야 한다.
 *
 * Jackson 라이브러리가 제공하는 objectMapper.writeValueAsString() 를 사용하면 객체를 JSON 문자로 변경 가능
 *
 * http://localhost:8080/response-json
 *
 * 참고)
 * - application/json 은 스펙상 utf-8 형식을 사용하도록 정의되어 있다.
 * - charset=utf-8 과 같은 추가 파라미터를 지원하지 않으므로, application/json 가 아닌
 *   application/json;charset=utf-8 이라고 전달하는 것은 의미 없는 파라미터를 추가한 것이 된다.
 */
@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        // {"username":"kim","age":20}
        String result = objectMapper.writeValueAsString(helloData);

        response.getWriter().write(result);
    }
}
