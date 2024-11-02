package hcmute.fit.event_management.service;

<<<<<<< HEAD
import hcmute.fit.event_management.entity.SponsorShip;
import hcmute.fit.event_management.repository.SponsorShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SponsorShipServiceImpl implements ISponsorShipService {
    @Autowired
    private SponsorShipRepository sponsorShipRepository;

    public SponsorShipServiceImpl(SponsorShipRepository sponsorShipRepository) {
        this.sponsorShipRepository = sponsorShipRepository;
    }

    @Override
    public long count() {
        return sponsorShipRepository.count();
    }

    @Override
    public void deleteAll() {
        sponsorShipRepository.deleteAll();
    }

    @Override
    public Optional<SponsorShip> findById(Integer integer) {
        return sponsorShipRepository.findById(integer);
    }

    @Override
    public <S extends SponsorShip> S save(S entity) {
        return sponsorShipRepository.save(entity);
    }

    @Override
    public List<SponsorShip> findAll(Sort sort) {
        return sponsorShipRepository.findAll(sort);
    }

    @Override
    public <S extends SponsorShip> Optional<S> findOne(Example<S> example) {
        return sponsorShipRepository.findOne(example);
    }

    @Override
    public void deleteById(Integer integer) {
        sponsorShipRepository.deleteById(integer);
    }
=======
public class SponsorShipServiceImpl {
>>>>>>> origin/master
}
