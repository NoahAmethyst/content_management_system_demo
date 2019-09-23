package amethyst.shiro.realm;

import amethyst.po.sys.User;
import amethyst.service.*;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.provider.MD5;

import java.util.Arrays;
import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceI userService;
    @Autowired
    private MenuServiceI menuService;
    @Autowired
    private RoleServiceI roleService;

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        /*
            身份(principal) 凭证(credentials) 盐值 realmName
            身份可以随便赋值,比如username或dbUser
            凭证放置是数据库中的密码，匹配token中的password，一样则认证成功，否则认证失败
            如果有盐值的内容时，先把token中的 password，会先找到配置的加密方式和加密次数，再结合盐值，算出加密后的结果与数据密码比较
        */

        //连接数据库，根据用户名查询用户
        User dbUser = null;
        try {
            dbUser=userService.login(username);
            return new SimpleAuthenticationInfo(dbUser,dbUser.getPassword(), ByteSource.Util.bytes(dbUser.getSalt()),getName());
        } catch (UnknownAccountException e) {
            throw e;
        }catch (IncorrectCredentialsException e) {
            throw e;
        }catch (LockedAccountException e){
            throw e;        }
        catch (AuthenticationException e) {
            throw e;        }
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        User user = (User) principalCollection.getPrimaryPrincipal();
        //根据用户信息，获取用户所有角色，获取用户所有权限
        List<String> roles =roleService.findRolesByUserId(user.getUserId());
        List<String> perms=menuService.findMenusByUserId(user.getUserId());

        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(perms);
        return authorizationInfo;
    }

//    public static void main(String[] args) {
//        System.out.println(new Md5Hash("admin","111111",2));
//    }



}

