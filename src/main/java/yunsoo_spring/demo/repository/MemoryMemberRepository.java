package yunsoo_spring.demo.repository;

import org.springframework.stereotype.Repository;
import yunsoo_spring.demo.domain.Member;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{
    private static long sequence=0L;
    private static Map<Long,Member> store = new HashMap<>();

    public void clearStore(){
        store.clear();
    }

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }//haspmap에서 id 가 키이므로 한번에 찾을 수 있다.

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();//value라서 다찾음
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
