package amethyst.service;


import amethyst.po.sys.Post;

import java.util.List;

public interface PostServiceI {

    List<Post> selectPostList(Post post);

    void insertPost(Post post);

    void deletePostByIds(String ids);

    Post getPostByIds(Long postId);

    void updatePost(Post post);
}
