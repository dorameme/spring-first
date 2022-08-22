package yunsoo_spring.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yunsoo_spring.demo.repository.MemberRepository;
import yunsoo_spring.demo.repository.MemoryMemberRepository;
import yunsoo_spring.demo.service.MemberService;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
