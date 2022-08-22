package yunsoo_spring.demo.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yunsoo_spring.demo.domain.Member;
import yunsoo_spring.demo.repository.MemoryMemberRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository=new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
    }

    @AfterEach
    public void AfterEach(){
        memberRepository.clearStore();
    }

    @Test
    public void join() throws Exception{
        Member member=new Member();
        member.setName("spring1");

        Long saveId=memberService.join(member);

        Member findMember=memberRepository.findById(saveId).get();
        assertEquals(member.getName(),findMember.getName());
    }

    @Test
    public void duplicated_member() throws Exception {
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

        memberService.join(member1);
        IllegalStateException e=assertThrows(IllegalStateException.class,
                ()-> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}