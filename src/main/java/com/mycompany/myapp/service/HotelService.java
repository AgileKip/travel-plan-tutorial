package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Hotel;
import com.mycompany.myapp.repository.HotelRepository;
import com.mycompany.myapp.service.dto.HotelDTO;
import com.mycompany.myapp.service.mapper.HotelMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Hotel}.
 */
@Service
@Transactional
public class HotelService {

    private final Logger log = LoggerFactory.getLogger(HotelService.class);

    private final HotelRepository hotelRepository;

    private final HotelMapper hotelMapper;

    public HotelService(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    /**
     * Save a hotel.
     *
     * @param hotelDTO the entity to save.
     * @return the persisted entity.
     */
    public HotelDTO save(HotelDTO hotelDTO) {
        log.debug("Request to save Hotel : {}", hotelDTO);
        Hotel hotel = hotelMapper.toEntity(hotelDTO);
        hotel = hotelRepository.save(hotel);
        return hotelMapper.toDto(hotel);
    }

    /**
     * Partially update a hotel.
     *
     * @param hotelDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<HotelDTO> partialUpdate(HotelDTO hotelDTO) {
        log.debug("Request to partially update Hotel : {}", hotelDTO);

        return hotelRepository
            .findById(hotelDTO.getId())
            .map(
                existingHotel -> {
                    hotelMapper.partialUpdate(existingHotel, hotelDTO);
                    return existingHotel;
                }
            )
            .map(hotelRepository::save)
            .map(hotelMapper::toDto);
    }

    /**
     * Get all the hotels.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<HotelDTO> findAll() {
        log.debug("Request to get all Hotels");
        return hotelRepository.findAll().stream().map(hotelMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one hotel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HotelDTO> findOne(Long id) {
        log.debug("Request to get Hotel : {}", id);
        return hotelRepository.findById(id).map(hotelMapper::toDto);
    }

    /**
     * Delete the hotel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Hotel : {}", id);
        hotelRepository.deleteById(id);
    }
}
