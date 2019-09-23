package amethyst.service;


import amethyst.po.sys.UserRole;

import java.util.List;

public interface UserRoleServiceI {


    //查询
    public List<UserRole> selectUserRoleById(Long userId);


    public List<UserRole> selectUserRoleList(UserRole userRole);

    //插入
    public int insertUserRole(UserRole userRole);

    //修改
    public int updateUserRole(UserRole userRole);

    //删除
    public int deleteUserRoleByIds(String ids);



}
