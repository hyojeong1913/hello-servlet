package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Model 파라미터 사용
 *
 * 뷰의 논리 이름인 viewName 직접 반환
 *
 * @RequestParam 어노테이션을 통해 HTTP 요청 파라미터를 받는다.
 *
 * @RequestMapping 어노테이션
 * : URL 만 매칭하는 것이 아니라, HTTP Method 도 함께 구분 가능
 *  => @GetMapping , @PostMapping 으로 더 유용하게 사용 가능
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    /**
     * 회원 등록 폼
     *
     * @return
     */
    @GetMapping("/new-form")
    public String newForm() {

        return "new-form";
    }

    /**
     * 회원 저장
     *
     * @param username
     * @param age
     * @param model
     * @return
     */
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
    ) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);

        return "save-result";
    }

    /**
     * 회원 목록
     *
     * @param model
     * @return
     */
    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";
    }
}
