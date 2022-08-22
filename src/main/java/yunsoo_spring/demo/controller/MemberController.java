package yunsoo_spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import yunsoo_spring.demo.domain.Member;
import yunsoo_spring.demo.service.MemberService;

import java.util.List;

@Controller//컴포넌트 스캔과 자동 의존관계 설정
public class MemberController {
    private final MemberService memberService;

    @Autowired//생성자에 @Autowired 에노테이션이 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
    //이런식으로 객체의존관계를 외부에서 넣어주는 것을 DI(dependency injection)이라고 한다.(의존성 주입)
    // 스프링이 주입해준다.
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member=new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members= memberService.findMembers();
        model.addAttribute("members",members);
        return "/members/memberList";
    }
}
