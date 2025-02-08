package service;


import data.dao.FinancialModelDao;
import data.model.FinancialModel;
import io.grpc.stub.StreamObserver;
import org.example.credit_assessment.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CreditCheckService extends CreditCheckServiceGrpc.CreditCheckServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(CreditCheckService.class.getName());
    private final FinancialModelDao financialModelDao;

    public CreditCheckService(FinancialModelDao financialModelDao) {
        this.financialModelDao = financialModelDao;
    }


    @Override
    public void checkCredit(CreditCheckRequest request, StreamObserver<CreditCheckResponse> responseObserver) {
        FinancialModel currentModel = new FinancialModel(request);
        logger.info("Checking credit");

        Decision decision = FinancialCalculationsUtils.makeDecision(currentModel, financialModelDao.findAllByCIF(request.getCif()));

        responseObserver.onNext(CreditCheckResponse.newBuilder()
                .setReferenceId(request.getReferenceId())
                .setDecision(decision).build()
        );

        currentModel.setDecision(decision);
        financialModelDao.save(currentModel);

        responseObserver.onCompleted();
    }

    @Override
    public void getCreditCheckHistory(CreditCheckHistoryRequest request, StreamObserver<CreditCheckHistoryResponse> responseObserver) {
        List<FinancialModel> models = financialModelDao.findAllByCIF(request.getCif());
        logger.info("Getting credit check history");

        responseObserver.onNext(CreditCheckHistoryResponse.newBuilder()
                .addAllCreditCheckResponses(models.stream().map(model -> CreditCheckResponse
                        .newBuilder()
                        .setReferenceId(model.getReferenceId())
                        .setDecision(model.getDecision())
                        .build()).toList())
                .build()
        );

        responseObserver.onCompleted();
    }
}
