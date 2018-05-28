package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BookServiceTest {


    BookService bookService;
    @Mock
    BookRepository bookRepository;

    @Before
    public void setup() {
        bookService = new BookServiceImpl(bookRepository);
        List<Book> books = new ArrayList<Book>();
        Book book = new Book("exampleTitle","55555","exampleGenre","exampleAuthor",10,20);
       // Book book=new BookBuilder().setTitle("asvd").setAuthor("svss").setGenre("SF").setPrice(30.2).setQuantity(0).build();
        books.add(book);
        Mockito.when(bookRepository.findAllByAuthor("Author")).thenReturn(books);
        Mockito.when(bookRepository.findAllByGenreContaining("Genre")).thenReturn(books);
        Mockito.when(bookRepository.getByTitle("Title")).thenReturn(book);
    }

    @Test
    public void findByAuthor()
    {
        List<Book> books=bookService.getBooksByAuthor("Author");
        Assert.assertTrue(books.size()==1);
    }
    @Test
    public void findByGenre()
    {
        List<Book> books=bookService.getBooksByGenreContaining("Genre");
        Assert.assertTrue(books.size()==1);
    }

    @Test
    public void findByTitle()
    {
        Book book = bookService.getBookByTitle("Title");
        Assert.assertTrue(book.getTitle().equals("exampleTitle"));
    }


}
