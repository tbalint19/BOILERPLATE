package com.base.superAdmin.controller.superAdminAuth;


import com.base.superAdmin.http.request.ResetRequest;
import com.base.superAdmin.http.response.SuccessResponse;
import com.base.superAdmin.model.superAdminAuth.superAdmin.SuperAdmin;
import com.base.superAdmin.model.superAdminAuth.superAdminPasswordReset.SuperAdminPasswordReset;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reset")
public class ResetController extends AbstractAuthAPI {

    @GetMapping("/start")
    public SuccessResponse startProcess(@RequestParam String credential){
        SuperAdmin superAdmin = superAdminRepository.findByUsername(credential);
        if (superAdmin == null){
            return new SuccessResponse(false);
        }
        SuperAdminPasswordReset superAdminPasswordReset = new SuperAdminPasswordReset(superAdmin);
        superAdminPasswordResetRepository.save(superAdminPasswordReset);
        emailServiceController.sendResetEmail(superAdminPasswordReset);
        return new SuccessResponse(true);
    }

    @PostMapping("/finish")
    public SuccessResponse finishProcess(@RequestBody ResetRequest request){
        SuperAdmin superAdmin = superAdminRepository.findByUsername(request.getUsername());
        if (superAdmin == null){
            return new SuccessResponse(false);
        }
        SuperAdminPasswordReset superAdminPasswordReset = superAdminPasswordResetRepository.findByCode(request.getCode());
        if (superAdminPasswordReset == null) {
            return new SuccessResponse(false);
        }
        superAdmin.setPassword(request.getPassword());
        securityManager.secure(superAdmin);
        superAdminRepository.save(superAdmin);
        return new SuccessResponse(true);
    }

}
