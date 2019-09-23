package amethyst.mapper;

import amethyst.dto.sys.UserDto;
import amethyst.po.sys.User;

import java.util.List;

public interface UserMapper {

    //查询
    List<UserDto> selectUserDtoList(UserDto userDto);
    List<User> selectUserList(User user);

    User selectUserById(Long userId);

    User selectUserDtoById(Long userId);

    //修改
    void updateUser(User user);

    //插入
    int insertUser(UserDto userDto);

    //删除

    void deleteUserByIds(String[] ids);


    User selectUserByName(String loginName);

    User selectUserByPhone(String phone);

    User selectUserByEmail(String email);


}
