package com.restaurantservice.restaurantservice.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RestaurentServiceTest {

//    @Mock
//    private IRestaurantRepository iRestaurantRepository;
//
//    @InjectMocks
//    private RestaurantServiceImpl restaurantService;
//
//    private Restaurant testRestaurant;
//    private Dishes testDish;
//
//    @BeforeEach
//    void setUp() {
//        testRestaurant = new Restaurant("1", "Motel Way", "Chennai", new ArrayList<>());
//        testDish = new Dishes("1", "Coffee", "Category", 15.99f, 5);
//    }
//
//    @Test
//    void testAddRestaurant_Success() {
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.empty());
//        when(iRestaurantRepository.save(any(Restaurant.class))).thenReturn(testRestaurant);
//
//        assertDoesNotThrow(() -> restaurantService.addRestaurant(testRestaurant));
//    }
//
//    @Test
//    void testAddRestaurant_AlreadyExists() {
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.of(testRestaurant));
//
//        assertThrows(RestaurantAlreadyExistException.class, () -> restaurantService.addRestaurant(testRestaurant));
//    }
//
//    @Test
//    void testSaveDishesToRestaurant_Success() {
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.of(testRestaurant));
//        when(iRestaurantRepository.save(any(Restaurant.class))).thenReturn(testRestaurant);
//
//        assertDoesNotThrow(() -> restaurantService.saveDishesToRestaurant(testDish, "1"));
//    }
//
//    @Test
//    void testSaveDishesToRestaurant_RestaurantNotFound() {
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.empty());
//
//        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.saveDishesToRestaurant(testDish, "1"));
//    }
//
//    @Test
//    void testSaveDishesToRestaurant_DishAlreadyExists() {
//        testRestaurant.getDishes().add(testDish);
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.of(testRestaurant));
//
//        assertThrows(DishAlreadyExistException.class, () -> restaurantService.saveDishesToRestaurant(testDish, "1"));
//    }
//
//    @Test
//    void testDeleteRestaurant_Success() {
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.of(testRestaurant));
//
//        assertDoesNotThrow(() -> restaurantService.deleteRestaurant("1"));
//    }
//
//    @Test
//    void testDeleteRestaurant_RestaurantNotFound() {
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.empty());
//
//        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.deleteRestaurant("1"));
//    }
//
//    @Test
//    void testDeleteDishFromRestaurant_Success() {
//        testRestaurant.getDishes().add(testDish);
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.of(testRestaurant));
//
//        assertDoesNotThrow(() -> restaurantService.deleteDishFromRestaurant("1", "1"));
//    }
//
//    @Test
//    void testDeleteDishFromRestaurant_RestaurantNotFound() {
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.empty());
//
//        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.deleteDishFromRestaurant("1", "1"));
//    }
//
//    @Test
//    void testDeleteDishFromRestaurant_DishNotFound() {
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.of(testRestaurant));
//
//        assertThrows(DishNotFoundException.class, () -> restaurantService.deleteDishFromRestaurant("1", "2"));
//    }
//
//    @Test
//    void testUpdateRestaurant_Success() {
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.of(testRestaurant));
//
//        assertDoesNotThrow(() -> restaurantService.updateRestaurant(testRestaurant));
//    }
//
//    @Test
//    void testUpdateRestaurant_RestaurantNotFound() {
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.empty());
//
//        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.updateRestaurant(testRestaurant));
//    }
//
//    @Test
//    void testUpdateDish_Success() {
//        testRestaurant.getDishes().add(testDish);
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.of(testRestaurant));
//
//        assertDoesNotThrow(() -> restaurantService.updateDish(testDish, "1"));
//    }
//
//    @Test
//    void testUpdateDish_RestaurantNotFound() {
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.empty());
//
//        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.updateDish(testDish, "1"));
//    }
//
//    @Test
//    void testUpdateDish_DishNotFound() {
//        when(iRestaurantRepository.findById("1")).thenReturn(Optional.of(testRestaurant));
//
//        assertThrows(DishNotFoundException.class, () -> restaurantService.updateDish(testDish, "1"));
//    }
//
//
//    @Test
//    void testGetAllRestaurants() throws RestaurantNotFoundException {
//        List<Restaurant> allRestaurants = Arrays.asList(testRestaurant, new Restaurant("2", "Second Restaurant", "Second Address", new ArrayList<>()));
//
//        when(iRestaurantRepository.findAll()).thenReturn(allRestaurants);
//
//        assertEquals(allRestaurants, restaurantService.getAllRestaurants());
//    }
//
//
//
//
//    @Test
//    void testGetRestaurantByName_Success() throws RestaurantNotFoundException {
//        when(iRestaurantRepository.findByName("Test Restaurant")).thenReturn(Optional.of(testRestaurant));
//
//        assertEquals(testRestaurant, restaurantService.getRestaurantByName("Test Restaurant"));
//    }
//
//    @Test
//    void testGetRestaurantByName_RestaurantNotFound() {
//        when(iRestaurantRepository.findByName("Test Restaurant")).thenReturn(Optional.empty());
//
//        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.getRestaurantByName("Test Restaurant"));
//    }


}


