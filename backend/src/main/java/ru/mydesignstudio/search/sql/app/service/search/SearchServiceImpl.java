package ru.mydesignstudio.search.sql.app.service.search;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.service.model.ModelService;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ModelService modelService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Collection<String>> search(String query) {
        Validations.assertTrue(StringUtils.isNoneBlank(query), "Query wasn't provided");

        final Collection<Collection<String>> result = new ArrayList<>();

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

        return result;
    }

    @Override
    public Map<Integer, String> findLookupValues(TypeDefinition typeDefinition) {
        Validations.assertNotNull(typeDefinition, "Type definition wasn't provided");

        final PropertyDefinition displayProperty = modelService.findDisplayProperty(typeDefinition);
        final PropertyDefinition primaryKeyProperty = modelService.findPrimaryKeyProperty(typeDefinition);

        // todo, move it to separated class
        final StringBuilder builder = new StringBuilder();
        builder.append("SELECT ")
                .append(typeDefinition.getTableName())
                .append(".")
                .append(primaryKeyProperty.getPropertyColumn())
                .append(", ")
                .append(typeDefinition.getTableName())
                .append(".")
                .append(displayProperty.getPropertyColumn())
                .append(" FROM ")
                .append(typeDefinition.getTableName());

        final Map<Integer, String> values = new HashMap<>();
        jdbcTemplate.query(builder.toString(), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                values.put(
                        rs.getInt(primaryKeyProperty.getPropertyColumn()),
                        rs.getString(displayProperty.getPropertyColumn())
                );
            }
        });
        return values;
    }
}
