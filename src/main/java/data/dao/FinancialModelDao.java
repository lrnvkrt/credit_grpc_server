package data.dao;

import data.model.FinancialModel;

import java.util.List;

public interface FinancialModelDao {
    void save(FinancialModel financialModel);
    List<FinancialModel> findAllByCIF(String cif);
    List<FinancialModel> findByDateRangeAndCIF(String startDate, String endDate, String cif);
}
