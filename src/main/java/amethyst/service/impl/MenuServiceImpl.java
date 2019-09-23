package amethyst.service.impl;


import amethyst.exception.ServiceException;
import amethyst.mapper.MenuMapper;
import amethyst.mapper.RoleMapper;
import amethyst.po.sys.Menu;
import amethyst.po.sys.RoleMenu;
import amethyst.po.sys.UserRole;
import amethyst.service.MenuServiceI;
import amethyst.service.RoleMenuServiceI;
import amethyst.service.RoleServiceI;
import amethyst.service.UserRoleServiceI;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MenuServiceImpl  implements MenuServiceI {
	
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleServiceI roleService;
	@Autowired
	private UserRoleServiceI userRoleService;
	@Autowired
	private RoleMenuServiceI roleMenuService;

	//查询

	//通过数据获取相应Menu
	@Override
	public List<Menu> selectMenuList(Menu menu){
		try{
			return menuMapper.selectMenuList(menu);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}


	//通过Id查询
	@Override
	public Menu selectMenuById(Long menuId){
		try{
			return menuMapper.selectMenuById(menuId);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

	//通过userId获取对应菜单
	@Override
	public List<String> findMenusByUserId(Long userId) {
		List<UserRole> userRoleList= userRoleService.selectUserRoleById(userId);
		List<RoleMenu> roleMenuList=new ArrayList<>();
		Set<String> menuSet=new HashSet<>();
 		for (UserRole userRole:userRoleList) {
			roleMenuList.add(roleMenuService.selectRoleMenuById(userRole.getRoleId()));
		}
 		for(RoleMenu roleMenu:roleMenuList){
			menuSet.add(menuMapper.selectMenuById(roleMenu.getMenuId()).getPerms());
		}
		List<String> menuList=new ArrayList<>(menuSet);
 		return menuList;

	}


	//插入
	@Override
	public int insertMenu(Menu menu){
		try{
			return menuMapper.insertMenu(menu);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

	//修改
	@Override
	public int updateMenu(Menu menu){
		try{
			return menuMapper.updateMenu(menu);
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}

	//删除
	@Override
	public int deleteMenuByIds(String ids){
		try{
			return menuMapper.deleteMenuByIds(ids.split(","));
		} catch (Exception e) {
		    log.error(e.getMessage());
		    throw new ServiceException(e);
		}
	}



}
