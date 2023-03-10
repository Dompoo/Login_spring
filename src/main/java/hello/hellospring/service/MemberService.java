package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(String name) {
        validateDuplicateMember(name); //중복회원 검증

        Member member = new Member();
        member.setName(name);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(String name) {
        memberRepository.findByName(name)
                .ifPresent(member1 -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * ID로 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /**
     * 회원 이름 수정
     */
    public Long editMember(String curName, String editName) {
        validateDuplicateMember(editName); //이름 중복 검증

        Member curMember = memberRepository.findByName(curName).get();
        curMember.setName(editName);

        return curMember.getId();
    }

    public Long deleteMember(String name) {
        Member deleteMember = memberRepository.findByName(name).get();
        Long returnId = deleteMember.getId();
        memberRepository.deleteByName(name);
        return returnId;
    }
}
