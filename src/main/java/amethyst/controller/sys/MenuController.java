package amethyst.controller.sys;

import amethyst.controller.BaseController;
import amethyst.po.sys.Menu;
import amethyst.service.MenuServiceI;
import amethyst.vo.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/sys/menu")
public class MenuController extends BaseController {
    private static final long serialVersionUID = 1L;


    @Autowired
    private MenuServiceI menuService;

    /**
     * 锚定页面
     * @return
     */
    @GetMapping
    public ModelAndView view(){
        ModelAndView mv = new ModelAndView("/sys/menu");
        return mv;
    }
//    /**
//     * 初始化表格
//     * @param menu
//     * @return
//     */
    @GetMapping("/list")
    public AjaxResult list(Menu menu) {
        try {
            return success(menuService.selectMenuList(menu));
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }
//
    @GetMapping("/listAll")
    public AjaxResult listAll() {
        try {
            List<Menu> list = menuService.selectMenuList(new Menu());
            return success(list);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("服务器正忙，请稍后再试");
        }
    }
//
//    /**
//     * 跳转icon
//     * @return
//     */
    @GetMapping("/icon")
    public ModelAndView icon(){
        return new ModelAndView("sys/icon");
    }
//
//    /**
//     * 删除菜单
//     * @param ids
//     * @return
//     */
    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        try {
            menuService.deleteMenuByIds(ids);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }
//
//    /**
//     * 添加菜单
//     * 跳转模态框
//     * @return
//     */
    @GetMapping("/add")
    public String add(){
        return  "/sys/menu/add";
    }
//
//    /**
//     * 添加菜单
//     * @param menu
//     * @return
//     */
    @PostMapping
    public AjaxResult add(@RequestBody Menu menu){

        try {
            menuService.insertMenu(menu);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }
//
//    /**
//     * 获取菜单
//     * @param menuId
//     * @return
//     */
    @GetMapping("/{menuId}")
    public AjaxResult getByMenuById(@PathVariable Long menuId){
        try {
            Menu menu = menuService.selectMenuById(menuId);
            return success(menu);
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }

    }
//
//    /**
//     * 跳转模态框
//     * 树状多级部门显示
//     * @return
//     */
    @GetMapping("/tree")
    public ModelAndView tree(){
        ModelAndView mv=new ModelAndView("/sys/menu/tree");
        return mv;
    }
//
//    /**
//     * 修改菜单
//     * @param menu
//     * @return
//     */
    @PutMapping
    public AjaxResult update(@RequestBody Menu menu){

        try {
            menuService.updateMenu(menu);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }



}

