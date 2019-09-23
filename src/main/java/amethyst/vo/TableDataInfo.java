package amethyst.vo;

import lombok.Data;

import java.util.List;

/**
 * 返回表格参数
 * 配合PageHelper使用
 */
@Data
public class TableDataInfo {
    private long total;
    private List rows;
}
