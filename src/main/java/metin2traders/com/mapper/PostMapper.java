package metin2traders.com.mapper;

import metin2traders.com.domain.Post;
import metin2traders.com.domain.Server;
import metin2traders.com.resource.PostResource;
import metin2traders.com.resource.SavePostResource;
import org.springframework.stereotype.Service;

@Service
public class PostMapper {
    ServerMapper serverMapper;
    public Post toEntity(SavePostResource resource){
        if ( resource == null ) {
            return null;
        }
        Post ent = new Post();
        ent.setPost_name(resource.getPost_name());
        ent.setPrice(resource.getPrice());
        ent.setDescription(resource.getDescription());
        ent.setServer(serverMapper.fromId(resource.getServerId()));
        return ent;
    }
    public PostMapper(ServerMapper serverMapper){
        this.serverMapper = serverMapper;
    }
    private Long postMapperServerId(Post post){
        if (post == null) {
            return null;
        }
        Server server = post.getServer();
        if ( server == null) {
            return null;
        }
        Long id = server.getId();
        if (id == null) {
            return null;
        }
        return id;
    }
    public PostResource toResource(Post ent) {
        if ( ent == null ) {
            return null;
        }

        PostResource postResource = new PostResource();

        postResource.setId(ent.getId());
        postResource.setPost_name(ent.getPost_name());
        postResource.setPrice(ent.getPrice());
        postResource.setDescription(ent.getDescription());
        postResource.setServerId(postMapperServerId(ent));
        return postResource;
    }
}
