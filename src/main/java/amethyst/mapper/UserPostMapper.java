package amethyst.mapper;

import amethyst.po.sys.UserPost;

import java.util.List;

public interface UserPostMapper {
    //查询
    public UserPost selectUserPostById(Long userId);

    public List<UserPost> selectUserPostList(UserPost userPost);

    //插入
    public int insertUserPost(UserPost userPost);

    //修改
    public int updateUserPost(UserPost userPost);

    //删除
    public int deleteUserPostById(Long userId);


    public int deleteUserPostByIds(String[] userIds);
}
