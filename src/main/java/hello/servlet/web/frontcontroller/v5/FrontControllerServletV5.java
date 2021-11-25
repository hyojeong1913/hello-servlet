package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 어댑터 패턴
 * : 프론트 컨트롤러가 다양한 방식의 컨트롤러를 처리할 수 있도록 지원
 *
 * 핸들러 어댑터
 * : 중간에 어댑터 역할을 하는 어댑터로 덕분에 다양한 종류의 컨트롤러를 호출 가능
 *
 * 핸들러
 * : 어댑터가 있기 때문에 컨트롤러의 개념 뿐만 아니라 어떠한 것이든 해당하는 종류의 어댑터만 있으면 다 처리 가능
 */
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // 매핑 정보
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    /**
     * 생성자는 핸들러 매핑과 어댑터를 초기화
     */
    public FrontControllerServletV5() {

        // 핸들러 매핑 초기화
        initHandlerMappingMap();

        // 어댑터 초기화
        initHandlerAdapters();
    }

    /**
     * 핸들러 매핑 초기화
     */
    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV3());
    }

    /**
     * 어댑터 초기화
     */
    private void initHandlerAdapters() {

        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 핸들러 매핑
        Object handler = getHandler(request);

        // 만약 없는 경우
        if (handler == null) {

            // 404(SC_NOT_FOUND) 상태 코드를 반환
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 핸들러를 처리할 수 있는 어댑터 조회
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // 실제 어댑터 호출
        // 어댑터는 handler(컨트롤러)를 호출하고 그 결과를 어댑터에 맞추어 반환
        ModelView mv = adapter.handle(request, response, handler);

        String viewName = mv.getViewName();

        // 실제 물리 경로가 있는 MyView 객체
        MyView view = viewResolver(viewName);

        // 뷰 객체를 통해서 HTML 화면을 렌더링
        // 뷰 객체의 render() 는 모델 정보도 함께 받는다.
        view.render(mv.getModel(), request, response);
    }

    /**
     * 핸들러를 처리할 수 있는 어댑터 조회
     *
     * 만약, handler 가 ControllerV3 인터페이스를 구현했다면, ControllerV3HandlerAdapter 객체가 반환된다.
     *
     * @param handler
     * @return
     */
    private MyHandlerAdapter getHandlerAdapter(Object handler) {

        for (MyHandlerAdapter adapter : handlerAdapters) {

            // handler 를 처리할 수 있는 어댑터 탐색
            if (adapter.supports(handler)) {

                return adapter;
            }
        }

        throw new IllegalArgumentException("HandlerAdapter 를 찾을 수 없습니다. Handler : " + handler);
    }

    /**
     * 핸들러 매핑
     * : 핸들러 매핑 정보인 handlerMappingMap 에서 URL 에 매핑된 핸들러(컨트롤러) 객체를 찾아서 반환
     *
     * @param request
     * @return
     */
    private Object getHandler(HttpServletRequest request) {

        // requestURI 조회
        String requestURI = request.getRequestURI();

        return handlerMappingMap.get(requestURI);
    }

    private MyView viewResolver(String viewName) {

        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
