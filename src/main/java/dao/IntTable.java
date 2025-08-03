package dao;

import animal.Animal;

import java.util.List;

public interface IntTable {

        List<Animal> findAll();
        List<Animal> findByType(String searchType);
    }

