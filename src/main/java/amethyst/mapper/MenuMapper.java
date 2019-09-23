package amethyst.mapper;

import amethyst.po.sys.Menu;

import java.util.List;

public interface MenuMapper {

        //查询
        public Menu selectMenuById(Long menuId);

        public List<Menu> selectMenuList(Menu menu);

       //插入
        public int insertMenu(Menu menu);

      //修改
        public int updateMenu(Menu menu);

       //删除
        public int deleteMenuById(Long menuId);


        public int deleteMenuByIds(String[] menuIds);


}