package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Sponsor;
import hcmute.fit.event_management.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SponsorServiceImpl implements ISponsorService {
    @Autowired
    private SponsorRepository sponsorRepository;

    public SponsorServiceImpl(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    @Override
    public List<Sponsor> findAll() {
        return sponsorRepository.findAll();
    }

    @Override
    public List<Sponsor> findAllById(Iterable<Integer> integers) {
        return sponsorRepository.findAllById(integers);
    }

    @Override
    public <S extends Sponsor> List<S> saveAll(Iterable<S> entities) {
        return sponsorRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return sponsorRepository.count();
    }

    @Override
    public void delete(Sponsor entity) {
        sponsorRepository.delete(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        sponsorRepository.deleteById(integer);
    }

    @Override
    public Optional<Sponsor> findById(Integer integer) {
        return sponsorRepository.findById(integer);
    }

    @Override
    public <S extends Sponsor> S save(S entity) {
        return sponsorRepository.save(entity);
    }

    @Override
    public List<Sponsor> findAll(Sort sort) {
        return sponsorRepository.findAll(sort);
    }

    @Override
    public <S extends Sponsor> Optional<S> findOne(Example<S> example) {
        return sponsorRepository.findOne(example);
    }
}
