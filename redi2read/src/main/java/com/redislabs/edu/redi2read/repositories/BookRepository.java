package com.redislabs.edu.redi2read.repositories;

import com.redislabs.edu.redi2read.models.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, String> {
}
