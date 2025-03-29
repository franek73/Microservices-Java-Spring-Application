package pl.edu.pg.eti.instrument.controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pg.eti.category.entities.Category;
import pl.edu.pg.eti.category.services.CategoryService;
import pl.edu.pg.eti.instrument.dtos.GetInstrumentResponse;
import pl.edu.pg.eti.instrument.dtos.GetInstrumentsResponse;
import pl.edu.pg.eti.instrument.dtos.PutInstrumentRequest;
import pl.edu.pg.eti.instrument.entities.Instrument;
import pl.edu.pg.eti.instrument.services.InstrumentService;

import java.util.*;
import java.util.function.*;
import java.util.logging.Level;

@RestController
@Log
public class InstrumentController {
    private final InstrumentService instrumentService;

    private final CategoryService categoryService;

    @Autowired
    public InstrumentController(InstrumentService instrumentService, CategoryService categoryService) {
        this.instrumentService = instrumentService;
        this.categoryService = categoryService;
    }

    private final Function<Instrument, GetInstrumentResponse> instrumentToResponse = entity -> {
        return GetInstrumentResponse.builder()
                .uuid(entity.getUuid())
                .name(entity.getName())
                .description(entity.getDescription())
                .category(GetInstrumentResponse.Category.builder()
                        .uuid(entity.getCategory().getUuid())
                        .build())
                .build();
    };

    private final Function<List<Instrument>, GetInstrumentsResponse> instrumentsToResponse = entities -> {
        return GetInstrumentsResponse.builder()
                .instruments(entities.stream()
                        .map(instrument -> GetInstrumentsResponse.Instrument.builder()
                                .uuid(instrument.getUuid())
                                .name(instrument.getName())
                                .description(instrument.getDescription())
                                .build())
                        .toList())
                .build();
    };

    private final BiFunction<PutInstrumentRequest, UUID, Instrument> requestToInstrument = (request, id) -> {
        return Instrument.builder()
                .uuid(id)
                .name(request.getName())
                .description(request.getDescription())
                .category(Category.builder()
                        .uuid(request.getCategory())
                        .build())
                .build();
    };


    @GetMapping("/api/instruments")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetInstrumentsResponse getInstruments() {
        log.log(Level.INFO, "getInstruments");
        return instrumentsToResponse.apply(instrumentService.findAll());
    }

    @GetMapping("/api/instruments/category/{id}")
    public ResponseEntity<GetInstrumentsResponse> getInstrumentsByCategory(@PathVariable("id") UUID id) {
        log.log(Level.INFO, "getInstrumentsByCategory");
        if (categoryService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<List<Instrument>> instruments = instrumentService.findAllByCategory(id);
        if (instruments.isEmpty() || instruments.get().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            List<Instrument> instrumentsList = instruments.get();
            GetInstrumentsResponse response = instrumentsToResponse.apply(instrumentsList);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/api/instruments/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetInstrumentResponse getInstrument(@PathVariable("id") UUID id) {
        log.log(Level.INFO, "getInstrument");
        return instrumentService.findById(id)
                .map(instrumentToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/api/instruments/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void putInstrument(@PathVariable("id") UUID id, @RequestBody PutInstrumentRequest request) {
        log.log(Level.INFO, "putInstrument");
        instrumentService.create(requestToInstrument.apply(request, id));
    }

    @DeleteMapping("/api/instruments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInstrument(@PathVariable("id") UUID id) {
        log.log(Level.INFO, "deleteInstrument");
        instrumentService.findById(id)
                .ifPresentOrElse(
                        instrument -> {
                            instrument.setCategory(null);
                            instrumentService.deleteByID(id);
                        },
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }

}
