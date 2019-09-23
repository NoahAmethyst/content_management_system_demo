package amethyst.service.impl;

import amethyst.exception.ServiceException;
import amethyst.mapper.PostMapper;
import amethyst.po.sys.Post;
import amethyst.service.PostServiceI;
import amethyst.vo.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostServiceI {

    @Autowired
    private PostMapper postMapper;

    //查询
    @Override
    public List<Post> selectPostList(Post post) {
        return postMapper.selectPostList(post);
    }

    @Override
    public Post getPostByIds(Long postId) {
        try {
            return postMapper.getPostByIds(postId);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    //修改

    @Override
    public void updatePost(Post post) {
        try {
            postMapper.updatePost(post);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    //插入

    @Override
    public void insertPost(Post post) {
        try {
            postMapper.insertPost(post);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }



    //删除

    @Override
    public void deletePostByIds(String ids) {
        try {
            postMapper.deletePostByIds(ids.split(","));
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }




}
