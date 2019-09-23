package amethyst.service.impl;

import amethyst.dto.sys.UserDto;
import amethyst.exception.ServiceException;
import amethyst.mapper.UserPostMapper;
import amethyst.po.sys.User;
import amethyst.po.sys.UserPost;
import amethyst.service.UserPostServiceI;
import amethyst.service.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserPostServiceImpl implements UserPostServiceI {
    @Autowired
    private UserPostMapper userPostMapper;


    @Override
    public UserPost selectUserPostById(Long userId) {
        try {
            return userPostMapper.selectUserPostById(userId);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserPost> selectUserPostList(UserPost userPost) {
        try {
            return userPostMapper.selectUserPostList(userPost);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public int insertUserPost(UserPost userPost) {
        try {
            return userPostMapper.insertUserPost(userPost);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public int updateUserPost(UserPost userPost) {
        try {
            return userPostMapper.updateUserPost(userPost);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public int deleteUserPostById(Long userId) {
        try {
            return userPostMapper.deleteUserPostById(userId);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public int deleteUserPostByIds(String userIds) {
        try {
            return userPostMapper.deleteUserPostByIds(userIds.split(","));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }
}
