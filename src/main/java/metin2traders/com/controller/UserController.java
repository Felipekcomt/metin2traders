package metin2traders.com.controller;
import metin2traders.com.domain.User;
import metin2traders.com.resource.SaveUserResource;
import metin2traders.com.resource.UserResource;
import metin2traders.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Page<UserResource> getAllUsers(Pageable pageable) {
        List<UserResource> users = userService.getAllUsers(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
    int usersCount = users.size();
            return new PageImpl<>(users, pageable, usersCount);
    }
    @PostMapping("/users")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource) {
      return convertToResource(userService.createUser(convertToEntity(resource)));
    }
    @PutMapping("/users/{id}")
    public UserResource updateUser ( @PathVariable(name = "id") Long userId, @Valid @RequestBody SaveUserResource resource) {
        return convertToResource(userService.updateUser(userId,convertToEntity(resource)));
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteTag (@PathVariable(name = "id") Long userId)
    {
        return userService.deleteUser(userId);
    }
    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }
    private UserResource convertToResource(User entity) {

        return mapper.map(entity, UserResource.class);
    }
}
