package amethyst.service;

import amethyst.dto.sys.UserDto;
import amethyst.po.sys.User;

import java.util.List;

public interface UserServiceI {
    //查询
    List<UserDto> selectUserDtoList(UserDto userDto);
    List<User> selectUserList(User user);
    User selectUserById(Long userId);
    User selectUserDtoById(Long userId);


    //修改
    void updateUser(User user);

    //插入
    Long insertUser(UserDto userDto);

    //删除

    void deleteUserByIds(String ids);

    User login(String username);


}
