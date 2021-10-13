package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 이 공통 MyView 클래스를 통해 각 컨트롤러가 뷰를 반환하려면 MyView 객체를 생성하고 거기에 뷰 이름만 넣고,
 * 복잡한 dispatcher.forward() 를 직접 생성해서 호출하지 않아도 된다. => 중복 제거
 */
public class MyView {

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
