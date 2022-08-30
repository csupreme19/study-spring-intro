package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional // Jpa는 Transactional 안에서 수행되어야함.
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) throws IllegalStateException{

//        long startTimeMs = System.currentTimeMillis();

//        try {
            // 중복 회원이라면 스킵
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
//        } finally {
//            long endTimeMs = System.currentTimeMillis();
//            long resultTimeMs = endTimeMs - startTimeMs;
//            log.info("join() resultTimeMs: {}ms", resultTimeMs);
//        }


    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(
                m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
//        long startTimeMs = System.currentTimeMillis();
//        try {
            return memberRepository.findAll();
//        } finally {
//            long endTimeMs = System.currentTimeMillis();
//            long resultTimeMs = endTimeMs - startTimeMs;
//            log.info("findMembers() resultTimeMs: {}ms", resultTimeMs);
//        }
    }

    /**
     * 아이디로 회원 조회
     */
    public Member findById(Long id) {
        return memberRepository.findById(id).get();
    }

    /**
     * 이름으로 회원 조회
     */
    public Member findByName(String name) {
        return memberRepository.findByName(name).get();
    }

}
