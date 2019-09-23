package amethyst.service.impl;


import amethyst.exception.ServiceException;
import amethyst.mapper.UserRoleMapper;
import amethyst.po.sys.UserRole;
import amethyst.service.UserRoleServiceI;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserRoleServiceImpl  implements UserRoleServiceI {
	
	@Autowired
	private UserRoleMapper userRoleMapper;

	//查询
	@Override
	public List<UserRole> selectUserRoleById(Long userId){
		try{
			return userRoleMapper.selectUserRoleById(userId);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}


	@Override
	public List<UserRole> selectUserRoleList(UserRole userRole){
		try{
			return userRoleMapper.selectUserRoleList(userRole);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

	//插入
	@Override
	public int insertUserRole(UserRole userRole){
		try{
			return userRoleMapper.insertUserRole(userRole);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

	//修改
	@Override
	public int updateUserRole(UserRole userRole){
		try{
			return userRoleMapper.updateUserRole(userRole);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

	//删除
	@Override
	public int deleteUserRoleByIds(String ids){
		try{
			return userRoleMapper.deleteUserRoleByIds(ids.split(","));
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

}
