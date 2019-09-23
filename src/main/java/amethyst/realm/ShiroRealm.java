package amethyst.realm;

import amethyst.po.sys.User;

import amethyst.service.PostServiceI;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private PostServiceI postService;

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();


        //连接数据库，根据用户名查询用户
//        User dbUser =userService.findUserByName(username);
        User dbUser=null;
        if(dbUser==null){
            throw new AuthenticationException("用户名不存在");
        }

        /*
            身份(principal) 凭证(credentials) 盐值 realmName
            身份可以随便赋值,比如username或dbUser
            凭证放置是数据库中的密码，匹配token中的password，一样则认证成功，否则认证失败
            如果有盐值的内容时，先把token中的 password，会先找到配置的加密方式和加密次数，再结合盐值，算出加密后的结果与数据密码比较
        */
//        return new SimpleAuthenticationInfo(dbUser,dbUser.getPassword(), ByteSource.Util.bytes(dbUser.getSalt()),getName());
            return  null;
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User) principalCollection.getPrimaryPrincipal();
        //根据用户信息，获取用户所有角色，获取用户所有权限
        List<String> roles = Arrays.asList("role1","role2");
        List<String> perms=Arrays.asList("user:*","role:*");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(perms);
        return authorizationInfo;
    }


}



