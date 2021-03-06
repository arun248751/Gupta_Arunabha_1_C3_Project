import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    private Restaurant createRestaurant(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        return restaurant;
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Restaurant restaurant = createRestaurant();
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        Mockito.doReturn(LocalTime.parse("16:00")).when(spiedRestaurant).getCurrentTime();
        assertTrue(spiedRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant restaurant = createRestaurant();
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        Mockito.doReturn(LocalTime.parse("23:00")).when(spiedRestaurant).getCurrentTime();
        assertFalse(spiedRestaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        Restaurant restaurant = createRestaurant();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        Restaurant restaurant = createRestaurant();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        Restaurant restaurant = createRestaurant();

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>Order Value<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void total_price_of_the_items_selected_from_the_cart_should_be_388() throws itemNotFoundException {
        Restaurant restaurant = createRestaurant();
        List<Item> items = restaurant.getMenu();
        List<String> menuItems = items.stream().map(Item::getName).collect(Collectors.toList());
        assertEquals(388, restaurant.getOrderValue(menuItems));

    }

    @Test
    public void total_price_of_the_items_when_unavailable_item_selected_from_the_cart_should_throw_exception(){
        Restaurant restaurant = createRestaurant();
        List<String> menuItems = Arrays.asList("Sweet corn soup", "Popcorn");
        assertThrows(itemNotFoundException.class, () -> restaurant.getOrderValue(menuItems));

    }

    //<<<<<<<<<<<<<<<<<<<<<<<Order Value>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}