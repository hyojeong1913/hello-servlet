package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @RequestMapping 은 메서드 단위로 적용
 *  => 컨트롤러 클래스를 유연하게 하나로 통합 가능
 *
 * 클래스 레벨
 * - @RequestMapping("/springmvc/v2/members")
 *
 * 메서드 레벨
 * - @RequestMapping("/new-form")
 *      => /springmvc/v2/members/new-form
 * - @RequestMapping("/save")
 *      => /springmvc/v2/members/save
 * - @RequestMapping
 *      => /springmvc/v2/members
 */
@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    /**
     * 회원 등록 폼
     *
     * @return
     */
    @RequestMapping("/new-form")
    public ModelAndView newForm() {

        return new ModelAndView("new-form");
    }

    /**
     * 회원 저장
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");

        mv.addObject("member", member);

        return mv;
    }

    /**
     * 회원 목록
     * 
     * @return
     */
    @RequestMapping
    public ModelAndView members() {

        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);

        return mv;
    }
}