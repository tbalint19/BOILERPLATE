package com.base.defaultAdmin.controller.defaultAdminAuth;

import com.base.defaultAdmin.http.request.ResetRequest;
import com.base.defaultAdmin.http.response.SuccessResponse;
import com.base.defaultAdmin.model.defaultAdminAuth.defaultAdmin.DefaultAdmin;
import com.base.defaultAdmin.model.defaultAdminAuth.defaultAdminPasswordReset.DefaultAdminPasswordReset;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class ResetController extends AbstractAuthAPI {

    @GetMapping("/start")
    public SuccessResponse startProcess(@RequestParam String credential){
        DefaultAdmin defaultAdmin = defaultAdminRepository.findByUsername(credential);
        if (defaultAdmin == null){
            return new SuccessResponse(false);
        }
        DefaultAdminPasswordReset defaultAdminPasswordReset = new DefaultAdminPasswordReset(defaultAdmin);
        defaultAdminPasswordResetRepostory.save(defaultAdminPasswordReset);
        emailServiceController.sendResetEmail(defaultAdminPasswordReset);
        return new SuccessResponse(true);
    }

    @PostMapping("/finish")
    public SuccessResponse finishProcess(@RequestBody ResetRequest request){
        DefaultAdmin defaultAdmin = defaultAdminRepository.findByUsername(request.getUsername());
        if (defaultAdmin == null){
            return new SuccessResponse(false);
        }
        DefaultAdminPasswordReset defaultAdminPasswordReset = defaultAdminPasswordResetRepostory.findByCode(request.getCode());
        if (defaultAdminPasswordReset == null) {
            return new SuccessResponse(false);
        }
        defaultAdmin.setPassword(request.getPassword());
        securityManager.secure(defaultAdmin);
        defaultAdminRepository.save(defaultAdmin);
        return new SuccessResponse(true);
    }

}
