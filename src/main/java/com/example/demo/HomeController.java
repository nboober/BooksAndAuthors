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
        author.setBio("A lady lives in a house on an island and thinks up crazy stories.");

        //Create a Second Author
        Author author2 = new Author();
        author2.setName("George R.R. Martin");
        author2.setAge(55);
        author2.setBio("Old guy writes amazing stories and never finishes them.");

        //Now let's create a Book
        Book book = new Book();
        book.setTitle("Harry Potter and the Chamber of Secrets");
        book.setPages(500);
        book.setDescription("Your a 'izard 'arry!");

        //Create a second book
        Book book2 = new Book();
        book2.setTitle("Game of Thrones: A Song of Ice and Fire");
        book2.setPages(1000);
        book2.setDescription("Alot of people fighting for the throne.");

        //Add the book to an empty list
        Set<Book> books = new HashSet<Book>();
        books.add(book);
        books.add(book2);

        //Add the list of books to the actor's book list
        author.setBooks(books);
        author2.setBooks(books);

        //Save the actor to the database
        authorRepository.save(author);
        authorRepository.save(author2);

        //Save the books to the database
        bookRepository.save(book);
        bookRepository.save(book2);

        //Grab all the authors from the database and send them to the template
        model.addAttribute("authors", authorRepository.findAll());
        return "index";
    }

}
