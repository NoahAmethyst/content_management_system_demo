package amethyst.po.sys;

import amethyst.po.BasePo;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色-部门
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
public class RoleDept  extends BasePo implements Serializable{
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 部门ID
     */
    private Long deptId;
}


