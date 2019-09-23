package amethyst.controller.sys;

import amethyst.controller.BaseController;
import amethyst.po.sys.Role;
import amethyst.service.RoleServiceI;
import amethyst.vo.AjaxResult;
import amethyst.vo.TableDataInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Role页面
 *
 */
@RestController
@RequestMapping("sys/role")
@Slf4j
public class RoleController extends BaseController {
	private static final long serialVersionUID = 1L;

    @Autowired
    private RoleServiceI roleService;

//    @RequiresPermissions("sys:role:view")
    @GetMapping
    public ModelAndView view(){
        ModelAndView mv = new ModelAndView("/sys/role");
        return mv;
    }

    @GetMapping("/list")
//    @RequiresPermissions("sys:role:list")
    public TableDataInfo list(Role role) {
        try {
            startPage();
            List<Role> list = roleService.selectRoleList(role);
            return this.getTableDataInfo(list);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("服务器正忙，请稍后再试");
        }
    }

    @GetMapping("/listAll")
    public AjaxResult list() {
        try {
            List<Role> list = roleService.selectRoleList(new Role());
            return success(list);
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }


    @DeleteMapping("/remove/{ids}")
//    @RequiresPermissions("sys:role:remove")
    public AjaxResult remove(@PathVariable String ids) {
        try {
            roleService.deleteRoleByIds(ids);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }


    @GetMapping("/add")
    public String add(){
        return  "/sys/role/add";
    }


    @PostMapping
//    @RequiresPermissions("sys:role:add")
    public AjaxResult add(@RequestBody Role role){

        try {
            roleService.insertRole(role);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }


    @GetMapping("/{roleId}")
    public AjaxResult getByRoleById(@PathVariable Long roleId){
        try {
            Role role = roleService.selectRoleById(roleId);
            return success(role);
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }

    }

    @PutMapping
//    @RequiresPermissions("sys:role:edit")
    public AjaxResult update(@RequestBody Role role){

        try {
            roleService.updateRole(role);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }
}
