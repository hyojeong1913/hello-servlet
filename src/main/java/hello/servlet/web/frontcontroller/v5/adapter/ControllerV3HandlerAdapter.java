package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ControllerV3 를 지원하는 어댑터
 */
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    /**
     * ControllerV3 을 처리할 수 있는 어댑터
     *
     * @param handler
     * @return
     */
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    /**
     * handler 를 컨트롤러 V3로 변환한 다음에 V3 형식에 맞도록 호출
     *
     * ControllerV3는 ModelView 를 반환하므로 그대로 ModelView 를 반환
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);

        return mv;
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
