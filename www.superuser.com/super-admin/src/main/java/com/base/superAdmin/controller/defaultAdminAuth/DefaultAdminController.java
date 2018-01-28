package com.base.superAdmin.controller.defaultAdminAuth;

import com.base.superAdmin.http.response.SuccessResponse;
import com.base.superAdmin.model.defaultAdminAuth.defaultAdmin.DefaultAdmin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/default")
public class DefaultAdminController extends AbstractDefaultAdminAPI {

    @GetMapping("/all")
    public List<DefaultAdmin> getAllDefaultAdmin() {
        return defaultAdminRepository.findAll();
    }

    @PostMapping("/save")
    public SuccessResponse saveDefaultAdmin(@RequestBody DefaultAdmin defaultAdmin) {
        if (defaultAdmin.getId() == null) {
            securityManager.secure(defaultAdmin);
        }
        defaultAdminRepository.save(defaultAdmin);
        return new SuccessResponse(true);
    }

}
