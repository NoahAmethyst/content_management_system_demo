package amethyst.service.impl;


import amethyst.exception.ServiceException;
import amethyst.mapper.RoleDeptMapper;
import amethyst.po.sys.RoleDept;
import amethyst.service.RoleDeptServiceI;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleDeptServiceImpl  implements RoleDeptServiceI {
	
	@Autowired
	private RoleDeptMapper roleDeptMapper;

	//查询

	//通过id查询角色和部门关系
	@Override
	public RoleDept selectRoleDeptById(Long roleId){
		try{
			return roleDeptMapper.selectRoleDeptById(roleId);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}



	@Override
	public List<RoleDept> selectRoleDeptList(RoleDept roleDept){
		try{
			return roleDeptMapper.selectRoleDeptList(roleDept);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

	//插入

	@Override
	public int insertRoleDept(RoleDept roleDept){
		try{
			return roleDeptMapper.insertRoleDept(roleDept);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

	//修改

	@Override
	public int updateRoleDept(RoleDept roleDept){
		try{
			return roleDeptMapper.updateRoleDept(roleDept);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

	//删除

	@Override
	public int deleteRoleDeptByIds(String ids){
		try{
			return roleDeptMapper.deleteRoleDeptByIds(ids.split(","));
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

}
