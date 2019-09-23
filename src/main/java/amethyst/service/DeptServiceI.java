package amethyst.service;

import amethyst.dto.sys.DeptDto;
import amethyst.po.sys.Dept;

import java.util.List;

public interface DeptServiceI {

    //查询
    List<Dept> selectDeptList(Dept dept);

    Dept selectDeptById(long id);

    List<Dept> selectDeptByIds(String ids);

    DeptDto selectDeptDtoById(Long deptId);

    //插入
    void insertDept(Dept dept);

    //修改
    void updateDept(Dept dept);

    void removeById(String ids);


}
