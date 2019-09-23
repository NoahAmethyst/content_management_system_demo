package amethyst.controller;

import amethyst.util.ServletUtils;
import amethyst.util.StringUtils;
import amethyst.vo.AjaxResult;
import amethyst.vo.PageVo;
import amethyst.vo.TableDataInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.BeanUtils;

import java.util.List;

public class BaseController {
    private Integer successStatus=200;
    private Integer failStatus=500;


    /**
     * 成功
     * @return
     */
    public AjaxResult success(){
        return  new AjaxResult(successStatus,"success",null);
    }
    public AjaxResult success(String msg){
        return  new AjaxResult(successStatus,msg,null);
    }

    public AjaxResult success(Object data){
        return  new AjaxResult(successStatus,"success",data);
    }

    public AjaxResult success(String msg,Object data){
        return  new AjaxResult(successStatus,msg,data);
    }


    /**
     * 失败
     * @return
     */
    public AjaxResult error(){
        return  new AjaxResult(failStatus,"failed",null);

    }

    public AjaxResult error(String msg){
        return  new AjaxResult(failStatus,msg,null);
    }



    public AjaxResult error(String msg,Object data){
        return  new AjaxResult(failStatus,msg,data);
    }


    /**
     * 分页参数初始化
     */
    public void startPage(){
        PageVo pageVo=new PageVo();
        try {
            BeanUtils.populate(pageVo,ServletUtils.getRequest().getParameterMap());
            String orderByColumn=StringUtils.toUnderScoreCase(pageVo.getOrderColumn());
            PageHelper.startPage(pageVo.getPageNo(), pageVo.getPageSize(),orderByColumn+" "+pageVo.getOrderStyle());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    /**
     * 设置表格参数并传回具体处理器
     * @param data
     * @return
     */
    public TableDataInfo getTableDataInfo(List data){
            TableDataInfo tableDataInfo=new TableDataInfo();
            tableDataInfo.setRows(data);
            tableDataInfo.setTotal(new PageInfo(data).getTotal());
            return tableDataInfo;
    }

}
