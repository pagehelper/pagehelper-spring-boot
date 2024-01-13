package tk.mybatis.pagehelper.business2.mapper;

import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.pagehelper.business2.domain.Blog;

import java.util.List;

@Mapper
public interface BlogMapper {

    List<Blog> selectAllBlog();
}
