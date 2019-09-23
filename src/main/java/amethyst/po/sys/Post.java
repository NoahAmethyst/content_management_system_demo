package amethyst.po.sys;

import amethyst.po.BasePo;
import lombok.*;

import java.io.Serializable;

/**
* 岗位实体类
 *
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
public class Post extends BasePo implements Serializable{
    /**
     * 岗位ID
     */
    private Long postId;
    /**
     * 岗位编码
     */
    private String postCode;
    /**
     * 岗位名称
     */
    private String postName;
    /**
     * 显示顺序
     */
    private Integer postSort;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
}


