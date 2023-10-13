package com.wild.Rest.controller;


import com.wild.Rest.entity.Books;
import com.wild.Rest.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired

    BooksRepository booksRepository;
    private static List<Books> books = new ArrayList<>();


    @GetMapping
    public List<Books> index() {

        return booksRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        // les méthodes du repository (pas toutes) renvoient des optional(: wrapper qui va permettre d'ajouter une vérif supplémentaire sur l'existance d'une variable)

        Optional<Books> optionalBooks = booksRepository.findById(id);
        // la méthode isPresent() permet de vérifier l'existence de la valeur d'où la condition if,
        if (optionalBooks.isPresent()) {
            // si elle est vraiment présente je peux la récuperer grâce à la méthode get()
            Books book = optionalBooks.get();
            return ResponseEntity.ok(book);
        } else {
            // sinon je retourne un status 404 not found.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nope");
        }
    }

    @PostMapping("/search")
    public List<Books> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return booksRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }


    @PostMapping
    public Books create(@RequestBody Books books) {

        return booksRepository.save(books);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Books> update(@PathVariable Long id, @RequestBody Books updatedBook) {
        Optional<Books> existingBookOptional = booksRepository.findById(id);

        if (existingBookOptional.isPresent()) {

            Books existingBook = existingBookOptional.get();
            // Mettez à jour les propriétés du livre existant avec les nouvelles données :
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setDescription(updatedBook.getDescription());


            return ResponseEntity.ok(booksRepository.save(existingBook));
        } else {
            // Le livre n'a pas été trouvé
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {

        booksRepository.deleteById(id);
        return true;
    }
}
