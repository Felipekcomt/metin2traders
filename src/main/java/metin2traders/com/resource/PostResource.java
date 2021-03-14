package metin2traders.com.resource;

import lombok.Getter;
import lombok.Setter;
import metin2traders.com.domain.Server;

@Getter
@Setter
public class PostResource {
    private Long id;
    private String post_name;
    private String price;
    private String description;
    private Long serverId;
}
