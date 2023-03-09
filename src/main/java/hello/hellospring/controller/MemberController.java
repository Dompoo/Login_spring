package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) { //spring이 자동으로 정보를 넣어 준다.
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/members/edit")
    public String editForm() {
        return "members/editMemberForm";
    }

    @PostMapping("/members/edit")
    public String edit(EditMemberForm form) { //spring이 자동으로 정보를 넣어 준다.

        //System.out.println("cur, edit = " + form.getCurrentName() + ", " + form.getEditName());
        memberService.editMember(form.getCurrentName(),form.getEditName());

        return "redirect:/";
    }
}
