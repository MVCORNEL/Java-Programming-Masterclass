import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private final Map<StockItem,Integer> basketList;

    public Basket(String name) {
        this.name = name;
        //order the entries base on the Comparable method
        this.basketList = new TreeMap<>();
    }

    public int addToBasket(StockItem item,int quantity){
        if((item!=null)&&(quantity >0)){
            int inBasket=basketList.getOrDefault(item,0);
            basketList.put(item,inBasket+quantity);
            return inBasket;
        }
        return 0;
    }

    public Map<StockItem,Integer> Items(){
        return Collections.unmodifiableMap(basketList);
    }

    @Override
    public String toString() {
        String s= "\nShopping Basket\n" + name + " contains" + basketList.size() + " items\n";
        double totalCost=0.0;
        for(Map.Entry<StockItem,Integer> item : basketList.entrySet()){
            s=s+ item.getKey()+ ". " + item.getValue()+ " purchased\n";
            totalCost+=item.getKey().getPrice()* item.getValue();
        }
        return s+ "Total cost " +totalCost;
    }
}
