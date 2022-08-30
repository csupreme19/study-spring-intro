package hello.hellospring.config;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {

    private final DataSource dataSource;

    private final EntityManager entityManager;

    @Bean
    MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(entityManager);
    }

}
