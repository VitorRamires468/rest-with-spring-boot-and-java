package br.com.vitorramires468.controllers.docs;

import br.com.vitorramires468.data.dto.BookDTO;
import br.com.vitorramires468.data.dto.PersonDTO;
import br.com.vitorramires468.services.BookServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface BookControllerDocs {

    @GetMapping("/{id}")
    @Operation(summary = "Get a book", description = "Get a specific book by its id", tags = {"books"},
            responses ={
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = BookDTO.class)
                            )
                    }),
            @ApiResponse(description = "No content", responseCode = "204", content = @Content()),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
            @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content())     }
    )
    BookDTO findById(@PathVariable @Parameter(name = "id", description = "Book id", example = "1") int id);

    @GetMapping
    @Operation(summary = "Get all books", description = "Get all books from the database", tags = {"books"},
            responses ={
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content())     }
    )
    public List<BookDTO> findAll();

    @PostMapping
    @Operation(summary = "Create a book", description = "Create a new a book", tags = {"books"},
            responses ={
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = BookDTO.class)
                                    )
                            }),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content())     }
    )
    public BookDTO create(@RequestBody BookDTO bookDTO);

    @PutMapping
    @Operation(summary = "Update a book", description = "Update a book", tags = {"books"},
            responses ={
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = BookDTO.class)
                                    )
                            }),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content())     }
    )
    public BookDTO update(@RequestBody BookDTO bookDTO);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Delete a book by its Id", tags = {"books"},
            responses ={
                    @ApiResponse(
                            description = "Success",
                            responseCode = "204",
                            content = {
                                    @Content(
                                    )
                            }),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content())     }
    )
    public ResponseEntity delete(@PathVariable int id);
}
