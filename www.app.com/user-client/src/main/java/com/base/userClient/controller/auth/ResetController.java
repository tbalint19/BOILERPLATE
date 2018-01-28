package com.base.userClient.controller.auth;


import com.base.userClient.model.userAuth.ApplicationUser;
import com.base.userClient.model.userAuth.Reset;
import com.base.userClient.model.userAuth.request.ResetRequest;
import com.base.userClient.model.common.response.SuccessResponse;
import com.base.userClient.service.auth.AuthService;
import com.base.userClient.service.auth.ResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reset")
public class ResetController extends AuthAPI {
    @Autowired
    private ResetService resetService;

    @Autowired
    private AuthService authService;

    @GetMapping("/start")
    public SuccessResponse startProcess(@RequestParam String credential){
        ApplicationUser user = userService.getUserByCredential(credential);
        Reset reset = null;
        if (user != null){
            reset = resetService.createReset(user);
            if (reset != null) {
                emailServiceController.sendResetEmail(user, reset);
            }
        }
        return new SuccessResponse(user != null && reset != null);
    }

    @PostMapping("/finish")
    public SuccessResponse finishProcess(@RequestBody ResetRequest request){
        Boolean successful = false;
        ApplicationUser user = userRepository.findByUsername(request.getUsername());
        if (user != null){
            if (resetService.applyReset(user, request.getCode())){
                user.setPassword(authService.hash(request.getPassword()));
                userRepository.save(user);
                successful = true;
            }
        }
        return new SuccessResponse(successful);
    }
}
