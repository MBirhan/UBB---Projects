import domain.Book;
import domain.Client;
import domain.Purchase;
import domain.validators.BookValidator;
import domain.validators.ClientValidator;
import domain.validators.PurchaseValidator;
import domain.validators.Validator;
import repository.*;
import repository.Paging.PagingRepository;
import service.*;
import ui.Console;

import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String args[]) throws Exception {


        try {
            System.out.println(new File(".").getCanonicalPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        Validator<Book> bookValidator = new BookValidator();
        Validator<Client> clientValidator= new ClientValidator();
        Validator<Purchase> purchaseValidator = new PurchaseValidator();



        //PagingRepository<Long, Book> FileBookRepo = new BookFileRepository(bookValidator, "C:\\Faculty\\MPP\\LibraryApp\\book_library\\src\\main\\resources\\Books");
        PagingRepository<Long, Book> FileBookRepo = new BookFileRepository(bookValidator, "C:\\Users\\Birhan\\Desktop\\AN 2 Sem 2\\MPP\\Mpp proiecte\\Library_app\\src\\main\\resources\\Books");
        BookService bookServiceFile = new BookService(FileBookRepo);


        Validator<Client> clientValidatorFile = new ClientValidator();
        //PagingRepository<Long, Client> FileClientRepo = new ClientFileRepository(clientValidatorFile, "C:\\Faculty\\MPP\\LibraryApp\\book_library\\src\\main\\resources\\Clients");
        PagingRepository<Long, Client> FileClientRepo = new ClientFileRepository(clientValidatorFile, "C:\\Users\\Birhan\\Desktop\\AN 2 Sem 2\\MPP\\Mpp proiecte\\Library_app\\src\\main\\resources\\Clients");
        ClientService clientServiceFile = new ClientService(FileClientRepo);


        PagingRepository<Long, Purchase> purchaseRepository = new InMemoryRepository<>(purchaseValidator);
        PurchaseService purchaseService = new PurchaseService(purchaseRepository);


        //PagingRepository<Long, Book> XMLBookRepo = new XMLRepositoryBook(bookValidator, "C:\\Faculty\\MPP\\LibraryApp\\book_library\\src\\main\\resources\\BookXML");
        PagingRepository<Long, Book> XMLBookRepo = new XMLRepositoryBook(bookValidator, "C:\\Users\\Birhan\\Desktop\\AN 2 Sem 2\\MPP\\Mpp proiecte\\Library_app\\src\\main\\resources\\BookXML");
        XMLBookService XMLBookService = new XMLBookService(XMLBookRepo);


        //PagingRepository<Long, Client> XMLClientRepo = new XMLRepositoryClient(clientValidator, "C:\\Faculty\\MPP\\LibraryApp\\book_library\\src\\main\\resources\\ClientXML");
        PagingRepository<Long, Client> XMLClientRepo = new XMLRepositoryClient(clientValidator, "C:\\Users\\Birhan\\Desktop\\AN 2 Sem 2\\MPP\\Mpp proiecte\\Library_app\\src\\main\\resources\\ClientXML");
        XMLClientService XMLClientService = new XMLClientService(XMLClientRepo);

        PagingRepository<Long, Book> DBBookRepo = new BookDBRepo(bookValidator);
        DBBookService DBBookService = new DBBookService(DBBookRepo);

        Repository<Long, Client> DBClientRepo = new ClientDBRepo(clientValidator);
        DBClientService DBClientService = new DBClientService(DBClientRepo);

        Repository<Long, Purchase> DBPurchaseRepo = new PurchaseDBRepo(purchaseValidator);
        DBPurchaseService DBPurchaseService = new DBPurchaseService(DBPurchaseRepo);

        Console console = new Console(bookServiceFile, clientServiceFile, purchaseService,XMLBookService, XMLClientService, DBBookService, DBClientService, DBPurchaseService);
        console.runConsole();
        System.out.println("Bye World!");
    }
}