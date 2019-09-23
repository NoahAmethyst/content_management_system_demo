package amethyst.controller.sys;

import amethyst.controller.BaseController;
import amethyst.po.sys.RoleMenu;
import amethyst.service.RoleMenuServiceI;
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
 * 角色-菜单关联
 */
@Controller
@RequestMapping("/sys/roleMenu")
@ResponseBody
@Slf4j
public class RoleMenuController extends BaseController {
	private static final long serialVersionUID = 1L;

    @Autowired
    private RoleMenuServiceI roleMenuService;

    /**
     * 锚定页面
     * @return
     */
    @RequiresPermissions("sys:roleMenu:view")
    @GetMapping
    public ModelAndView view(){
        ModelAndView mv = new ModelAndView("/sys/roleMenu");
        return mv;
    }

    /**
     * 初始化表格
     * @param roleMenu
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:roleMenu:list")
    public TableDataInfo list(RoleMenu roleMenu) {
        try {
            startPage();
            List<RoleMenu> list = roleMenuService.selectRoleMenuList(roleMenu);
            return this.getTableDataInfo(list);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("服务器正忙，请稍后再试");
        }
    }

    /**
     * 删除
     * 联级
     * @param ids
     * @return
     */
    @DeleteMapping("/remove/{ids}")
    @RequiresPermissions("sys:roleMenu:remove")
    public AjaxResult remove(@PathVariable String ids) {
        try {
            roleMenuService.deleteRoleMenuByIds(ids);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }

    /**
     * 插入
     * @return
     */
    @GetMapping("/add")
    public String add(){
        return  "/sys/roleMenu/add";
    }

    /**
     * 增加
     * @param roleMenu
     * @return
     */
    @PostMapping
    @RequiresPermissions("sys:roleMenu:add")
    public AjaxResult add(@RequestBody RoleMenu roleMenu){

        try {
            roleMenuService.insertRoleMenu(roleMenu);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }

    /**
     * 查询
     * @param roleId
     * @return
     */
    @GetMapping("/{roleId}")
    public AjaxResult getByRoleMenuById(@PathVariable Long roleId){
        try {
            RoleMenu roleMenu = roleMenuService.selectRoleMenuById(roleId);
            return success(roleMenu);
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }

    }

    /**
     * 修改
     * @param roleMenu
     * @return
     */
    @PutMapping
    @RequiresPermissions("sys:roleMenu:edit")
    public AjaxResult update(@RequestBody RoleMenu roleMenu){

        try {
            roleMenuService.updateRoleMenu(roleMenu);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }
}
