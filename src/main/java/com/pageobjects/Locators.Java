package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Locators {
    protected WebDriver driver;

    public Locators(WebDriver driver) {
        this.driver = driver;
    }

    public By getFilter() {
        return By.id("filter-input");
    }

    public By getSort() {
        return By.id("sort-select");
    }

    public By getNames() {
        return By.xpath("//div[@class='table-data data-name']");
    }

    public By getComplexities() {
        return By.xpath("//div[@class='table-data data-complexity']");
    }

    public By getImpactScores() {
        return By.xpath("//div[@class='table-data data-averageImpact']");
    }
}
