package amethyst.po.sys;

import amethyst.po.BasePo;
import lombok.*;

import java.io.Serializable;

/**
 * 用户-岗位中间类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
public class UserPost extends BasePo implements Serializable{
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long postId;
}


