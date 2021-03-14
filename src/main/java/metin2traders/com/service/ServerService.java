package metin2traders.com.service;

import metin2traders.com.domain.Server;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ServerService {
    Page<Server> getAllServers(Pageable pageable);
    Server createServer(Server server);
    Server updateServer(Long serverId, Server serverDetails);
    ResponseEntity<?> deleteServer(Long serverId);
}
