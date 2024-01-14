package com.spring.project.hotelbooking.service;

import com.spring.project.hotelbooking.dto.reservation.CreateReservationDTO;
import com.spring.project.hotelbooking.dto.reservation.ReservationCheckInDTO;
import com.spring.project.hotelbooking.dto.reservation.ReservationDTO;
import com.spring.project.hotelbooking.dto.reservation.ReservationExtendDateDto;
import com.spring.project.hotelbooking.entity.Reservation;
import com.spring.project.hotelbooking.entity.Room;
import com.spring.project.hotelbooking.entity.User;
import com.spring.project.hotelbooking.exception.ReservationNotFoundException;
import com.spring.project.hotelbooking.mapper.ReservationMapper;
import com.spring.project.hotelbooking.repository.ReservationRepository;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final ReservationMapper reservationMapper;
    private final RoomService roomService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository,
                              UserService userService,
                              ReservationMapper reservationMapper,
                              RoomService roomService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.reservationMapper = reservationMapper;
        this.roomService = roomService;
    }

    public List<ReservationDTO> getReservationsByUserEmail(String email) {
        List<Reservation> reservations = reservationRepository.getReservationByUserEmail(email);
        return reservations
                .stream()
                .map(reservationMapper::reservationToReservationDTO)
                .toList();
    }

    public List<ReservationDTO> getReservationForOneMonth(int userId) {
        List<Reservation> reservations = reservationRepository.getUserReservationsForMonth(userId);
        return reservations
                .stream()
                .map(reservationMapper::reservationToReservationDTO)
                .toList();
    }

    public Reservation createReservation(CreateReservationDTO reservationDTO) {
        Reservation createdReservation = reservationMapper.createReservationToReservation(reservationDTO);
        User user = userService.getUserById(reservationDTO.getUserId());
        List<Reservation> reservationsCountToCurrentUser = reservationRepository
                .getUserReservationsForMonth(user.getId());
        userService.updateLoyaltyByCountOfReservations(reservationsCountToCurrentUser.size(), user.getId());
        createdReservation.setBookingId(UUID.randomUUID().toString());
        createdReservation.setUser(user);
        Reservation savedReservation = reservationRepository.save(createdReservation);
        Set<Room> rooms = getRoomsFromReservationDTO(reservationDTO);
        savedReservation.setRooms(rooms);
        double priceOfRooms = calculatePriceOfRooms(rooms);
        savedReservation.setTotalPrice(calculateTotalPriceOfRooms(priceOfRooms,user));
        return reservationRepository.save(savedReservation);
    }

    public Reservation extensionReservationTime(int id, ReservationExtendDateDto updateDate) {
        Reservation reservationForUpdate = reservationRepository
                .findById(id)
                .orElseThrow(()-> new ReservationNotFoundException("Reservation not found!"));
        reservationForUpdate.setCheckOutDate(updateDate.getCheckInDate());
        return reservationRepository.save(reservationForUpdate);
    }

    public void deleteReservation(String id) {
        reservationRepository.deleteByBookingId(id);
    }

    public List<ReservationDTO> getAllNotCheckedInReservations() {
        List<Reservation> allNotCheckedReservations = reservationRepository.getAllNotCheckedInReservations();
        return allNotCheckedReservations
                .stream()
                .map(reservationMapper::reservationToReservationDTO)
                .toList();
    }

    public Reservation checkAndAddGuestsToReservation(int id, ReservationCheckInDTO reservationCheckInDTO) {
        Reservation reservation = reservationRepository
                .findById(id)
                .orElseThrow(()-> new ReservationNotFoundException("Reservation with this id not exit"));
        reservation.setResConfirmation(reservationCheckInDTO.isResConfirmation());
        reservation.setGuestInformations(reservationCheckInDTO.getGuestInformation());
        return reservationRepository.save(reservation);
    }

    private Set<Room> getRoomsFromReservationDTO(CreateReservationDTO reservationDTO) {
        Set<Room> rooms = new HashSet<>();
        for (int id: reservationDTO.getRoomIds()) {
            Room room = roomService.getRoomById(id);
            rooms.add(room);
        }
        return rooms;
    }

    private double calculateTotalPriceOfRooms(double priceOfRooms, User user) {
        int discount = user.getLoyalty().getDiscount();
        return priceOfRooms - ((priceOfRooms * discount)) / 100;
    }

    private double calculatePriceOfRooms(Set<Room> rooms) {
        double sum = 0;
        for (Room room: rooms) {
            sum += room.getPricePerNight();
        }
        return sum;
    }
}