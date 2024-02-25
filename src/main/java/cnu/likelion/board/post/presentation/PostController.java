package cnu.likelion.board.post.presentation;

import cnu.likelion.board.auth.Auth;
import cnu.likelion.board.post.application.PostService;
import cnu.likelion.board.post.presentation.request.PostUpdateRequest;
import cnu.likelion.board.post.presentation.request.PostWriteRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // TODO [4단계] PostControllerTest를 참고하여 포스트 작성 API를 적절히 설계하시오
    @PostMapping("/posts")
    public ResponseEntity<Void> write(
            // TODO [4단계] 3단계에서 만든 Auth 를 사용해 회원 정보를 받아옵니다.
            // TODO [4단계] 요청 본문에 포함된 데이터를 적절한 Request dto로 매핑합니다.
            @Auth Long memberId,
            @RequestBody PostWriteRequest postWriteRequest
    ) {
        // TODO [4단계] 포스트를 생성하고, 생성된 아이디를 Location 헤더에 /posts/{postId} 값으로 넣어주세요
        Long postId = postService.write(memberId, postWriteRequest.title(), postWriteRequest.content());
        return ResponseEntity.created(URI.create("/posts/" + postId)).build();
    }

    @PutMapping("/posts/{id}")
    // TODO [4단계] PostControllerTest를 참고하여 포스트 수정 API를 적절히 설계하시오
    public void update(
            @Auth Long memberId,
            @PathVariable("id") Long id,
            @RequestBody PostUpdateRequest postUpdateRequest
            ) {
        postService.update(memberId, id, postUpdateRequest.title(), postUpdateRequest.content());
    }

    // TODO [4단계] PostControllerTest를 참고하여 포스트 제거 API를 적절히 설계하시오
    @DeleteMapping("/posts/{id}")
    public void delete(
            @Auth Long memberId,
            @PathVariable("id") Long id
    ) {
        postService.delete(memberId, id);
    }
}
