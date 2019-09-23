package amethyst.mapper;

import amethyst.po.sys.Role;
import java.util.List;

public interface RoleMapper {

        //查询
        public Role selectRoleById(Long roleId);


        public List<Role> selectRoleList(Role role);

      //插入
        public int insertRole(Role role);

     //更新
        public int updateRole(Role role);

       //删除
        public int deleteRoleById(Long roleId);


        public int deleteRoleByIds(String[] roleIds);

}