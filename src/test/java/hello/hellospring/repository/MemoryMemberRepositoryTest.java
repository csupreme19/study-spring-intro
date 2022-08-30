package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(MemoryMemberRepositoryTest.class);

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    void tearDown() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        Member given = new Member();
        given.setName("choi");
        memberRepository.save(given);

        Member actual = memberRepository.findById(given.getId()).get();
        log.info("{} == {}", given, actual);
        assertThat(given).isEqualTo(actual);
    }

    @Test
    void findById() {
        Member given = new Member();
        given.setName("choi");
        memberRepository.save(given);

        Member given2 = new Member();
        given.setName("kim");
        memberRepository.save(given2);

        Member actual = memberRepository.findById(given.getId()).get();
        log.info("{} == {}", given.getId(), actual.getId());
        assertThat(given.getId()).isEqualTo(actual.getId());
        log.info("{} != {}", given2.getId(), actual.getId());
        assertThat(given2.getId()).isNotEqualTo(actual.getId());
    }
}