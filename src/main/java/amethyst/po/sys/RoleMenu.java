package amethyst.po.sys;

import amethyst.po.BasePo;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色-菜单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
public class RoleMenu  extends BasePo implements Serializable{
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;
}


