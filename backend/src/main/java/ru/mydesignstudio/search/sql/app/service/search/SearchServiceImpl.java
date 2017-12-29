package ru.mydesignstudio.search.sql.app.service.search;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class SearchServiceImpl implements SearchService {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Collection<String>> search(String query) {
        Validations.assertTrue(StringUtils.isNoneBlank(query), "Query wasn't provided");

        final Collection<Collection<String>> result = new ArrayList<>();

        /*
        jdbcTemplate.query(query, new RowMapper<Collection<String>>() {
            @Override
            public Collection<String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                final ResultSetMetaData metaData = rs.getMetaData();
                final int columnCount = metaData.getColumnCount();
                Collection<String> row = new ArrayList<>();
                if (result.size() == 0) {
                    for (int i = 1; i < columnCount; i++) {
                        row.add(metaData.getColumnName(i));
                    }
                    result.add(row);
                }
                row = new ArrayList<>();
                for (int i  = 1; i < columnCount; i++) {
                    row.add(rs.getString(i));
                }
                return row;
            }
        });
        */

        return result;
    }
}
