public class Item {
    private String name;
    private int price;
    private boolean isSelected;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    @Override
    public String toString(){
        return  name + ":"
                + price
                + "\n"
                ;
    }
}
