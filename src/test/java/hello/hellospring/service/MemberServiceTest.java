package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("string1");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(findMember.getId()).isEqualTo(member.getId());
    }

    @Test
    void 중복회원조회() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(
                IllegalStateException.class
                , () -> memberService.join(member2)
        );

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //람다식을 실행시켰을 때 앞의 예외가 나와야 한다.

        //then
    }

    @Test
    void findAll() {

    }

    @Test
    void findOne() {
    }
}