package amethyst.service.impl;


import amethyst.dto.sys.DeptDto;
import amethyst.exception.ServiceException;
import amethyst.mapper.DeptMapper;
import amethyst.po.sys.Dept;
import amethyst.service.DeptServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptServiceI {
    @Autowired
    private DeptMapper deptMapper;


    //查询

    @Override
    public List<Dept> selectDeptList(Dept dept) {
        try {
            return deptMapper.selectDeptList(dept);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Dept selectDeptById(long id) {
        try {
            return deptMapper.selectDeptById(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Dept> selectDeptByIds(String ids) {

        try {
            return deptMapper.selectDeptByIds(ids.split(","));
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public DeptDto selectDeptDtoById(Long deptId) {
        try {
            return deptMapper.selectDeptDtoById(deptId);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    //插入

    @Override
    public void insertDept(Dept dept) {
        try {
            Dept info=deptMapper.selectDeptById(dept.getParentId());
            dept.setAncestors(info.getAncestors()+","+dept.getParentId());
            deptMapper.inserDept(dept);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    //修改

    /**
     * 如果修改对象是上级节点，那么同步修改所有下级节点ancestor
     * 同步修改所有上下级节点status
     * @param dept
     */
    @Override
    public void updateDept(Dept dept) {
        try {
            //获取旧ancestors
            String oldancestor=dept.getAncestors();
            //获取新ancestors
            String newancestor=deptMapper.selectDeptById(dept.getParentId()).getAncestors()+","+dept.getParentId();
            //更新ancestors
            dept.setAncestors(dept.getAncestors().replace(oldancestor, newancestor));

            //更新当前节点数据
            deptMapper.updateDept(dept);
            //获取当前节点所有下级节点
            List<Dept> subDepts=deptMapper.selectDeptByIds(dept.getDeptId().toString().split(","));

            //获取当前节点的上级节点
            Dept supDept=deptMapper.selectDeptById(dept.getParentId());

            //如果当前节点存在下级节点，则同步对下级节点ancestor进行更新
            //如果当前节点status设置为1，则同步对下级节点status进行更新
            if(subDepts.size()>0){
                for (Dept subdept:subDepts) {
                    if(dept.getStatus().equals("1")){
                        subdept.setStatus("1");
                    }
                    updateDept(subdept);
                }
            }
            //如果当前节点存在上级节点
            //如果上级节点status为1且当前节点status设置为0，则同步对上级节点status进行更新
            if(supDept!=null){
                if(dept.getStatus().equals("0") && supDept.getStatus().equals("1")){
                    supDept.setStatus("0");
                    updateDept(supDept);
                }
            }

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    //删除 SET del_flag=1

    /**
     * 删除
     * @param ids
     */

    @Override
    public void removeById(String ids) {
        try {
            //判断是否有下级部门
            List<Dept> depts=deptMapper.selectDeptByIds(ids.split(","));
                if(depts.size()>0){
                    throw new ServiceException("存在下级部门，不可直接删除");
            }
            deptMapper.updateDelflag(ids.split(","));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
