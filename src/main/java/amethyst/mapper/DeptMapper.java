package amethyst.mapper;

import amethyst.dto.sys.DeptDto;
import amethyst.po.sys.Dept;

import java.util.List;

public interface DeptMapper {

    List<Dept> selectDeptList(Dept dept);

    List<Dept> selectDeptByIds(String[] ids);

    Dept selectDeptById(Long id);

    DeptDto selectDeptDtoById(Long deptId);

    void inserDept(Dept dept);

    void updateDept(Dept dept);

    void updateSubDepts(List<Dept> subDepts);

    void updateDelflag(String[] ids);


}
