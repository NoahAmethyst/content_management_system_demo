package amethyst.service;


import amethyst.po.sys.Role;

import java.util.List;

public interface RoleServiceI {


    //查询
    public Role selectRoleById(Long roleId);

    public List<Role> selectRoleList(Role role);

   //插入
    public int insertRole(Role role);

    //更新
    public int updateRole(Role role);

    //删除
    public int deleteRoleByIds(String ids);


    List<String> findRolesByUserId(Long userId);
}
