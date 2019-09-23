package amethyst.dto.sys;

import amethyst.po.sys.Dept;
import lombok.*;

/**
 * 部门实体类外延
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptDto extends Dept {

    private String parentMenuName;
}
