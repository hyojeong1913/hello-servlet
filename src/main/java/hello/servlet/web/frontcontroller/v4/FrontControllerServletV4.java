package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 프론트 컨트롤러 V4
 */
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {

        // put(매핑 URL, 호출될 컨트롤러)
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestURI 조회
        String requestURI = request.getRequestURI();

        // 실제 호출할 컨트롤러를 controllerMap 에서 찾는다.
        ControllerV4 controller = controllerMap.get(requestURI);

        // 만약 없는 경우
        if (controller == null) {

            // 404(SC_NOT_FOUND) 상태 코드를 반환
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);

        // 모델 객체를 프론트 컨트롤러에서 생성
        Map<String, Object> model = new HashMap<>();

        // 뷰의 논리 이름을 직접 반환
        String viewName = controller.process(paramMap, model);

        // 컨트롤러가 직접 뷰의 논리 이름을 반환하므로 이 값을 사용해서 실제 물리 뷰를 찾을 수 있다.
        MyView view = viewResolver(viewName);

        view.render(model, request, response);
    }

    /**
     * 뷰 리졸버
     *
     * 컨트롤러가 반환한 논리 뷰 이름을 실제 물리 뷰 경로로 변경
     * 실제 물리 경로가 있는 MyView 객체를 반환
     *
     * @param viewName
     * @return
     */
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    /**
     * HttpServletRequest 에서 파라미터 정보를 꺼내서 Map 으로 변환하여 컨트롤러에 전달하면서 호출
     *
     * @param request
     * @return
     */
    private Map<String, String> createParamMap(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }
}
