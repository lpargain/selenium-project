package com.qa.testcases;

import com.pageactions.FilterSortingActions;
import com.pages.HomePage;
import com.qa.baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class BasicTests extends BaseTest {
    HomePage homePage;

    /**
     * This test will verify sort with impact score and filter by low complexity
     */
    @Test
    public void testVerifyComplexityFilterAndImpactScoreSorting() {
        homePage = new HomePage(driver);
        List<Double> beforeScoreList = homePage.getImpactScoreList();
        List<String> beforeComplexity = homePage.getComplexityList();
        Map<Double, String> map = homePage.getScoreComplexity(beforeScoreList, beforeComplexity);

        ArrayList<Double> sortedKeys = new ArrayList<>(map.keySet());
        Map<Double, String> map2 = new HashMap<>();
        Collections.sort(sortedKeys);
        for (Double x : sortedKeys) {
            map2.put(x, map.get(x));
        }
        map2.values().removeIf("high"::equals);
        map2.values().removeIf("medium"::equals);

        homePage.filterSortData("low", FilterSortingActions.SORT.AV_IMPACT_SCORE);
        List<Double> afterScoreList = homePage.getImpactScoreList();
        List<String> afterComplexity = homePage.getComplexityList();
        Map<Double, String> map3 = homePage.getScoreComplexity(afterScoreList, afterComplexity);
        Assert.assertEquals(map3, map2);
    }

    /**
     * This test checks integer value with filter and sorting with Complexity: It should be a bug
     */
    @Test
    public void testVerifyScoreFilterAndImpactScoreSorting() {
        homePage = new HomePage(driver);
        homePage.filterSortData("8.12", FilterSortingActions.SORT.COMPLEXITY);
        List<Double> list = homePage.getImpactScoreList();
        Assert.assertTrue(list.isEmpty());
    }

    /**
     * This test will verify sort with name and filter by complexity
     */
    @Test
    public void testVerifyNameFilterAndImpactScoreSorting() {
        homePage = new HomePage(driver);
        String search = "XSS";
        List<String> beforeNameList = homePage.getNameList();
        List<String> beforeComplexity = homePage.getComplexityList();
        //Create a map consist of Name and Complexity
        Map<String, String> map = homePage.getNameComplexity1(beforeNameList, beforeComplexity);
        ArrayList<String> sortedKeys = new ArrayList<>(map.keySet());
        Map<String, String> map2 = new HashMap<>();
        Collections.sort(sortedKeys);
        for (String x : sortedKeys) {
            map2.put(x, map.get(x));
        }
        //Create one more map with only XSS name
        Map<String, String> map3 = new HashMap<>();
        map3.put(search, map2.get(search));

        homePage.filterSortData(search, FilterSortingActions.SORT.COMPLEXITY);
        List<String> list1 = homePage.getNameList();
        List<String> list2 = homePage.getComplexityList();
        Map<String, String> map4 = homePage.getNameComplexity1(list1, list2);

        Assert.assertEquals(map4, map3);

    }
}


