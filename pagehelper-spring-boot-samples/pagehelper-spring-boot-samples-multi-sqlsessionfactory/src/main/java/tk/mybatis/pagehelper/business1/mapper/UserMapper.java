package tk.mybatis.pagehelper.business1.mapper;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.pagehelper.business1.domain.User;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAll();
}
