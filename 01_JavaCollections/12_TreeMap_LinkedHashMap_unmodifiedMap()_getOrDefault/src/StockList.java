import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    StockList() {
        //To maintain the order in which the items were added to the Map
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) {
            //GETS THE ITEM IF IT ALREADY EXISTS IN THE MAP,if not get the item from the method parameter
            StockItem inStock = list.getOrDefault(item.getName(), item);
            //if there are already stock oon this item, adjust the quantity
            if (inStock != item) {
                item.adjustStock(inStock.quantityInStock());
            }
            list.put(item.getName(), item);
            return item.quantityInStock();
        }
        return 0;
    }

    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);

        if ((inStock != null) && (inStock.quantityInStock() >= quantity) && (quantity > 0)) {
            //Minus because we are deducting the quantity now
            inStock.adjustStock(-quantity);
            return quantity;
        }
        return 0;
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, StockItem> Items() {
        //COLLECTIONS CLASS PROVIDES A WRAPPER FOR LIST,MAPS AND SETS to PROVIDE AN UNMODIFIABLE COLLECTION
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;

        for (Map.Entry<String, StockItem> item : list.entrySet()) {

            StockItem stockItem = item.getValue();
            double itemValue = stockItem.quantityInStock() * stockItem.getPrice();

            s = s + stockItem + ". There are " + stockItem.quantityInStock() + " in stock.Value of items : ";
            s = s + String.format("%10.2f",itemValue) + "\n";
            totalCost += itemValue;
        }
        return s + "Total stock value : " + totalCost;
    }
}
