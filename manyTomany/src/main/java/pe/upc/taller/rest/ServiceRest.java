package pe.upc.taller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.upc.taller.entidades.Author;
import pe.upc.taller.entidades.Book;
import pe.upc.taller.repositorio.AuthorRepository;
import pe.upc.taller.repositorio.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceRest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/demo")
    public String prueba(){
        return "Hola";
    }

    @PostMapping("/books")
    public List<Book> saveBook(@RequestBody List<Book> bookList) {
        System.out.println("Save!!!");
        List<Book> bookResponse = bookRepository.saveAll(bookList);
        return bookResponse;
    }
    @PostMapping("/book/addBook/{idBook}")
    public Book add(@PathVariable(value = "idBook") Long idBook, @RequestBody Author author)
    {
    	Book b = bookRepository.findById(idBook).get();    	
    	if(b!=null) {
    	  b.addAuthor(author);
    	  bookRepository.save(b);
    	  return b;
    	}
    	return b;    	
    }

    @GetMapping("/books")
    public  List<Book> demo(){
        List<Book> list = (List<Book>) bookRepository.findAll();
        return list;
    }

}
