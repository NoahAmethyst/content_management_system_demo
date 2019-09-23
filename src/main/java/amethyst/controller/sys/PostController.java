package amethyst.controller.sys;

import amethyst.controller.BaseController;
import amethyst.exception.ServiceException;
import amethyst.po.sys.Post;
import amethyst.service.PostServiceI;
import amethyst.vo.AjaxResult;
import amethyst.vo.TableDataInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 岗位页面
 * post
 */

@RestController
@RequestMapping("/sys/post")
@Slf4j
public class PostController extends BaseController  {

    @Autowired
    private PostServiceI postService;

    /**
     * 锚定页面
     * @return
     */
    @GetMapping
    public ModelAndView post(){
        ModelAndView mv=new ModelAndView("/sys/post");
        return mv;
    }

    /**
     * 初始化表格
     * @param post
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo list(Post post){
//        //设置第一页初始值
//        PageHelper.startPage(1,10,"post_id asc");
//        List<Post> postList= postService.selectPostList(post);
//        //逻辑分页
////        PageInfo pageInfo=new PageInfo(postList);
//        TableDataInfo tableDataInfo=new TableDataInfo();
//        tableDataInfo.setRows(postList);
//        tableDataInfo.setTotal(20L);
//        return tableDataInfo;

        startPage();
        List<Post> postList= postService.selectPostList(post);
        return getTableDataInfo(postList);
    }

    /**
     * 获取所有Post
     * 数据传入user
     * @return
     */
    @GetMapping("/listAll")
    public AjaxResult listAll(){
        try {
            return success(postService.selectPostList(new Post()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return error(e.getMessage());
        }
    }

    /**
     * 添加职位
     * @return
     */
    @PostMapping
    public AjaxResult add(@RequestBody  Post post){
        try {
            postService.insertPost(post);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }

    }

    /**
     * 删除 可批量删除
     * @param ids
     * @return
     */

    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable String ids){
        try {
            postService.deletePostByIds(ids);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }

    /**
     * 修改模态框回填
     * 这他妈就是前端做的要我来做
     * @param postId
     * @return
     */

    @GetMapping("/{postId}")
    public AjaxResult getPostById(@PathVariable Long postId ){
        try {
            return success(postService.getPostByIds(postId));
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }

    /**
     * 修改
     * 前端传回json格式
     * @param post
     * @return
     */

    @PutMapping
    public AjaxResult update(@RequestBody Post post){
        try {
            postService.updatePost(post);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return error();
        }
    }





}
