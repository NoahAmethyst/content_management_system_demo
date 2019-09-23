package amethyst.service.impl;


import amethyst.exception.ServiceException;
import amethyst.mapper.RoleMenuMapper;
import amethyst.po.sys.RoleMenu;
import amethyst.service.RoleMenuServiceI;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleMenuServiceImpl  implements RoleMenuServiceI {
	
	@Autowired
	private RoleMenuMapper roleMenuMapper;

	//查询
	@Override
	public RoleMenu selectRoleMenuById(Long roleId){
		try{
			return roleMenuMapper.selectRoleMenuById(roleId);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}


	@Override
	public List<RoleMenu> selectRoleMenuList(RoleMenu roleMenu){
		try{
			return roleMenuMapper.selectRoleMenuList(roleMenu);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

	//插入
	@Override
	public int insertRoleMenu(RoleMenu roleMenu){
		try{
			return roleMenuMapper.insertRoleMenu(roleMenu);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

	//修改
	@Override
	public int updateRoleMenu(RoleMenu roleMenu){
		try{
			return roleMenuMapper.updateRoleMenu(roleMenu);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

	//删除
	@Override
	public int deleteRoleMenuByIds(String ids){
		try{
			return roleMenuMapper.deleteRoleMenuByIds(ids.split(","));
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

}
