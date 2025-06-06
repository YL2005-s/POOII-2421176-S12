import entities.Transaction;
import entities.strategy.TransactionStrategy;
import entities.strategy.impl.TransferenceStrategy;
import models.TransactionProcessor;
import models.account.AccountService;
import models.account.impl.AccountServiceImpl;
import models.bill.BillService;
import models.bill.impl.BillServiceImpl;
import models.repository.impl.TransactionRepository;

public class Main {
    public static void main(String[] args) {
        AccountService cuentaService = new AccountServiceImpl();
        BillService facturaService = new BillServiceImpl();
        TransactionRepository repo = new TransactionRepository();

        TransactionProcessor processor = new TransactionProcessor(repo);

        Transaction t = new Transaction();
        t.setTipo(Transaction.Tipo.TRANSFERENCIA);
        t.setMonto(1000);
        t.setCuentaOrigen("123");
        t.setCuentaDestino("456");

        TransactionStrategy strategy = new TransferenceStrategy(cuentaService);
        processor.procesar(t, strategy);
    }
}