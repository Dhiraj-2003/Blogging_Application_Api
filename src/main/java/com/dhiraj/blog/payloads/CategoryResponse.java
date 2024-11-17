package com.dhiraj.blog.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CategoryResponse {

    private List<CategoryDto> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int totalElements;
    private boolean lastPage;
}
