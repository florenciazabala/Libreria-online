package com.egg.library.util.pagination;


import com.egg.library.util.pagination.ElementsPage;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class RenderPages <T>{

    private String url;
    private Page<T> page;
    private int totalPages;
    private int elementsForPage;
    private int currentPage;
    private List<ElementsPage> pages;

    public RenderPages(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.pages = new ArrayList<>();

        totalPages = page.getTotalPages();
        elementsForPage = page.getSize();
        currentPage = page.getNumber() +1;

        int from,to;
        from =1;
        to = totalPages;

        for (int i=from; i<to;i++){
            pages.add(new ElementsPage(from+i,currentPage == from+i));
        }
    }
}
