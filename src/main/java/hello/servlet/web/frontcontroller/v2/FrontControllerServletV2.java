package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 프론트 컨트롤러 V2
 *
 * 프론트 컨트롤러의 도입으로 MyView 객체의 render() 를 호출하는 부분을 모두 일관되게 처리 가능
 * => 각각의 컨트롤러는 MyView 객체를 생성만 해서 반환하면 된다.
 */
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {

        // put(매핑 URL, 호출될 컨트롤러)
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestURI 조회
        String requestURI = request.getRequestURI();

        // 실제 호출할 컨트롤러를 controllerMap 에서 찾는다.
        ControllerV2 controller = controllerMap.get(requestURI);

        // 만약 없는 경우
        if (controller == null) {

            // 404(SC_NOT_FOUND) 상태 코드를 반환
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // ControllerV2 의 반환 타입이 MyView 이므로 프론트 컨트롤러는 컨트롤러의 호출 결과로 MyView 를 반환 받는다.
        MyView view = controller.process(request, response);

        // view.render() 를 호출하면 forward 로직을 수행해서 JSP 가 실행된다.
        view.render(request, response);
    }
}
