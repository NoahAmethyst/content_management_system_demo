package amethyst.controller.sys;

import amethyst.controller.BaseController;
import amethyst.dto.sys.UserDto;
import amethyst.exception.ServiceException;
import amethyst.po.sys.User;
import amethyst.service.UserServiceI;
import amethyst.vo.AjaxResult;
import amethyst.vo.TableDataInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/sys/user")
@Slf4j
public class UserController extends BaseController {
    @Autowired
    private UserServiceI userService;

    /**
     * 锚定页面
     * @return
     */
    @GetMapping
    public ModelAndView dept(){
        ModelAndView mv=new ModelAndView("/sys/user");
        return mv;
    }

    /**
     *  登陆验证
     * @param user
     * @return
     */
    @PostMapping("/login")
    public AjaxResult login(UserDto user){
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(),user.getPassword());
            //登录，跳转到reaml中做登录验证
            Subject subject = SecurityUtils.getSubject();

            //设置是否记住用户
            token.setRememberMe(user.isRememberme());

            subject.login(token);
            return success("登陆成功");
        }catch (UnknownAccountException e){
            return error("该用户不存在");
        }catch (IncorrectCredentialsException e) {
            return error("用户名或密码不正确");
        }catch (LockedAccountException e){
            return error("该用户已被锁定");
        }
        catch (AuthenticationException e) {
            return error("该用户已注销");
        }
    }


    @GetMapping("/redirectIndex")
    public void redirect(HttpServletResponse response){
        try {
            response.sendRedirect("/index.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/currentUser")
    public AjaxResult currentUser(){
        try {

            return success(userService.selectUserById(1L));
        } catch (Exception e) {
            return error();
        }
    }

    /**
     * 初始化表格
     * @param userDto
     * @return
     */

    @GetMapping("/list")
    public TableDataInfo list(UserDto userDto){
        List<UserDto> userDtoList=new ArrayList<>();
        try {
            startPage();
            userDtoList= userService.selectUserDtoList(userDto);

        } catch (ServiceException e) {
            log.error(e.getMessage());
        }
        return getTableDataInfo(userDtoList);
    }

    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable String ids){
        try {
            userService.deleteUserByIds(ids);
            return success();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return error();
        }
    }

//    @GetMapping("/list")
//    public TableDataInfo list(User user){
//        List<User> userList=new ArrayList<>();
//        try {
//            startPage();
//            userList= userService.selectUserList(user);
//
//        } catch (ServiceException e) {
//            log.error(e.getMessage());
//        }
//        return getTableDataInfo(userList);
//    }




    /**
     * 插入
     * @param userDto
     * @return
     */
    @PostMapping
//    @RequiresPermissions("sys:user:add")
    public AjaxResult add(@RequestBody UserDto userDto){

        try {
            userService.insertUser(userDto);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }

    /**
     * 修改模态框初始化
     * 数据回显
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public AjaxResult getByUserById(@PathVariable Long userId){
        try {
            return success(userService.selectUserDtoById(userId)) ;
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return error();
        }
    }

    /**
     * 修改
     * @param userDto
     * @return
     */
    @PutMapping
//    @RequiresPermissions("sys:user:edit")
    public AjaxResult update(@RequestBody UserDto userDto){

        try {
            userService.updateUser(userDto);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }
//
//    @GetMapping("/success")
//    public String success(){
//        return "success";
//    }
//
//    @GetMapping("/del")
//    @ResponseBody
//    @RequiresPermissions("user:delete")
//    public String delete(){
//
//        return "delete success";
//    }

}
