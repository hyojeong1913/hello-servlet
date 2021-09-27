package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 회원 저장소
 *
 * 동시성 문제가 고려되어 있지 않아 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 *
 * 스프링을 사용하면 스프링 빈으로 등록하면 되지만, 최대한 스프링 없이 순수 서블릿 만으로 구현하는 것이 목적이므로 싱글톤 패턴 적용
 * => 싱글톤 패턴은 객체를 단 하나만 생생해서 공유해야 하므로 생성자를 private 접근자로 막아둔다.
 */
public class MemberRepository {

    private Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    /**
     * 회원 저장
     *
     * @param member
     * @return
     */
    public Member save(Member member) {

        member.setId(++sequence);

        store.put(member.getId(), member);

        return member;
    }

    /**
     * 회원 조회
     *
     * @param id
     * @return
     */
    public Member findById(Long id) {
        return store.get(id);
    }

    /**
     * 회원 리스트 조회
     *
     * @return
     */
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * 회원 리스트 초기화
     */
    public void clearStore() {
        store.clear();
    }
}
