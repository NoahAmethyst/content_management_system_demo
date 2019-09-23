package amethyst.mapper;

import amethyst.po.sys.RoleMenu;

import java.util.List;

public interface RoleMenuMapper {
       //查询
        public RoleMenu selectRoleMenuById(Long roleId);


        public List<RoleMenu> selectRoleMenuList(RoleMenu roleMenu);

       //插入
        public int insertRoleMenu(RoleMenu roleMenu);

        //修改
        public int updateRoleMenu(RoleMenu roleMenu);

        //删除
        public int deleteRoleMenuById(Long roleId);

        public int deleteRoleMenuByIds(String[] roleIds);

}