package metin2traders.com.service;

import metin2traders.com.domain.Post;
import metin2traders.com.domain.Server;
import metin2traders.com.exception.ResourceNotFoundException;
import metin2traders.com.repository.PostRepository;
import metin2traders.com.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private ServerRepository serverRepository;
    @Autowired
    private PostRepository postRepository;


    @Override
    public Page<Post> getAllPostsByServerId(Long serverId, Pageable pageable) {
        return serverRepository.findById(serverId).map(server-> {
            List<Post> post = server.getPost();
            int postsCount = post.size();
            return new PageImpl<>(post, pageable, postsCount);
        }).orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", serverId));
    }

    @Override
    public ResponseEntity<?> deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        postRepository.delete(post);
        return ResponseEntity.ok().build();
    }

    @Override
    public Post updatePost(Long postId, Post postRequest) {
       Post post  = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
       post.setPost_name(postRequest.getPost_name());
       post.setPrice(postRequest.getPrice());
       post.setServer(postRequest.getServer());
       post.setDescription(postRequest.getDescription());
       return postRepository.save(post);
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
    }

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
