package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ControllerV4 를 지원하는 어댑터
 */
public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    /**
     * ControllerV4 를 처리할 수 있는 어댑터
     *
     * @param handler
     * @return
     */
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    /**
     * 어댑터가 호출하는 ControllerV4 는 뷰의 이름을 반환하지만
     * 어댑터는 뷰의 이름이 아니라 ModelView 를 만들어서 반환해야 한다.
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

        // handler 를 ControllerV4 로 casting
        ControllerV4 controller = (ControllerV4) handler;

        // paramMap, model 을 만들어서
        Map<String, String> paramMap = createParamMap(request);
        HashMap<String, Object> model = new HashMap<>();

        // 해당 컨트롤러를 호출해서 viewName 을 반환 받는다.
        String viewName = controller.process(paramMap, model);

        // 어댑터 변환
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

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
