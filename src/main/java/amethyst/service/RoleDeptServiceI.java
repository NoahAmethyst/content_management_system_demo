package amethyst.service;


import amethyst.po.sys.RoleDept;

import java.util.List;

public interface RoleDeptServiceI {

    //查询

    public RoleDept selectRoleDeptById(Long roleId);


    public List<RoleDept> selectRoleDeptList(RoleDept roleDept);

    //插入

    public int insertRoleDept(RoleDept roleDept);

    //修改

    public int updateRoleDept(RoleDept roleDept);

    //删除

    public int deleteRoleDeptByIds(String ids);



}
