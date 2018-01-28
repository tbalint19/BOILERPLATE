package util.initialLoader;

import com.base.superAdmin.model.superAdminAuth.superAdmin.SuperAdmin;
import com.base.superAdmin.model.superAdminAuth.superAdmin.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import util.security.SecurityManager;

import javax.annotation.PostConstruct;

@Component
public class SuperAdminLoader {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private SuperAdminRepository superAdminRepository;

    @Value("${superuser.username}")
    private String username;

    @Value("${superuser.email}")
    private String email;

    @Value("${superuser.password}")
    private String password;

    @PostConstruct
    public void createSuperAdmin() {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setUsername(username);
        superAdmin.setEmail(email);
        superAdmin.setPassword(password);
        securityManager.secure(superAdmin);
        superAdminRepository.save(superAdmin);
    }
}
