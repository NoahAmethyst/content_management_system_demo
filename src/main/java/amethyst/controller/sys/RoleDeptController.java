package amethyst.controller.sys;

import amethyst.controller.BaseController;
import amethyst.po.sys.RoleDept;
import amethyst.service.RoleDeptServiceI;
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
@RequestMapping("/sys/roleDept")
@ResponseBody
@Slf4j
public class RoleDeptController extends BaseController {
	private static final long serialVersionUID = 1L;

    @Autowired
    private RoleDeptServiceI roleDeptService;

    /**
     * 锚定页面
     * @return
     */
    @RequiresPermissions("sys:roleDept:view")
    @GetMapping
    public ModelAndView view(){
        ModelAndView mv = new ModelAndView("/sys/roleDept");
        return mv;
    }

    /**
     * 初始化表格
     * @param roleDept
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:roleDept:list")
    public TableDataInfo list(RoleDept roleDept) {
        try {
            startPage();
            List<RoleDept> list = roleDeptService.selectRoleDeptList(roleDept);
            return this.getTableDataInfo(list);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("服务器正忙，请稍后再试");
        }
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @DeleteMapping("/remove/{ids}")
    @RequiresPermissions("sys:roleDept:remove")
    public AjaxResult remove(@PathVariable String ids) {
        try {
            roleDeptService.deleteRoleDeptByIds(ids);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }

    /**
     * 跳转新增模态框
     * @return
     */
    @GetMapping("/add")
    public String add(){
        return  "/sys/roleDept/add";
    }

    /**
     * 插入
     * @param roleDept
     * @return
     */
    @PostMapping
    @RequiresPermissions("sys:roleDept:add")
    public AjaxResult add(@RequestBody RoleDept roleDept){

        try {
            roleDeptService.insertRoleDept(roleDept);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }

    /**
     * 精确查询
     * @param roleId
     * @return
     */
    @GetMapping("/{roleId}")
    public AjaxResult getByRoleDeptById(@PathVariable Long roleId){
        try {
            RoleDept roleDept = roleDeptService.selectRoleDeptById(roleId);
            return success(roleDept);
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }

    }

    /**
     * 修改
     * @param roleDept
     * @return
     */
    @PutMapping
    @RequiresPermissions("sys:roleDept:edit")
    public AjaxResult update(@RequestBody RoleDept roleDept){

        try {
            roleDeptService.updateRoleDept(roleDept);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }
}
