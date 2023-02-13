/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:26 AM
 */
package io.huyvu.reboot.backend.user.workspace;

import io.huyvu.reboot.backend.entity.UserAccount;
import io.huyvu.reboot.backend.entity.Workspace;
import io.huyvu.reboot.backend.entity.WorkspaceMember;
import io.huyvu.reboot.backend.entity.WorkspaceMemberId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class WpServiceImpl implements WpService {
    private final WpRepository wpRepo;
    private final WpMemberRepository wpMemRepo;

    @Transactional
    @Override
    public Workspace create(String wpNm, long adminId) {
        Workspace wp = new Workspace();
        wp.setTitle(wpNm);
        Workspace savedWp = wpRepo.save(wp);

        UserAccount userAccount = wpRepo.findAdminById(adminId);
        WorkspaceMemberId wpMemKey = new WorkspaceMemberId();
        wpMemKey.setWp(savedWp);
        wpMemKey.setUser(userAccount);

        WorkspaceMember wpMem = new WorkspaceMember();
        wpMem.setKey(wpMemKey);
        wpMem.setAdmin(true);
        wpMemRepo.save(wpMem);

        return savedWp;
    }
}
