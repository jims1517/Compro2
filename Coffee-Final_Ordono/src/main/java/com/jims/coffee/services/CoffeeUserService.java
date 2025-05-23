package com.jims.coffee.services;

import com.jims.coffee.models.CoffeeUser;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CoffeeUserService {
    private List<CoffeeUser> coffeeUser;

    @PostConstruct
    public void init() throws IOException {
        coffeeUser = new ArrayList<>();
        File file = new File("data/coffee_users.csv");

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            CoffeeUser user = new CoffeeUser();
            user.setUsername(parts[0]);
            user.setPassword(parts[1]);
            coffeeUser.add(user);
        }
    }

    public CoffeeUser findByUsername(String username) {
        return coffeeUser.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void save(CoffeeUser user) {

    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        for (CoffeeUser user : coffeeUser) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(oldPassword)) {
                    user.setPassword(newPassword);
                    saveAllUsers();
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private void saveAllUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/coffee_users.csv"))) {
            for (CoffeeUser user : coffeeUser) {
                writer.write(user.getUsername() + "," + user.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
