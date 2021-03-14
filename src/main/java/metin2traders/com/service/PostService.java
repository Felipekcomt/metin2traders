package metin2traders.com.service;

import metin2traders.com.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PostService {

    Page<Post> getAllPostsByServerId(Long serverId, Pageable pageable);
    ResponseEntity<?> deletePost(Long postId);
    Post updatePost(Long postId, Post postRequest);
    Post createPost(Post post);
    Post getPostById(Long postId);
    Page<Post> getAllPosts(Pageable pageable);
}
