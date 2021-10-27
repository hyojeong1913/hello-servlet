package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

/**
 * 컨트롤러는 뷰의 논리 이름을 반환하고, 실제 물리 위치의 이름은 프론트 컨트롤러에서 처리하도록 단순화
 * => 뷰 이름 중복 제거
 *
 * 예)
 * /WEB-INF/views/new-form.jsp => new-form
 * /WEB-INF/views/save-result.jsp => save-result
 * /WEB-INF/views/members.jsp => members
 */
public class ModelView {

    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
