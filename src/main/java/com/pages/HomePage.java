package com.pages;

import com.pageactions.FilterSortingActions;
import com.pageobjects.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HomePage {
    FilterSortingActions filterSortingActions;
    Locators locators;

    public HomePage(WebDriver driver) {
        filterSortingActions = new FilterSortingActions(driver);
        locators = new Locators(driver);
    }

    public void filterSortData(String filter, FilterSortingActions.SORT sort) {
        filterSortingActions.enterFilterData(filter);
        filterSortingActions.sortData(sort);
    }

    public List<Double> getImpactScoreList() {
        List<Double> scoreList = filterSortingActions.getAllImpactScores();
        return scoreList;
    }


    public List<String> getNameList() {
        List<String> nameList = filterSortingActions.getAllNames();
        return nameList;
    }

    public List<String> getComplexityList() {
        List<String> complexity = filterSortingActions.getAllComplexities();
        return complexity;
    }

    public Map<Double, String> getScoreComplexity(List<Double> list1, List<String> list2) {
        Map<Double, String> map2 = IntStream.range(0, Math.min(list1.size(), list2.size())).boxed().collect(Collectors.toMap(list1::get, list2::get));
        return map2;

    }

    public Map<String, String> getNameComplexity1(List<String> list1, List<String> list2) {
        Map<String, String> map2 = IntStream.range(0, Math.min(list1.size(), list2.size())).boxed().collect(Collectors.toMap(list1::get, list2::get));
        return map2;

    }
}
