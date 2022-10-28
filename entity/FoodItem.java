package entity;

public class FoodItem {
    
    private final String foodName;

    private double price;

    private int itemCount;

    public FoodItem(String foodName, double price, int count) {
        this.foodName = foodName;
        this.itemCount = count;
        this.price = price;
    }

    public String getFoodname() {
        return this.foodName;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public void setItemCount(int newCount) {
        this.itemCount = newCount;
    }
}
