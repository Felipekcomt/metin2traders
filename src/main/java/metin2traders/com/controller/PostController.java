package metin2traders.com.controller;

import io.swagger.v3.oas.annotations.Parameter;
import metin2traders.com.domain.Post;
import metin2traders.com.mapper.PostMapper;
import metin2traders.com.resource.PostResource;
import metin2traders.com.resource.SavePostResource;
import metin2traders.com.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PostController  {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostService postService;

    private final PostMapper postMapper;
    public PostController(PostMapper postMapper)
    {
        this.postMapper = postMapper;
    }
    @GetMapping("/posts")
    public Page<PostResource> getAllPosts(Pageable pageable)
    {
        Page<Post> posts = postService.getAllPosts(pageable);
        List<PostResource> resources = posts.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping("/posts/{id}")
    public PostResource getPostById(
            @Parameter(description="Post Id")
            @PathVariable(name = "id") Long postId) {
        return convertToResource(postService.getPostById(postId));
    }

    @PostMapping("/posts")
    public PostResource createPost(@Valid @RequestBody SavePostResource resource)  {
        Post post = postMapper.toEntity(resource);
        return postMapper.toResource(postService.createPost(post));

    }
    @PutMapping("/posts/{id}")
    public PostResource updatePost(@PathVariable(name = "id") Long postId, @Valid @RequestBody SavePostResource resource) {
        Post post = convertToEntity(resource);
        return convertToResource(postService.updatePost(postId, post));
    }
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") Long postId) {
        return postService.deletePost(postId);
    }
    @GetMapping("/tags/{serverId}/posts")
    public Page<PostResource> getAllPostsByServerId(@PathVariable(name = "serverId") Long serverId, Pageable pageable) {
        Page<Post> postsPage = postService.getAllPostsByServerId(serverId, pageable);
        List<PostResource> resources = postsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private PostResource convertToResource(Post entity) {
        return mapper.map(entity, PostResource.class);
    }
    private Post convertToEntity(SavePostResource resource) {
        return mapper.map(resource, Post.class);
    }
}
