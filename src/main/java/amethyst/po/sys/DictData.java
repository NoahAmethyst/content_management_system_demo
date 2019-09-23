package amethyst.po.sys;

import lombok.*;
import amethyst.po.BasePo;

import java.io.Serializable;

/**
 * 数据属性实体类
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
public class DictData extends BasePo implements Serializable{
    /**
     * 字典编码
     */
    private Long dictCode;
    /**
     * 字典排序
     */
    private Integer dictSort;
    /**
     * 字典标签
     */
    private String dictLabel;
    /**
     * 字典键值
     */
    private String dictValue;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 样式属性（其他样式扩展）
     */
    private String cssClass;
    /**
     * 表格回显样式
     */
    private String listClass;
    /**
     * 是否默认（Y是 N否）
     */
    private String isDefault;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
}


