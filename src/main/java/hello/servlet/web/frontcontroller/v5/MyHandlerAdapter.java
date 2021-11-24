package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 어댑터용 인터페이스
 */
public interface MyHandlerAdapter {

    // 어댑터가 해당 컨트롤러를 처리할 수 있는지 판단하는 메서드
    boolean supports(Object handler);

    /**
     * 어댑터는 실제 컨트롤러를 호출하고, 그 결과로 ModelView 를 반환
     * 실제 컨트롤러가 ModelView 를 반환하지 못하면, 어댑터가 ModelView 를 직접 생성해서라도 반환해야 한다.
     * 이 어댑터를 통해서 실제 컨트롤러가 호출된다.
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws ServletException
     * @throws IOException
     */
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
