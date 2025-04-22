package com.JimsCoffee.JimsCoffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for handling coffee-related operations in the Spring Boot application.
 * This class provides methods for managing a list of coffee items, including
 * adding, updating, deleting, and searching for coffees.
 */
@Controller
public class HomeController {

    private List<Coffee> coffeeList = new ArrayList<>();

    @Autowired
    private CsvDataService csvDataService;

    /**
     * Constructor that initializes the coffee list with sample data.
     * Also saves the initial data to CSV.
     */
    @Autowired
    public HomeController(CsvDataService csvDataService) {
        this.csvDataService = csvDataService;

        // Initialize coffee list with sample data
        coffeeList.add(new Coffee(1, "Espresso", "Arabica", "Small", 3.50, "Dark", "Ethiopia", false, 10, Arrays.asList("Chocolate", "Nutty"), "Espresso"));
        coffeeList.add(new Coffee(2, "Latte", "Arabica", "Medium", 4.50, "Medium", "Brazil", false, 8, Arrays.asList("Creamy", "Sweet"), "Drip"));
        coffeeList.add(new Coffee(3, "Cappuccino", "Robusta", "Large", 5.00, "Medium", "Colombia", false, 12, Arrays.asList("Fruity", "Bold"), "French Press"));
        coffeeList.add(new Coffee(4, "Mocha", "Arabica", "Medium", 4.75, "Dark", "Guatemala", false, 6, Arrays.asList("Chocolate", "Smooth"), "Espresso"));
        coffeeList.add(new Coffee(5, "Americano", "Robusta", "Large", 3.25, "Light", "Kenya", false, 15, Arrays.asList("Citrus", "Balanced"), "Drip"));

        // Save initial data to CSV
        this.csvDataService.saveToCsv(coffeeList);
    }

    /**
     * Handles GET requests to the root ("/") endpoint.
     * Retrieves the list of coffee objects and adds them to the model.
     * Additionally, filters the coffee list based on the search query if provided.
     *
     * @param search The search term entered by the user.
     * @param model  The model to which the filtered list of coffees is added.
     * @return The name of the Thymeleaf template to render ("index").
     */
    @GetMapping("/")
    public String getCoffees(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Coffee> filteredCoffees = coffeeList;

        // Filter the coffee list based on the search term
        if (search != null && !search.isEmpty()) {
            filteredCoffees = coffeeList.stream()
                    .filter(coffee -> coffee.getName().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Add the filtered list and search term to the model
        model.addAttribute("coffees", filteredCoffees);
        model.addAttribute("search", search);
        return "index";
    }

    /**
     * Handles the deletion of a coffee item by its ID.
     * After deletion, the user is redirected to the root ("/") page.
     *
     * @param id The ID of the coffee item to delete.
     * @return A redirect to the root ("/") page after deletion.
     */
    @GetMapping("/delete")
    public String deleteCoffee(@RequestParam int id) {
        coffeeList.removeIf(coffee -> coffee.getId() == id);

        // Automatically save changes to CSV
        csvDataService.saveToCsv(coffeeList);

        return "redirect:/";
    }

    /**
     * Handles GET requests to the "/add" endpoint.
     * Returns the view for adding a new coffee item.
     *
     * @return The name of the Thymeleaf template ("new") for adding a coffee.
     */
    @GetMapping("/add")
    public String addCoffee() {
        return "new";
    }

    /**
     * Handles POST requests to the "/save" endpoint.
     * Adds a new coffee item to the coffee list.
     * After saving, the user is redirected to the root ("/") page.
     *
     * @param name        The name of the coffee.
     * @param type        The type of the coffee (Arabica/Robusta).
     * @param size        The size of the coffee (Small/Medium/Large).
     * @param price       The price of the coffee.
     * @param roastLevel  The roast level of the coffee (Light/Medium/Dark).
     * @param origin      The origin of the coffee.
     * @param isDecaf     Whether the coffee is decaffeinated.
     * @param stock       The available stock of the coffee.
     * @param flavorNotes The flavor notes associated with the coffee.
     * @param brewMethod  The brew method used for the coffee.
     * @return A redirect to the root ("/") page after saving the coffee.
     */
    @PostMapping("/save")
    public String saveCoffee(@RequestParam String name,
                             @RequestParam String type,
                             @RequestParam String size,
                             @RequestParam double price,
                             @RequestParam String roastLevel,
                             @RequestParam String origin,
                             @RequestParam boolean isDecaf,
                             @RequestParam int stock,
                             @RequestParam List<String> flavorNotes,
                             @RequestParam String brewMethod) {

        // Create a new Coffee object and add it to the coffee list
        int newId = coffeeList.get(coffeeList.size() - 1).getId() + 1;
        coffeeList.add(new Coffee(newId, name, type, size, price, roastLevel, origin, isDecaf, stock, flavorNotes, brewMethod));

        // Automatically save changes to CSV
        csvDataService.saveToCsv(coffeeList);

        return "redirect:/";
    }

    /**
     * Handles GET requests to the "/edit" endpoint.
     * Retrieves a coffee object by its ID and adds it to the model for editing.
     * If the coffee is found, it returns the "edit" view; otherwise, it redirects to the root ("/") page.
     *
     * @param id    The ID of the coffee item to edit.
     * @param model The model to which the coffee item is added for editing.
     * @return The name of the Thymeleaf template ("edit") if the coffee item is found, otherwise a redirect to the root ("/") page.
     */
    @GetMapping("/edit")
    public String editCoffee(@RequestParam int id, Model model) {
        for (Coffee coffee : coffeeList) {
            if (coffee.getId() == id) {
                model.addAttribute("coffee", coffee);
                return "edit";
            }
        }
        return "redirect:/";
    }

    /**
     * Handles POST requests to the "/update" endpoint.
     * Updates the details of an existing coffee item with the provided information.
     * After updating, the user is redirected to the root ("/") page.
     *
     * @param id          The ID of the coffee item to update.
     * @param name        The name of the coffee.
     * @param type        The type of the coffee (Arabica/Robusta).
     * @param size        The size of the coffee (Small/Medium/Large).
     * @param price       The price of the coffee.
     * @param roastLevel  The roast level of the coffee (Light/Medium/Dark).
     * @param origin      The origin of the coffee.
     * @param isDecaf     Whether the coffee is decaffeinated.
     * @param stock       The available stock of the coffee.
     * @param flavorNotes The flavor notes associated with the coffee.
     * @param brewMethod  The brew method used for the coffee.
     * @return A redirect to the root ("/") page after updating the coffee.
     */
    @PostMapping("/update")
    public String updateCoffee(@RequestParam int id,
                               @RequestParam String name,
                               @RequestParam String type,
                               @RequestParam String size,
                               @RequestParam double price,
                               @RequestParam String roastLevel,
                               @RequestParam String origin,
                               @RequestParam boolean isDecaf,
                               @RequestParam int stock,
                               @RequestParam List<String> flavorNotes,
                               @RequestParam String brewMethod) {

        // Update the existing coffee item with the provided information
        for (Coffee coffee : coffeeList) {
            if (coffee.getId() == id) {
                coffee.setName(name);
                coffee.setType(type);
                coffee.setSize(size);
                coffee.setPrice(price);
                coffee.setRoastLevel(roastLevel);
                coffee.setOrigin(origin);
                coffee.setDecaf(isDecaf);
                coffee.setStock(stock);
                coffee.setFlavorNotes(flavorNotes);
                coffee.setBrewMethod(brewMethod);
                break;
            }
        }

        // Automatically save changes to CSV
        csvDataService.saveToCsv(coffeeList);

        return "redirect:/";
    }
}