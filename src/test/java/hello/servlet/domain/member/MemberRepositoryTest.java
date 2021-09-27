package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 회원 저장소 테스트 코드
 */
class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    /**
     * 각 테스트가 끝날 때, 다음 테스트에 영향을 주지 않도록 각 테스트의 저장소를 clearStore() 를 호출해서 초기화
     */
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    /**
     * 회원 저장 테스트 코드
     */
    @Test
    void save() {

        // given
        Member member = new Member("hello", 20);

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Member findMember = memberRepository.findById(savedMember.getId());

        assertThat(findMember).isEqualTo(savedMember);
    }

    /**
     * 회원 목록 조회 테스트 코드
     */
    @Test
    void findAll() {

        // given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> result = memberRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}
