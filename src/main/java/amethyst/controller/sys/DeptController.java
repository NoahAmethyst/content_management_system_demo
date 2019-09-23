package amethyst.controller.sys;

import amethyst.controller.BaseController;
import amethyst.exception.ServiceException;
import amethyst.po.sys.Dept;
import amethyst.service.DeptServiceI;
import amethyst.vo.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

/**
 * 部门页面
 * dept
 */

@RestController
@RequestMapping("/sys/dept")
@Slf4j
public class DeptController extends BaseController  {

    @Autowired
    private DeptServiceI deptService;

    /**
     * 锚定页面
     * @return
     */
    @GetMapping
    public ModelAndView dept(){
        ModelAndView mv=new ModelAndView("/sys/dept");
        return mv;
    }

    /**
     * 初始化表格
     * @param dept
     * @return
     */

    @GetMapping("/list")
    public List<Dept> list(Dept dept){
        try {
            return deptService.selectDeptList(dept);
        } catch (ServiceException e) {
           log.error(e.getMessage());
           return Collections.EMPTY_LIST;
        }
    }

    /**
     * 跳转模态框
     * 树状多级部门显示
     * @return
     */
    @GetMapping("/tree")
    public ModelAndView tree(){
        ModelAndView mv=new ModelAndView("/sys/tree");
        return mv;
    }

    /**
     * 新增部门
     * @param dept
     * @return
     */
    @PostMapping
    public AjaxResult add(@RequestBody Dept dept){
        try {
            deptService.insertDept(dept);
            return success() ;
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return error();
        }
    }

    /**
     * 删除部门
     * 父节点不可随意删除
     * 需要验证子节点
     * @param ids
     * @return
     */
    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable String ids){
        try {
            deptService.removeById(ids);
            return success() ;
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return error();
        }
    }

    /**
     * 修改模态框初始化
     * 数据回显
     * @param deptId
     * @return
     */
   @GetMapping("/{deptId}")
    public AjaxResult getDeptById(@PathVariable Long deptId){
       try {
           return success(deptService.selectDeptDtoById(deptId)) ;
       } catch (ServiceException e) {
           log.error(e.getMessage());
           return error();
       }
    }

    /**
     * 修改数据
     * @param dept
     * @return
     */
    @PutMapping
    public AjaxResult updateDept(@RequestBody Dept dept){
        try {
            deptService.updateDept(dept);
            return success();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return error();
        }
    }






}
