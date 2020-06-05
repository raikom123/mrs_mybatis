package mrs.app.reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mrs.domain.model.ReservableRoom;
import mrs.domain.model.ReservableRoomId;
import mrs.domain.model.Reservation;
import mrs.domain.service.reservation.ReservationService;
import mrs.domain.service.room.RoomService;
import mrs.domain.service.user.ReservationUserDetails;

@Controller
@RequestMapping("/reservations/{date}/{roomId}")
public class ReservationsController {

    private RoomService roomService;

    private ReservationService reservationService;

    @Autowired
    public ReservationsController(RoomService roomService, ReservationService reservationService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    @ModelAttribute
    public ReservationForm setupForm() {
        ReservationForm form = new ReservationForm();
        form.setStartTime(LocalTime.of(9, 0));
        form.setEndTime(LocalTime.of(10, 0));
        return form;
    }

    @GetMapping
    public String reserveForm(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
            @PathVariable("roomId") Integer roomId, Model model) {
        ReservableRoomId reservableRoomId = new ReservableRoomId(roomId, date);
        List<Reservation> reservationList = reservationService.findReservationList(reservableRoomId);

        List<LocalTime> timeList = Stream.iterate(LocalTime.of(0, 0), t -> t.plusMinutes(30))
                .limit(24 * 2)
                .collect(Collectors.toList());

        model.addAttribute("room", roomService.findMeetingRoom(roomId));
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("timeList", timeList);

        return "reservation/reserveForm";
    }

    @PostMapping
    public String reserve(@Validated ReservationForm form,
            BindingResult bindingResult,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
            @PathVariable("roomId") Integer roomId, @AuthenticationPrincipal ReservationUserDetails userDetails,
            Model model) {

        if (bindingResult.hasErrors()) {
            return reserveForm(date, roomId, model);
        }

        var reservableRoom = new ReservableRoom(new ReservableRoomId(roomId, date));
        var reservation = new Reservation();
        reservation.setStartTime(form.getStartTime());
        reservation.setEndTime(form.getEndTime());
        reservation.setReservableRoom(reservableRoom);
        reservation.setUser(userDetails.getUser());

        try {
            reservationService.reserve(reservation);
        } catch (Exception e) {
            return handleException(e, date, roomId, model);
        }

        return "redirect:/reservations/{date}/{roomId}";
    }

    @PostMapping(params = "cancel")
    public String cancel(@RequestParam("reservationId") Integer reservationId, @PathVariable("roomId") Integer roomId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
            @AuthenticationPrincipal ReservationUserDetails userDetails, Model model) {
        try {
//            reservationService.cancel(reservationId, userDetails.getUser());
            Reservation reservation = reservationService.findOne(reservationId);
            reservationService.cancel(reservation);
        } catch (Exception e) {
            return handleException(e, date, roomId, model);
        }

        return "redirect:/reservations/{date}/{roomId}";
    }

    private String handleException(Exception e, LocalDate date, Integer roomId, Model model) {
        e.printStackTrace();
        model.addAttribute("error", e.getMessage());
        return reserveForm(date, roomId, model);
    }

}
