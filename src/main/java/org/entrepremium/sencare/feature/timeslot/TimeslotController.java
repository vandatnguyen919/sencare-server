package org.entrepremium.sencare.feature.timeslot;

import lombok.RequiredArgsConstructor;
import org.entrepremium.sencare.feature.timeslot.converter.TimeslotToDtoConverter;
import org.entrepremium.sencare.feature.timeslot.dto.TimeslotDto;
import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/timeslots")
@RequiredArgsConstructor
public class TimeslotController {

    private final TimeslotService timeslotService;
    private final TimeslotToDtoConverter timeslotToDtoConverter;

    @GetMapping
    public Result findAllTimeslots(Pageable pageable) {
        Page<Timeslot> foundTimeslots = timeslotService.findAll(pageable);
        Page<TimeslotDto> timeslotDtos = foundTimeslots.map(timeslotToDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Timeslots Success", timeslotDtos);
    }

    @GetMapping("/{timeslotId}")
    public Result findTimeslotById(@PathVariable String timeslotId) {
        Timeslot foundTimeslot = timeslotService.findById(timeslotId);
        TimeslotDto timeslotDto = timeslotToDtoConverter.convert(foundTimeslot);
        return new Result(true, StatusCode.SUCCESS, "Find One Timeslot Success", timeslotDto);
    }

    @GetMapping("/doctor/{doctorId}")
    public Result findTimeslotsByDoctorId(@PathVariable String doctorId) {
        List<Timeslot> foundTimeslots = timeslotService.findByDoctorId(doctorId);
        List<TimeslotDto> timeslotDtos = foundTimeslots.stream()
                .map(timeslotToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Timeslots By Doctor Success", timeslotDtos);
    }

    @GetMapping("/date/{date}")
    public Result findTimeslotsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Timeslot> foundTimeslots = timeslotService.findByDate(date);
        List<TimeslotDto> timeslotDtos = foundTimeslots.stream()
                .map(timeslotToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Timeslots By Date Success", timeslotDtos);
    }

    @GetMapping("/available")
    public Result findAvailableTimeslots() {
        List<Timeslot> foundTimeslots = timeslotService.findByOccupiedStatus(false);
        List<TimeslotDto> timeslotDtos = foundTimeslots.stream()
                .map(timeslotToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Available Timeslots Success", timeslotDtos);
    }

    @GetMapping("/doctor/{doctorId}/date/{date}")
    public Result findTimeslotsByDoctorAndDate(@PathVariable String doctorId,
                                               @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Timeslot> foundTimeslots = timeslotService.findByDoctorIdAndDate(doctorId, date);
        List<TimeslotDto> timeslotDtos = foundTimeslots.stream()
                .map(timeslotToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Timeslots By Doctor And Date Success", timeslotDtos);
    }

    @PostMapping
    public Result addTimeslot(@RequestBody Timeslot newTimeslot) {
        Timeslot savedTimeslot = timeslotService.save(newTimeslot);
        TimeslotDto savedTimeslotDto = timeslotToDtoConverter.convert(savedTimeslot);
        return new Result(true, StatusCode.SUCCESS, "Add Timeslot Success", savedTimeslotDto);
    }

    @PutMapping("/{timeslotId}")
    public Result updateTimeslot(@PathVariable String timeslotId, @RequestBody Timeslot update) {
        Timeslot updatedTimeslot = timeslotService.update(timeslotId, update);
        TimeslotDto updatedTimeslotDto = timeslotToDtoConverter.convert(updatedTimeslot);
        return new Result(true, StatusCode.SUCCESS, "Update Timeslot Success", updatedTimeslotDto);
    }

    @DeleteMapping("/{timeslotId}")
    public Result deleteTimeslot(@PathVariable String timeslotId) {
        timeslotService.delete(timeslotId);
        return new Result(true, StatusCode.SUCCESS, "Delete Timeslot Success");
    }
}
