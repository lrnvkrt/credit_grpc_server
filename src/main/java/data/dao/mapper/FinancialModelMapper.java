package data.dao.mapper;

import data.model.FinancialModel;
import org.example.credit_assessment.Decision;
import org.example.credit_assessment.EmploymentStatus;
import org.example.credit_assessment.Purpose;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FinancialModelMapper implements RowMapper<FinancialModel> {
    @Override
    public FinancialModel map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new FinancialModel(
                rs.getString("cif"),
                rs.getDouble("total_monthly_debt_payment"),
                rs.getDouble("annual_income"),
                rs.getInt("term"),
                rs.getInt("age"),
                EmploymentStatus.forNumber(Integer.parseInt(rs.getString("employment_status"))),
                rs.getString("reference_id"),
                rs.getDouble("amount"),
                Purpose.forNumber(Integer.parseInt(rs.getString("purpose"))),
                Decision.forNumber(Integer.parseInt(rs.getString("decision"))),
                rs.getTimestamp("created_at").toLocalDateTime()
        );
    }
}
