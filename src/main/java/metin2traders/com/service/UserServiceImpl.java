package metin2traders.com.service;

import metin2traders.com.domain.User;
import metin2traders.com.exception.ResourceNotFoundException;
import metin2traders.com.repository.UserRepotisory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepotisory userRepotisory;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepotisory.findAll(pageable);
    }

    @Override
    public User createUser(User user) {
        return userRepotisory.save(user);
    }

    @Override
    public User updateUser(Long userId, User userDetails) {
        return userRepotisory.findById(userId).map(user -> {
            user.setName(userDetails.getName());
            user.setLastname(userDetails.getLastname());
            user.setEmail(userDetails.getEmail());
            user.setPhone_number(userDetails.getPhone_number());
            user.setUrl_facebook(userDetails.getUrl_facebook());
            return userRepotisory.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        return userRepotisory.findById(userId).map(user -> {
            userRepotisory.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }
}
