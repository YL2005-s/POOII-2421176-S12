import entities.Account;
import entities.Tipo;
import entities.Transaction;
import entities.strategy.TransactionStrategy;
import entities.strategy.impl.DepositStrategy;
import entities.strategy.impl.PaymentServicesStrategy;
import entities.strategy.impl.TransferenceStrategy;
import models.TransactionProcessor;
import models.repository.impl.TransactionRepository;
import services.AccountService;
import services.BillService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        BillService billService = new BillService();
        TransactionRepository repository = new TransactionRepository();

        TransactionProcessor processor = new TransactionProcessor(repository);
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SALDOS INICIALES ===");
        mostrarSaldos(accountService);

        while (true) {
            System.out.println("\n=== MENÚ DE TRANSACCIONES ===");
            System.out.println("1. Transferencia");
            System.out.println("2. Depósito");
            System.out.println("3. Pago de Servicio");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            if (opcion == 4) break;

            Transaction transaction = new Transaction();

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Cuenta origen: ");
                        transaction.setCuentaOrigen(scanner.nextLine());
                        System.out.print("Cuenta destino: ");
                        transaction.setCuentaDestino(scanner.nextLine());
                        System.out.print("Monto: ");
                        transaction.setMonto(Double.parseDouble(scanner.nextLine()));
                        transaction.setTipo(Tipo.TRANSFERENCIA);
                        processor.setTransactionStrategy(new TransferenceStrategy(accountService));
                        break;

                    case 2:
                        System.out.print("Cuenta destino: ");
                        transaction.setCuentaDestino(scanner.nextLine());
                        System.out.print("Monto: ");
                        transaction.setMonto(Double.parseDouble(scanner.nextLine()));
                        transaction.setTipo(Tipo.DEPOSITO);
                        processor.setTransactionStrategy(new DepositStrategy(accountService));
                        break;

                    case 3:
                        System.out.print("Referencia de la factura: ");
                        transaction.setReferencia(scanner.nextLine());
                        transaction.setTipo(Tipo.PAGO_SERVICIO);
                        processor.setTransactionStrategy(new PaymentServicesStrategy(billService));
                        break;

                    default:
                        System.out.println("Opción inválida.");
                        continue;
                }

                processor.procesar(transaction);
                System.out.println("=== " + transaction.getTipo() +" ===");
                System.out.println("Transferencia exitosa");
                System.out.println("Detalles: "  + transaction);
                mostrarSaldos(accountService);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Gracias por usar el sistema.");
    }

    private static void mostrarSaldos(AccountService accountService) {
        System.out.println("Saldos actualizados:");
        for (String cuentaId : accountService.obtenerCuentas()) {
            Account cuenta = accountService.obtenerCuenta(cuentaId);
            System.out.printf("- Cuenta CU%s: $%.1f%n", cuentaId, cuenta.getSaldo());
        }
    }

}