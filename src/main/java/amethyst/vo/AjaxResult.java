package amethyst.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ajax返回数据
 * @返回参数 状态，消息，数据（对象）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResult {
    private Integer status;
    private String msg;
    private Object data;
}
