package mrs.app.room;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import mrs.domain.model.MeetingRoom;
import mrs.domain.model.ReservableRoom;
import mrs.domain.service.room.RoomService;

@Controller
@RequestMapping("rooms")
public class RoomsController {

    private RoomService roomService;

    public RoomsController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String listRooms(Model model) {
        LocalDate today = LocalDate.now();
        model.addAttribute("date", today);
        return listRooms(today, model);
    }

    @GetMapping(path = "{date}")
    public String listRooms(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate date, Model model) {
        List<ReservableRoom> reservedRoomList = roomService.findReservableRoomList(date);
        model.addAttribute("roomList", reservedRoomList);
        MeetingRoom meeting = new MeetingRoom();
        meeting.setRoomId(1);
        meeting.setRoomName("test");
        ReservableRoom room = new ReservableRoom();
        room.setMeetingRoom(meeting);
        return "room/listRooms";
    }

}
