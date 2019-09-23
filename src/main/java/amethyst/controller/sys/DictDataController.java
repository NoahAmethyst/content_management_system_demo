package amethyst.controller.sys;

import amethyst.controller.BaseController;
import amethyst.po.sys.DictData;
import amethyst.service.DictDataServiceI;
import amethyst.vo.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 数据类型/属性
 */

@RestController
@RequestMapping("/sys/dictData")
public class DictDataController extends BaseController  {

    @Autowired
    private DictDataServiceI dictDataService;


    /**
     * 获取所有属性
     * @param type
     * @return
     */
    @GetMapping("/byType")
    public AjaxResult dictDataByType(String type){
        List<DictData> dictDataList=dictDataService.findDictDataByType(type);
        return success("searching success",dictDataList);
    }

}
