package ru.academits.orlov.countries_main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.academits.orlov.countries.Country;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Country> countriesList = objectMapper.readValue(new File("CountriesJSON/countries.json"), new TypeReference<>() {});

            long totalPopulation = countriesList.stream()
                    .mapToLong(Country::getPopulation)
                    .sum();

            System.out.println("Суммарная численность по странам: " + totalPopulation);
            System.out.println("Перечень валют:");

            countriesList.stream()
                    .flatMap(country -> country.getCurrencies().stream())
                    .distinct()
                    .forEach(System.out::println);

            List<Country> populationOverMillionCountries = countriesList.stream()
                    .filter(country -> country.getPopulation() >= 1000000)
                    .toList();

            System.out.println();
            System.out.println("Страны, у которых численность населения не менее 1 млн:");

            populationOverMillionCountries.forEach(System.out::println);

            objectMapper.writeValue(new File("CountriesJSON/million-plus-cities.json"), populationOverMillionCountries);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}