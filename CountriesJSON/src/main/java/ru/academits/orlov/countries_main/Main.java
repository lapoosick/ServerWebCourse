package ru.academits.orlov.countries_main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.academits.orlov.countries.Country;
import ru.academits.orlov.countries.Currency;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Country> countriesList = objectMapper.readValue(new File("countries.json"), new TypeReference<>() {});

            long totalPopulation = countriesList
                    .stream()
                    .mapToLong(Country::getPopulation)
                    .sum();

            System.out.println("Суммарная численность по странам: " + totalPopulation);

            List<Currency> currenciesList = countriesList
                    .stream()
                    .map(Country::getCurrencies)
                    .flatMap(List::stream)
                    .distinct()
                    .toList();

            System.out.println("Перечень валют: " + currenciesList);

            Country[] populationOverMillionCountries = countriesList
                    .stream()
                    .filter(country -> country.getPopulation() >= 1000000)
                    .toArray(Country[]::new);

            System.out.println("Страны, у которых численность населения не менее 1 млн: " + Arrays.toString(populationOverMillionCountries));

            objectMapper.writeValue(new File("million-plus-cities.json"), populationOverMillionCountries);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}