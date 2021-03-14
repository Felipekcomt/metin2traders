package metin2traders.com.service;

import metin2traders.com.domain.Server;
import metin2traders.com.domain.User;
import metin2traders.com.exception.ResourceNotFoundException;
import metin2traders.com.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    private ServerRepository serverRepository;
    @Override
    public Page<Server> getAllServers(Pageable pageable)
    {
        return serverRepository.findAll(pageable);
    }
    @Override
    public Server createServer(Server server) {
        return serverRepository.save(server);
    }

    @Override
    public Server updateServer(Long serverId, Server serverDetails) {
        return serverRepository.findById(serverId).map(server -> {
            server.setName(serverDetails.getName());
            return serverRepository.save(server);
        }).orElseThrow(() -> new ResourceNotFoundException("Server", "Id",serverId));
    }

    @Override
    public ResponseEntity<?> deleteServer(Long serverId) {
        return serverRepository.findById(serverId).map(server -> {
            serverRepository.delete(server);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Server", "Id",serverId));
    }
}
