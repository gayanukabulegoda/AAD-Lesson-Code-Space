package lk.ijse.notetaker.repository;

import lk.ijse.notetaker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    // This method is used to get the user entity by the user id (created by the spring data jpa)
    UserEntity getUserEntitiesByUserId(String userId);
}
