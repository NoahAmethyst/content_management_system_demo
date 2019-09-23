package amethyst.controller.sys;

import amethyst.controller.BaseController;
import amethyst.po.sys.UserRole;
import amethyst.service.UserRoleServiceI;
import amethyst.vo.AjaxResult;
import amethyst.vo.TableDataInfo;
import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/sys/userRole")
@ResponseBody
@Slf4j
public class UserRoleController extends BaseController {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserRoleServiceI userRoleService;


    @RequiresPermissions("sys:userRole:view")
    @GetMapping
    public ModelAndView view() {
        ModelAndView mv = new ModelAndView("/sys/userRole");
        return mv;
    }


    @GetMapping("/list")
    @RequiresPermissions("sys:userRole:list")
    public TableDataInfo list(UserRole userRole) {
        try {
            startPage();
            List<UserRole> list = userRoleService.selectUserRoleList(userRole);
            return this.getTableDataInfo(list);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("服务器正忙，请稍后再试");
        }
    }


    @DeleteMapping("/remove/{ids}")
    @RequiresPermissions("sys:userRole:remove")
    public AjaxResult remove(@PathVariable String ids) {
        try {
            userRoleService.deleteUserRoleByIds(ids);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }


    @GetMapping("/add")
    public String add() {
        return "/sys/userRole/add";
    }


    @PostMapping
    @RequiresPermissions("sys:userRole:add")
    public AjaxResult add(@RequestBody UserRole userRole) {

        try {
            userRoleService.insertUserRole(userRole);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }


    @GetMapping("/{userId}")
    public AjaxResult getByUserRoleById(@PathVariable Long userId) {
        try {
            List<UserRole> userRoleList = userRoleService.selectUserRoleById(userId);
            UserRole userRole=userRoleList.get(0);
            return success(userRole);
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }

    }


    @PutMapping
    @RequiresPermissions("sys:userRole:edit")
    public AjaxResult update(@RequestBody UserRole userRole) {

        try {
            userRoleService.updateUserRole(userRole);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }
}
