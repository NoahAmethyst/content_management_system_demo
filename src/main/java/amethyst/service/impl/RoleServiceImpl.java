package amethyst.service.impl;


import amethyst.exception.ServiceException;
import amethyst.mapper.RoleMapper;
import amethyst.po.sys.Role;
import amethyst.po.sys.UserRole;
import amethyst.service.RoleServiceI;
import amethyst.service.UserRoleServiceI;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class RoleServiceImpl  implements RoleServiceI {
	
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleServiceI userRoleService;


	@Override
	public Role selectRoleById(Long roleId){
		try{
			return roleMapper.selectRoleById(roleId);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}


	@Override
	public List<Role> selectRoleList(Role role){
		try{
			return roleMapper.selectRoleList(role);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}


	@Override
	public int insertRole(Role role){
		try{
			return roleMapper.insertRole(role);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}


	@Override
	public int updateRole(Role role){
		try{
			return roleMapper.updateRole(role);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}


	@Override
	public int deleteRoleByIds(String ids){
		try{
			return roleMapper.deleteRoleByIds(ids.split(","));
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

	@Override
	public List<String> findRolesByUserId(Long userId) {
		List<UserRole> userRoleList= userRoleService.selectUserRoleById(userId);
		List<Long> roleIds=new ArrayList<>();
		for (UserRole userRole:userRoleList) {
			roleIds.add(userRole.getRoleId());
		}
		Set<String> roleKeys=new HashSet<>();
		for(Long roleId:roleIds){
			roleKeys.add(roleMapper.selectRoleById(roleId).getRoleKey());
		}
		return new ArrayList<String>(roleKeys);
	}

}
