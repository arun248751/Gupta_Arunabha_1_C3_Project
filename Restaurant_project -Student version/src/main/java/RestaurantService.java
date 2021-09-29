import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();



    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        Optional<Restaurant> searchedRestaurantOpt = restaurants.stream().filter(restaurant -> restaurant.getName().equals(restaurantName)).findAny();
        return searchedRestaurantOpt.orElseThrow(() -> new restaurantNotFoundException("No Restaurant available with the name: " + restaurantName + "."));
    }
    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
