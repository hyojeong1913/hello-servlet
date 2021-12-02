package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 회원 등록 폼
 *
 * @Controller 어노테이션
 * : 스프링이 자동으로 스프링 빈으로 등록 (내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 된다.)
 * : 스프링 MVC 에서 애노테이션 기반 컨트롤러로 인식
 */
@Controller
public class SpringMemberFormControllerV1 {

    /**
     * @RequestMapping 어노테이션
     * : 요청 정보를 매핑
     * : 해당 URL이 호출되면 이 메서드가 호출된다.
     *
     * @return
     */
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {

        // 모델과 뷰 정보를 담아서 반환
        return new ModelAndView("new-form");
    }
}
