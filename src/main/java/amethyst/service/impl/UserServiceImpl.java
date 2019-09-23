package amethyst.service.impl;

import amethyst.dto.sys.UserDto;
import amethyst.exception.ServiceException;
import amethyst.mapper.UserMapper;
import amethyst.mapper.UserPostMapper;
import amethyst.mapper.UserRoleMapper;
import amethyst.po.sys.User;
import amethyst.po.sys.UserPost;
import amethyst.po.sys.UserRole;
import amethyst.service.UserServiceI;
import amethyst.util.StringUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserServiceI {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPostMapper userPostMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    //查询

    //外延
    @Override
    public List<UserDto> selectUserDtoList(UserDto userDto) {
        try {
            return userMapper.selectUserDtoList(userDto);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    //本类
    @Override
    public List<User> selectUserList(User user) {
        try {
            return userMapper.selectUserList(user);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User selectUserById(Long userId) {
        try {
            return userMapper.selectUserById(userId);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User selectUserDtoById(Long userId) {
        try {
            return userMapper.selectUserDtoById(userId);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }



    //根据用户名精确查找
    //匹配用户名、邮箱、手机号
    @Override
    public User login(String username) {
        String loginName=username;
        try {
            User user=userMapper.selectUserByName(loginName);
            if (user==null){
                if(StringUtils.isEmail(username)){
                    String email=username;
                    user=userMapper.selectUserByEmail(email);
                }else if(StringUtils.isPhone(username)){
                    String phone=username;
                    user=userMapper.selectUserByPhone(phone);
                }
            }

            if(user==null){
                throw new UnknownAccountException("该用户不存在");
            }else if(user.getDelFlag().equals("1")){
                throw new LockedAccountException("该用户已被禁用");
            }else if(user.getDelFlag().equals("2")){
                throw new AuthenticationException("该用户已经注销");
            }

            return user;

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    //修改

    @Override
    public void updateUser(User user) {
        try {
            userMapper.updateUser(user);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    //插入

    @Override
    public Long insertUser(UserDto userDto) {
        try {
            userMapper.insertUser(userDto);
            Long newUserId=userDto.getUserId();
            userDto.setUserId(newUserId);
            //用户与岗位关系
//            insertUserPost(userDto);
            //用户与角色关系

            insertUserRelative(userDto);
            return newUserId;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    //插入用户-相关表
    private void insertUserRelative(UserDto userDto) {

        Long userId=userDto.getUserId();

        //用户-岗位
        for(Long postId:userDto.getPostIds()){
            UserPost userPost=new UserPost();
            userPost.setUserId(userId);
            userPost.setPostId(postId);
            userPostMapper.insertUserPost(userPost);
        }
        //用户-角色
        for(Long roleId:userDto.getRoleIds()){
            UserRole userRole=new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleMapper.insertUserRole(userRole);
        }

    }


    //删除 SET del_flag=1

    @Override
    public void deleteUserByIds(String ids) {
        try {
            userMapper.deleteUserByIds(ids.split(","));
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


}
