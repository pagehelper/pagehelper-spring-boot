package tk.mybatis.pagehelper;

import com.github.pagehelper.parser.defaults.DefaultOrderBySqlParser;

public class MyOrderBySqlParser extends DefaultOrderBySqlParser {

    @Override
    public String converToOrderBySql(String sql, String orderBy) {
        return "/* order-by */" + super.converToOrderBySql(sql, orderBy);
    }

}
