package pl.edu.pg.eti.instrument.services;

import pl.edu.pg.eti.category.repositories.CategoryRepository;
import pl.edu.pg.eti.instrument.entities.Instrument;
import pl.edu.pg.eti.instrument.repositories.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InstrumentService {

    private final InstrumentRepository instrumentRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public InstrumentService(InstrumentRepository instrumentRepository, CategoryRepository categoryRepository) {
        this.instrumentRepository = instrumentRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<Instrument> findAll() {
        return instrumentRepository.findAll();
    }

    @Transactional
    public Optional<Instrument> findById(UUID id) {
        return instrumentRepository.findById(id);
    }

    @Transactional
    public Instrument create(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    @Transactional
    public void update(Instrument instrument) {
        instrumentRepository.save(instrument);
    }

    @Transactional
    public void delete(Instrument instrument) {
        instrumentRepository.delete(instrument);
    }

    @Transactional
    public void deleteByID(UUID id) {
        instrumentRepository.deleteById(id);
    }

    @Transactional
    public Optional<List<Instrument>> findAllByCategory(UUID id) {
        return categoryRepository.findById(id)
                .map(instrumentRepository::findAllByCategory);
    }

    public void deleteAll() {
        instrumentRepository.deleteAll();
    }
}
