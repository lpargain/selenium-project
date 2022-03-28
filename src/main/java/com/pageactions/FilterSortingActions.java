package com.pageactions;

import com.pageobjects.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class FilterSortingActions {
    private final WebDriver driver;
    protected Locators locators;

    public FilterSortingActions(WebDriver driver) {
        this.driver = driver;
        locators = new Locators(driver);
    }

    public enum SORT {
        NAME, NUMBER_OF_CASES, AV_IMPACT_SCORE, COMPLEXITY
    }

    public void enterFilterData(String data) {
        driver.findElement(locators.getFilter()).sendKeys(data);
    }

    public void sortData(SORT sort) {
        WebElement sort1 = driver.findElement(locators.getSort());
        Select select = new Select(sort1);
        switch (sort) {
            case NAME:
                select.selectByVisibleText("Name");
                break;
            case NUMBER_OF_CASES:
                select.selectByVisibleText("Number of cases");
                break;
            case AV_IMPACT_SCORE:
                select.selectByVisibleText("Impact score");
                break;
            case COMPLEXITY:
                select.selectByVisibleText("Complexity");
        }
    }

    public List<String> getAllNames() {
        List<WebElement> list1 = driver.findElements(locators.getNames());
        List<String> newList = new ArrayList<>();
        for (WebElement p : list1) {
            newList.add(p.getText());
        }
        return newList;
    }

    public List<Double> getAllImpactScores() {
        List<WebElement> list1 = driver.findElements(locators.getImpactScores());
        List<Double> newList = new ArrayList<>();
        for (WebElement p : list1) {
            newList.add(Double.valueOf(p.getText()));
        }
        return newList;
    }

    public List<String> getAllComplexities() {
        List<WebElement> list1 = driver.findElements(locators.getComplexities());
        List<String> strings = new ArrayList<>();
        for (WebElement e : list1) {
            strings.add(e.getText());
        }
        return strings;
    }
}
