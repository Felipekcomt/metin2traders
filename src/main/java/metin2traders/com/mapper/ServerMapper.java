package metin2traders.com.mapper;

import metin2traders.com.domain.Server;
import metin2traders.com.resource.ServerResource;
import org.springframework.stereotype.Service;

@Service
public class ServerMapper {
    public Server toEntity(ServerResource resource) {
        if ( resource == null ) {
            return null;
        }
        Server ent = new Server();
        ent.setId(resource.getId());
        ent.setName(resource.getName());
        return ent;
    }
    public Server fromId(Long id) {
        if (id == null){
            return null;
        }
        Server server = new Server();
        server.setId(id);
        return server;
    }
}
