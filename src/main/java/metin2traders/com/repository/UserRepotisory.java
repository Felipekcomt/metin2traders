package metin2traders.com.repository;

import metin2traders.com.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepotisory extends JpaRepository<User, Long> {
}
