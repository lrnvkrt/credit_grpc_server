package data.init;

import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseInitializer {
    private static Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

    private final Jdbi jdbi;

    public DatabaseInitializer(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public void initialize() {
        try {
            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS financial_model (
                    id SERIAL PRIMARY KEY,
                    cif VARCHAR(255) NOT NULL,
                    age INT,
                    annual_income DOUBLE PRECISION,
                    total_monthly_debt_payment DOUBLE PRECISION,
                    employment_status INT,
                    reference_id VARCHAR(255),
                    amount DOUBLE PRECISION,
                    purpose INT,
                    term INT,
                    decision INT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );
            """;


            jdbi.useHandle(handle -> handle.execute(createTableSQL));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
