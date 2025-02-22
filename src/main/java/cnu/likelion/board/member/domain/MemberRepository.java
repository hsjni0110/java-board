package cnu.likelion.board.member.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

// TODO [1단계] Repository 빈으로 등록하세요.
@Repository
public class MemberRepository {

    private final Map<Long, Member> members = new HashMap<>();
        // TODO [1단계] member에 Id가 중복되지 않도록 세팅하고,
        // TODO [1단계] members 에 id를 Key로, member를 value로 저장하세요.
    private Long sequence = 1L;

    public Member save(Member member) {
        member.setId(sequence++);
        members.put(member.getId(), member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        // TODO [1단계] 저장된 회원 중 id가 일치하는 회원을 찾아 Optional 로 감싸서 반환하세요.
        // TODO [1단계] 만약 id가 일치하는 회원이 없는 경우, 빈 Optional 객체를 반환하세요.
       return Optional.ofNullable(members.get(id));
    }

    public Optional<Member> findByUsername(String username) {
        // TODO [1단계] 저장된 회원 중 username이 일치하는 회원을 찾아 Optional 로 감싸서 반환하세요.
        // TODO [1단계] 만약 username이 일치하는 회원이 없는 경우, 빈 Optional 객체를 반환하세요.
        return members.values()
                .stream()
                .filter(member -> member.getUsername().equals(username))
                .findAny();
    }

    // for test
    public void clear() {
        this.members.clear();
    }
}
