package cnu.likelion.board.post.application;

import cnu.likelion.board.common.exception.NotFoundException;
import cnu.likelion.board.common.exception.UnAuthorizedException;
import cnu.likelion.board.member.domain.Member;
import cnu.likelion.board.member.domain.MemberRepository;
import cnu.likelion.board.post.domain.Post;
import cnu.likelion.board.post.domain.PostRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public Long write(
            Long memberId,
            String title,
            String content
    ) {
        // TODO [4단계] 포스트를 작성하는 로직을 작성하시오. 생성된 포스트 Id를 반환합니다.
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new UnAuthorizedException("존재하지 않는 아이디입니다."));
        Post post = new Post(title, content, member);
        Post saved = postRepository.save(post);
        return saved.getId();
    }

    public void update(
            Long memberId,
            Long postId,
            String title,
            String content
    ) {
        // TODO [4단계] 포스트 수정 로직을 작성하시오.
        // TODO [4단계] 수정 전 포스트에 대한 접근 권한을 확인해야 합니다.
        Post post = postRepository.findById(postId).orElseThrow(() -> new UnAuthorizedException("포스트 정보가 존재하지 않습니다"));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new UnAuthorizedException("회원 정보가 존재하지 않습니다"));
        post.validateAuthority(member);
        post.update(title, content);
    }

    public void delete(
            Long memberId,
            Long postId
    ) {
        // TODO [4단계] 포스트 삭제 로직을 작성하시오.
        // TODO [4단계] 삭제 전 포스트에 대한 접근 권한을 확인해야 합니다.
        Post post = postRepository.findById(postId).orElseThrow(() -> new UnAuthorizedException("포스트 정보가 존재하지 않습니다"));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new UnAuthorizedException("회원 정보가 존재하지 않습니다"));
        post.validateAuthority(member);
        postRepository.delete(post);
    }
}
