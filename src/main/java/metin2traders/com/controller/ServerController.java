package metin2traders.com.controller;

import metin2traders.com.domain.Server;
import metin2traders.com.resource.SaveServerResource;
import metin2traders.com.resource.ServerResource;
import metin2traders.com.service.ServerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ServerController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ServerService serverService;

    @GetMapping("/servers")
    public Page<ServerResource> getAllServers(Pageable pageable)
    {
        List<ServerResource> servers = serverService.getAllServers(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int serverCount = servers.size();
        return new PageImpl<>(servers, pageable, serverCount);
    }
    @PostMapping("/servers")
    public ServerResource createServer(@Valid @RequestBody SaveServerResource resource) {
        return convertToResource(serverService.createServer(convertToEntity(resource)));
    }
    @PutMapping("/servers/{id}")
    public ServerResource updateTag(@PathVariable(name = "id") Long serverId, @Valid @RequestBody SaveServerResource resource) {
        return convertToResource(serverService.updateServer(serverId, convertToEntity(resource)));
    }
    @DeleteMapping("/servers/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(name = "id") Long serverId) {
        return serverService.deleteServer(serverId);
    }

    private Server convertToEntity(SaveServerResource resource) {
        return mapper.map(resource, Server.class);
    }


    private ServerResource convertToResource(Server entity) {
        return mapper.map(entity, ServerResource.class);
    }
}
