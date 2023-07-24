package pl.sda.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Getter
public class PaginatedList<T> {

    private static final int DEFAULT_PAGE_SIZE = 20;

    private final List<T> list;
    private List<List<T>> listOfPages;
    private final int pageSize = DEFAULT_PAGE_SIZE;
    private int currentPage = 0;

    public PaginatedList(List<T> list){
        this.list = list;
        createPages();
    }

    private void createPages(){
        if(list.isEmpty()){
            return;
        }
        int numOfPages = (int)(Math.ceil(list.size())/(double)pageSize);
        listOfPages = new ArrayList<>(numOfPages);
        for (int i = 0; i < numOfPages;){
            int from = i * pageSize;
            int to = Math.min(++i * pageSize, list.size());
            listOfPages.add(list.subList(from,to));
        }
    }
    public List<T> getPage(int pageNumber){
        if(listOfPages == null){
            return Collections.emptyList();
        }
        if(pageNumber <0  ){
            pageNumber = 0;
        }

        currentPage = pageNumber;
        return  listOfPages.get(pageNumber);
    }

    private void createPages(int newPageSize){
        if(list.isEmpty()){
            return;
        }
        int numOfPages = (int)(Math.ceil(list.size())/(double)newPageSize);
        listOfPages = new ArrayList<>(numOfPages);
        for (int i = 0; i < numOfPages;){
            int from = i * pageSize;
            int to = Math.min(++i * newPageSize, list.size());
            listOfPages.add(list.subList(from,to));
        }
    }

    public int getTotalPages(){
        return listOfPages.size();
    }


}
