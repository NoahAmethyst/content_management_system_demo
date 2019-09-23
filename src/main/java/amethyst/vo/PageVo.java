package amethyst.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 页码工具类
 * 配合PageHelper使用
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {

    //当前页码
    private Integer pageNo=1;
    //页面大小
    private Integer pageSize=10;
    //排序参照列
    private String orderColumn;
    //排序方式
    private String orderStyle;


}
