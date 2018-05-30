package org.zerhusen.security.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerhusen.model.security.User;

/**
 * Created by stephan on 20.03.16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    //"INSERT INTO USER_AUTHORITY " + "VALUES (1, 1)"
    @Transactional
    @Modifying
    @Query(value = "insert into USER_AUTHORITY (USER_ID, AUTHORITY_ID) select :i,:e", nativeQuery = true)
    void nexo(@Param("i") int i,@Param("e") int e);
    
}
