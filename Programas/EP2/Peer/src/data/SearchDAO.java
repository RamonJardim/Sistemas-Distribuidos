package data;

import models.SearchInfo;

import java.util.ArrayList;
import java.util.List;

public class SearchDAO {

    private static SearchDAO searchDAO;
    private static int TTL;
    private List<SearchInfo> seenSearches = new ArrayList<SearchInfo>();

    private SearchDAO(){

    }

    public static SearchDAO getSearchDAO(){
        if(searchDAO == null){
            return new SearchDAO();
        } else {
            return searchDAO;
        }
    }

    public static void setTTL(int TTL) {
        SearchDAO.TTL = TTL;
    }

    public static int getTTL() {
        return TTL;
    }

    public void addToSeenSearches(SearchInfo s) {
        seenSearches.add(s);
    }

    public boolean checkIfSeen(SearchInfo s) {
        return seenSearches.contains(s);
    }

}
