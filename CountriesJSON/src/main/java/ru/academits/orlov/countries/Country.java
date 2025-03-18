package ru.academits.orlov.countries;

import java.util.Map;

public class Country {
    private String name;
    private String[] topLevelDomain;
    private String alpha2Code;
    private String alpha3Code;
    private String[] callingCodes;
    private String capital;
    private String[] altSpellings;
    private String region;
    private String subregion;
    private long population;
    private double[] latlng;
    private String demonym;
    private double area;
    private double gini;
    private String[] timezones;
    private String[] borders;
    private String nativeName;
    private String numericCode;
    private Currency[] currencies;
    private Language[] languages;
    private Map<String, String> translations;
    private String flag;
    private RegionalBlock[] regionalBlocs;
    private String cioc;

    public String getName() {
        return name;
    }

    public String[] getTopLevelDomain() {
        return topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public String[] getCallingCodes() {
        return callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public String[] getAltSpellings() {
        return altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public long getPopulation() {
        return population;
    }

    public double[] getLatlng() {
        return latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public double getArea() {
        return area;
    }

    public double getGini() {
        return gini;
    }

    public String[] getTimezones() {
        return timezones;
    }

    public String[] getBorders() {
        return borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public Currency[] getCurrencies() {
        return currencies;
    }

    public Language[] getLanguages() {
        return languages;
    }

    public Map<String, String> getTranslations() {
        return translations;
    }

    public String getFlag() {
        return flag;
    }

    public RegionalBlock[] getRegionalBlocs() {
        return regionalBlocs;
    }

    public String getCioc() {
        return cioc;
    }

    @Override
    public String toString() {
        return name + " : население " + population;
    }
}
