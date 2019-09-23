package amethyst.service;


import amethyst.po.sys.RoleMenu;

import java.util.List;

public interface RoleMenuServiceI {

   //查询
    public RoleMenu selectRoleMenuById(Long roleId);


    public List<RoleMenu> selectRoleMenuList(RoleMenu roleMenu);

   //插入
    public int insertRoleMenu(RoleMenu roleMenu);

    //修改
    public int updateRoleMenu(RoleMenu roleMenu);

    //删除
    public int deleteRoleMenuByIds(String ids);



}
