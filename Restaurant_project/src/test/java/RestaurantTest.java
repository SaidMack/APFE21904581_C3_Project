import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;


    @BeforeEach
    public void addRestaurantInList() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amma's Biryani", "Bangalore", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {

        LocalTime openingTime = LocalTime.parse("15:30:00");
        LocalTime closingTime = LocalTime.parse("23:30:00");
        restaurant = new Restaurant("Mcdonald's", "China", openingTime, closingTime);
        assertTrue(restaurant.isRestaurantOpen(), "Restaurant is closed");
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {

        LocalTime openingTime = LocalTime.parse("19:20:00");
        LocalTime closingTime = LocalTime.parse("21:30:00");
        restaurant = new Restaurant("Mcdonald's", "China", openingTime, closingTime);
        assertFalse(restaurant.isRestaurantOpen(), "Restaurant is open");


    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void orderTotal_cost_should_be_the_same_as_cost_of_item_selected_from_menu()
    {
        List<Item> selectedItems = new ArrayList<>();
        List<Item> menu = restaurant.getMenu();
        selectedItems.add(menu.get(0));
        assertEquals(119,restaurant.checkOrderTotal(selectedItems));
    }
}

//<<<<<<<<<<<<<<<<<<<<<<TOTAL ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>