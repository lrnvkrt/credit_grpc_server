package data.config;

import data.dao.mapper.FinancialModelMapper;
import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;

public class JdbiConfig {

    public static Jdbi createJdbi(DataSource dataSource) {
        Jdbi jdbi = Jdbi.create(dataSource);
        jdbi.registerRowMapper(FinancialModelMapper.class, new FinancialModelMapper());

        return jdbi;
    }
}
