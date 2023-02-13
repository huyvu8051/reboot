package io.huyvu.reboot.backend.user.workspace;

import io.huyvu.reboot.backend.entity.UserAccount;
import io.huyvu.reboot.backend.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:28 AM
 */
public interface WpRepository extends JpaRepository<Workspace, Long> {

    @Query("""
           SELECT u 
           FROM UserAccount u 
           WHERE id = :userId
           """)
    UserAccount findAdminById(@Param("userId") long userId);

    @Query(value = """
            SELECT id, title, picture_url AS pictureUrl
            FROM `workspace`
            WHERE id IN (SELECT wp_id FROM `workspace_member` WHERE user_id = :userId)
            ORDER BY `modified_date` DESC
            """, nativeQuery = true)
    List<ListWpItem> findAllWpItem(@Param("userId") long userId);
}
