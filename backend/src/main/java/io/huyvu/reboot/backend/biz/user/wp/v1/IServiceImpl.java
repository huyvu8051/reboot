
package io.huyvu.reboot.backend.biz.user.wp.v1;


import io.huyvu.reboot.backend.util.FulltextSearchUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IServiceImpl implements IService {
    private final Repository wpRepo;
    private final JavaMailSender mailSender;
    private final Environment environment;

    @Transactional
    @Override
    public WpDetails create(String wpNm, long adminId) {
        long wpId = wpRepo.selectLastInsertId();
        wpRepo.insertWpMem(wpId, adminId, true);
        return wpRepo.selectWpDetails(wpId, adminId).orElseThrow();
    }

    @Override
    public List<ListWpItem> getList(long userId) {
        return wpRepo.selectWpItem(userId);
    }

    @Override
    public WpDetails getDetails(long wpId, long id) {
        return wpRepo.selectWpDetails(wpId, id).orElseThrow();
    }

    @Override
    public List<UserAccount> searchMembers(String keyword) {
        if (keyword.isBlank() || keyword.isEmpty()) return new ArrayList<>();
        return wpRepo.searchMembers(FulltextSearchUtils.getSearchCommand(keyword));
    }

    @Override
    public void inviteToWp(InviteMemberReq req) {
        var mems = req.getMems();
        for (InviteMemberRequestDetail mem : mems) {
            var oneByUsername = wpRepo.findOneByUsername(mem.getEmail());
            if (oneByUsername.isEmpty()) {
                sendMailInviteToWp(mem.getEmail(), req.getWId(), req.getMsg());
            } else {
                var aLong = wpRepo.selectMember(req.getWId(), oneByUsername.get().id());
                if (aLong.isEmpty()) {
                    wpRepo.insertWpMem(req.getWId(), oneByUsername.get().id(), false);
                }
            }

        }
    }

    private void sendMailInviteToWp(String email, long wId, String msg) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Invite to workspace");


        String port = environment.getProperty("local.server.port");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String ip = request.getRemoteAddr();
        message.setText(msg + "\n http://" + ip + ":" + port + "/wp/join?wId=" + wId);


        try{
            mailSender.send(message);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }


}
