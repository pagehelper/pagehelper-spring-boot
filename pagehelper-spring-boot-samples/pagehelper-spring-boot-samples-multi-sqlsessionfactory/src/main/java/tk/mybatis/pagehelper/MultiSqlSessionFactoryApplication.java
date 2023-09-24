package tk.mybatis.pagehelper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.pagehelper.business1.domain.User;
import tk.mybatis.pagehelper.business1.mapper.UserMapper;
import tk.mybatis.pagehelper.business2.domain.Blog;
import tk.mybatis.pagehelper.business2.mapper.BlogMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class MultiSqlSessionFactoryApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MultiSqlSessionFactoryApplication.class, args);
    }

    @Autowired
    @Qualifier("business1DataSource")
    private DataSource business1DataSource;

    @Autowired
    @Qualifier("business2DataSource")
    private DataSource business2DataSource;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    @Qualifier("business1SqlSessionFactory")
    private SqlSessionFactory business1SqlSessionFactory;

    @Autowired
    @Qualifier("business2SqlSessionFactory")
    private SqlSessionFactory business2SqlSessionFactory;

    @Override
    public void run(String... args) throws Exception {
        runScript(business1DataSource, "business1.sql");
        runScript(business2DataSource, "business2.sql");
        Page<User> userPage = PageHelper.startPage(1, 20);
        List<User> users = userMapper.selectAll();
        System.out.println("Total: " + userPage.getTotal());
        for (User user : users) {
            System.out.println("Name: " + user.getName());
        }
        List<Interceptor> business1 = business1SqlSessionFactory.getConfiguration().getInterceptors();
        List<Interceptor> business2 = business2SqlSessionFactory.getConfiguration().getInterceptors();
        System.out.println("business1 interceptor "+ business1);
        System.out.println("business2 interceptor "+ business2);
    }

    private void runScript(DataSource dataSource, String sqlPath) throws SQLException, IOException {
        try (Connection connection = dataSource.getConnection();
             Reader reader = Resources.getResourceAsReader(sqlPath)) {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.runScript(reader);
        }
    }
}
