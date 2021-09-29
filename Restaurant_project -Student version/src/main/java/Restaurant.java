
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private final String name;
    private final String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private final List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        return getCurrentTime().isAfter(this.openingTime) && getCurrentTime().isBefore(this.closingTime);
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public List<Item> getMenu() {
        return this.menu;
    }

    private Item findItemByName(String itemName) {
        for (Item item : menu) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n"
                + "Location:" + location + "\n"
                + "Opening time:" + openingTime + "\n"
                + "Closing time:" + closingTime + "\n"
                + "Menu:" + "\n" + getMenu());

    }

    public String getName() {
        return name;
    }


    private boolean selectItemFromMenu(String menuItem) {
        boolean isMenuItemAvailable = false;
        List<Item> menu = this.getMenu();
        for (Item item : menu) {
            if (item.getName().equals(menuItem)) {
                isMenuItemAvailable = true;
                item.setSelected(true);
            }
        }
        return isMenuItemAvailable;
    }


    public double getOrderValue(List<String> selectedMenuItems) throws itemNotFoundException {
        for (String menuItem : selectedMenuItems) {
            boolean isAvailable = this.selectItemFromMenu(menuItem);
            if (!isAvailable)
                throw new itemNotFoundException("Selected Item is not available on menu");
        }

        List<Item> menuItems = this.getMenu();
        double totalOrderValue = 0.0;
        for (Item item : menuItems) {
            if (item.isSelected()) {
                totalOrderValue += item.getPrice();
            }
        }
        return totalOrderValue;
    }
}

