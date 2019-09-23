package amethyst.po.sys;

import amethyst.po.BasePo;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户-角色中间类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
public class UserRole  extends BasePo implements Serializable{
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
}


