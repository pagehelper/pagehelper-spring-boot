package tk.mybatis.pagehelper;

import com.github.pagehelper.parser.defaults.DefaultCountSqlParser;

public class MyCountSqlParser extends DefaultCountSqlParser {
    @Override
    public String getSmartCountSql(String sql, String countColumn) {
        return "/* count */" + super.getSmartCountSql(sql, countColumn);
    }

    @Override
    public String getSimpleCountSql(String sql) {
        return "/* count */" + super.getSimpleCountSql(sql);
    }
}
