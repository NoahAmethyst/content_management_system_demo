package amethyst.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import java.security.Security;

@Service("permission")
public class ServicePermission {

    public String hasPermi(String permission){
        return isPermittedOperator(permission)? "" : "none";
    }

    private boolean isPermittedOperator(String permission) {
        return SecurityUtils.getSubject().isPermitted(permission);
    }
}
