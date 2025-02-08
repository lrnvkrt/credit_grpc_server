import data.config.DataSourceConfig;
import data.config.JdbiConfig;
import data.dao.FinancialModelDao;
import data.dao.impl.FinancialModelDaoImpl;
import data.init.DatabaseInitializer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.jdbi.v3.core.Jdbi;
import service.CreditCheckService;

import javax.sql.DataSource;
import java.io.IOException;


public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        DataSource dataSource = DataSourceConfig.createDataSource();
        Jdbi jdbi = JdbiConfig.createJdbi(dataSource);

        DatabaseInitializer initializer = new DatabaseInitializer(jdbi);
        initializer.initialize();

        FinancialModelDao financialModelDao = new FinancialModelDaoImpl(jdbi);

        Server server = ServerBuilder.forPort(50051)
                .addService(new CreditCheckService(financialModelDao))
                .build();

        server.start();
        server.awaitTermination();
    }
}
