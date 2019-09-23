package amethyst.dto.sys;

import amethyst.po.sys.Dept;
import amethyst.po.sys.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 用户实体类外延
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends User {

    private String startTime;
    private String endTime;

    private Dept dept;

    private Long[] postIds;
    private Long[] roleIds;

    private String validateCode;
    //是否记住密码
    private boolean rememberme;

}
