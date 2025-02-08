package data.dao.impl;

import data.dao.FinancialModelDao;
import data.dao.mapper.FinancialModelMapper;
import data.model.FinancialModel;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class FinancialModelDaoImpl implements FinancialModelDao {
    private final Jdbi jdbi;

    public FinancialModelDaoImpl(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public void save(FinancialModel model) {
        String sql = """
                    INSERT INTO finansial_models.public.financial_model (cif, age, annual_income, total_monthly_debt_payment, employment_status, reference_id, amount, purpose, term, decision)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        jdbi.useHandle(handle -> handle.execute(
                sql,
                model.getCif(),
                model.getAge(),
                model.getAnnualIncome(),
                model.getTotalMonthlyDebtPayment(),
                model.getEmploymentStatus().ordinal(),
                model.getReferenceId(),
                model.getAmount(),
                model.getPurpose().ordinal(),
                model.getTerm(),
                model.getDecision().ordinal()
        ));
    }

    @Override
    public List<FinancialModel> findAllByCIF(String cif) {
        String sql = "SELECT * FROM finansial_models.public.financial_model WHERE cif = ?";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind(0, cif)
                        .map(new FinancialModelMapper())
                        .stream().toList()
        );
    }

    @Override
    public List<FinancialModel> findByDateRangeAndCIF(String startDate, String endDate, String cif) {
        String sql = """
            SELECT * FROM finansial_models.public.financial_model
            WHERE created_at BETWEEN :startDate AND :endDate AND cif = :cif
        """;

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("startDate", startDate)
                        .bind("endDate", endDate)
                        .bind("cif", cif)
                        .map(new FinancialModelMapper())
                        .list()
        );
    }
}
