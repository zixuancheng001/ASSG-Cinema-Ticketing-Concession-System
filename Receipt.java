import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {

    private Payment payment;
    private LocalDateTime transactionTime;

    public Receipt(Payment payment) {
        this.payment = payment;
        this.transactionTime = LocalDateTime.now();
    }

    // Generate receipt text
    private String generateReceiptText() {

        Order order = payment.getOrder();

        StringBuilder sb = new StringBuilder();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        sb.append("\n==========================================================================\n");
        sb.append("                          CINEMA TICKET SYSTEM                          \n");
        sb.append("==========================================================================\n");

        sb.append(String.format("%-20s : %s\n", "Date", transactionTime.format(format)));
        sb.append(String.format("%-20s : %s\n", "Order ID", order.getOrderID()));
        sb.append(String.format("%-20s : %s\n", "Payment Method", payment.getPaymentMethod()));
        sb.append(String.format("%-20s : %s\n", "Reference No", payment.getReferenceNo()));

        sb.append("\n--------------------------------------------------------------------------\n");
        sb.append(String.format("Total Amount : RM %.2f\n", order.getTotalAmount()));
        sb.append("--------------------------------------------------------------------------\n");

        sb.append("\nThank you for choosing us!\n");
        sb.append("Enjoy your movie!\n");
        sb.append("==========================================================================\n");

        return sb.toString();
    }

    // Print receipt
    public void printReceipt() {
        System.out.println(generateReceiptText());
    }

    // Save to file
    public void saveReceipt() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("receipts.txt", true))) {
            bw.write(generateReceiptText());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving receipt!");
        }
    }
}