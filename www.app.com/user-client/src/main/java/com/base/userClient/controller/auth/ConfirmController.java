package com.base.userClient.controller.auth;


import com.base.userClient.model.userAuth.ApplicationUser;
import com.base.userClient.model.userAuth.request.ConfirmRequest;
import com.base.userClient.model.common.response.SuccessResponse;
import com.base.userClient.model.userAuth.response.TokenResponse;
import com.base.userClient.service.auth.ConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/confirm")
public class ConfirmController extends AuthAPI {

    @Autowired
    private ConfirmationService confirmationService;

    @GetMapping("/start")
    public SuccessResponse start(@RequestParam String credential){
        ApplicationUser user = userService.getUserByCredential(credential);
        Boolean canSend = user != null;
        Boolean shouldSend = !user.getConfirmed();
        if (canSend && shouldSend){
            emailServiceController.sendConfirmationEmail(user);
        }
        return new SuccessResponse(canSend && shouldSend);
    }

    @PostMapping("/finish")
    public TokenResponse finish(@RequestBody ConfirmRequest request){
        String token = confirmationService.attemptConfirm(
                request.getCredential(), request.getCode());
        return new TokenResponse(token);
    }
}
