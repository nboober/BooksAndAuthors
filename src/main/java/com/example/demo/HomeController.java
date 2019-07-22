package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping("/")
    public String index(Model model){
        //First Let's create an author
        Author author = new Author();
        author.setName("J.K. Rowling");
        author.setAge(45);

        //Now let's create a Book
        Book book = new Book();
        book.setTitle("Harry Potter and the Chamber of Secrets");
        book.setPages(500);

        //Add the book to an empty list
        Set<Book> books = new HashSet<Book>();
        books.add(book);

        //Add the list of books to the actor's book list
        author.setBooks(books);

        //Save the actor to the database
        authorRepository.save(author);

        //Grab all the authors from the database and send them to the template
        model.addAttribute("authors", authorRepository.findAll());
        return "index";
    }

}
