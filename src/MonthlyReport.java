import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    private final HashMap<Integer, ArrayList<String>> itemName = new HashMap<>();
    private final HashMap<Integer, ArrayList<Boolean>> isExpense = new HashMap<>();
    private final HashMap<Integer, ArrayList<Integer>> quantity = new HashMap<>();
    private final HashMap<Integer, ArrayList<Double>> sumOfOne = new HashMap<>();
    private boolean isRead = false;

    public HashMap<Integer, ArrayList<String>> getItemName() {
        return itemName;
    }

    public void putItemName(Integer index, ArrayList<String> itemName) {
        this.itemName.put(index, itemName);
    }

    public HashMap<Integer, ArrayList<Boolean>> getIsExpense() {
        return isExpense;
    }

    public void putIsExpense(Integer index, ArrayList<Boolean> isExpense) {
        this.isExpense.put(index, isExpense);
    }

    public HashMap<Integer, ArrayList<Integer>> getQuantity() {
        return quantity;
    }

    public void putQuantity(Integer index, ArrayList<Integer> quantity) {
        this.quantity.put(index, quantity);
    }

    public HashMap<Integer, ArrayList<Double>> getSumOfOne() {
        return sumOfOne;
    }

    public void putSumOfOne(Integer index, ArrayList<Double> sumOfOne) {
        this.sumOfOne.put(index, sumOfOne);
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}