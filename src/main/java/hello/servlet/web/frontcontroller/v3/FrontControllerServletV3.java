package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 프론트 컨트롤러 V3
 */
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {

        // put(매핑 URL, 호출될 컨트롤러)
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestURI 조회
        String requestURI = request.getRequestURI();

        // 실제 호출할 컨트롤러를 controllerMap 에서 찾는다.
        ControllerV3 controller = controllerMap.get(requestURI);

        // 만약 없는 경우
        if (controller == null) {

            // 404(SC_NOT_FOUND) 상태 코드를 반환
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 파라미터 정보
        Map<String, String> paramMap = createParamMap(request);

        // 컨트롤러가 반환한 논리 뷰 이름
        ModelView mv = controller.process(paramMap);
        String viewName = mv.getViewName();

        // 실제 물리 경로가 있는 MyView 객체
        MyView view = viewResolver(viewName);

        // 뷰 객체를 통해서 HTML 화면을 렌더링
        // 뷰 객체의 render() 는 모델 정보도 함께 받는다.
        view.render(mv.getModel(), request, response);
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
