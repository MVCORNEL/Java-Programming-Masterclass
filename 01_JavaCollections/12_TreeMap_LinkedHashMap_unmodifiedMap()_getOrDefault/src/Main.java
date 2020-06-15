public class Main {
    private static StockList stockList=new StockList();
    public static void main(String[] args) {
        //SOMETHING IS WRONG WITH THIS EXAMPLE WRONG WITH THIS CLASS, doesn't return deduce the quantity

        //STOCK LIST
        StockItem tempItem = new StockItem("bread", 0.86, 100);
        stockList.addStock(tempItem);
        tempItem = new StockItem("cake", 1.10, 7);
        stockList.addStock(tempItem);
        tempItem = new StockItem("car", 12.5, 2);
        stockList.addStock(tempItem);
        tempItem = new StockItem("chair", 62.00, 10);
        stockList.addStock(tempItem);
        tempItem = new StockItem("cup", 3, 20);
        stockList.addStock(tempItem);
        tempItem = new StockItem("cup", 2.5, 20);
        stockList.addStock(tempItem);
        tempItem = new StockItem("juice", 2.50, 36);
        stockList.addStock(tempItem);
        tempItem = new StockItem("phone", 96.6, 35);
        stockList.addStock(tempItem);
        tempItem = new StockItem("towel", 2.4, 80);
        stockList.addStock(tempItem);
        tempItem = new StockItem("vase", 8.76, 40);
        stockList.addStock(tempItem);
        System.out.println(stockList);
        //MY BASKET
        Basket bobBasket=new Basket("Bobo");

        sellItem(bobBasket,"car",1);
        System.out.println(bobBasket);

        sellItem(bobBasket,"car",1);
        System.out.println(bobBasket);

        sellItem(bobBasket,"car",1);
        System.out.println(bobBasket);
        sellItem(bobBasket,"car",1);
        System.out.println(bobBasket);
        sellItem(bobBasket,"cake",1);
        System.out.println(bobBasket);
        sellItem(bobBasket,"coconut",1);
        System.out.println(bobBasket);
        System.out.println(stockList);

    }
    //SELL METHOD
    public static int sellItem(Basket basket,String item,int quantity){
        //retrieve the item from the stock
        StockItem stockItem=stockList.get(item);
        if(stockItem==null){
            System.out.println("We don't sell" + item);
            return 0;
        }
        if(stockList.sellStock(item,quantity) !=0){
            basket.addToBasket(stockItem,quantity);
            return quantity;
        }
        return 0;
    }

}
