package com.cafe.coco;

import com.cafe.coco.repository.MemberRepository;
import com.cafe.coco.repository.MemoryMemberRepository;
import com.cafe.coco.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
