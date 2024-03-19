import java.util.ArrayList;

public class YearReport {
    private int year;
    private final ArrayList<Integer> month = new ArrayList<>();
    private final ArrayList<Double> amount = new ArrayList<>();
    private final ArrayList<Boolean> isExpense = new ArrayList<>();
    private boolean isRead = false;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Integer> getMonth() {
        return month;
    }

    public void addMonth(Integer month) {
        this.month.add(month);
    }

    public ArrayList<Double> getAmount() {
        return amount;
    }

    public void addAmount(Double amount) {
        this.amount.add(amount);
    }

    public ArrayList<Boolean> getIsExpense() {
        return isExpense;
    }

    public void addIsExpense(Boolean isExpense) {
        this.isExpense.add(isExpense);
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
