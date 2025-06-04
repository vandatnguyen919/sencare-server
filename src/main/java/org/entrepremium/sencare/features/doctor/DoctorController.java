package org.entrepremium.sencare.features.doctor;

import org.entrepremium.sencare.features.doctor.converter.DoctorToDtoConverter;
import org.entrepremium.sencare.features.doctor.dto.DoctorDto;
import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorToDtoConverter doctorToDoctorDtoConverter;

    public DoctorController(DoctorService doctorService,
                            DoctorToDtoConverter doctorToDtoConverter) {
        this.doctorService = doctorService;
        this.doctorToDoctorDtoConverter = doctorToDtoConverter;
    }

    @GetMapping
    public Result findAllDoctors(Pageable pageable) {
        Page<Doctor> foundDoctors = doctorService.findAll(pageable);
        Page<DoctorDto> doctorDtos = foundDoctors.map(doctorToDoctorDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Doctors Success", doctorDtos);
    }

    @GetMapping("/{doctorId}")
    public Result findDoctorById(@PathVariable String doctorId) {
        Doctor foundDoctor = doctorService.findById(doctorId);
        DoctorDto doctorDto = doctorToDoctorDtoConverter.convert(foundDoctor);
        return new Result(true, StatusCode.SUCCESS, "Find One Doctor Success", doctorDto);
    }

    @GetMapping("/search-name")
    public Result searchDoctorsByName(@RequestParam String name) {
        List<Doctor> foundDoctors = doctorService.findByName(name);
        List<DoctorDto> doctorDtos = foundDoctors.stream()
                .map(doctorToDoctorDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Search Doctors By Name Success", doctorDtos);
    }

    @GetMapping("/price-range")
    public Result findDoctorsByPriceRange(@RequestParam BigDecimal minPrice,
                                          @RequestParam BigDecimal maxPrice) {
        List<Doctor> foundDoctors = doctorService.findByPriceRange(minPrice, maxPrice);
        List<DoctorDto> doctorDtos = foundDoctors.stream()
                .map(doctorToDoctorDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Doctors By Price Range Success", doctorDtos);
    }

    @PostMapping
    public Result addDoctor(@RequestBody Doctor newDoctor) {
        Doctor savedDoctor = doctorService.save(newDoctor);
        DoctorDto savedDoctorDto = doctorToDoctorDtoConverter.convert(savedDoctor);
        return new Result(true, StatusCode.SUCCESS, "Add Doctor Success", savedDoctorDto);
    }

    @PutMapping("/{doctorId}")
    public Result updateDoctor(@PathVariable String doctorId, @RequestBody Doctor update) {
        Doctor updatedDoctor = doctorService.update(doctorId, update);
        DoctorDto updatedDoctorDto = doctorToDoctorDtoConverter.convert(updatedDoctor);
        return new Result(true, StatusCode.SUCCESS, "Update Doctor Success", updatedDoctorDto);
    }

    @DeleteMapping("/{doctorId}")
    public Result deleteDoctor(@PathVariable String doctorId) {
        doctorService.delete(doctorId);
        return new Result(true, StatusCode.SUCCESS, "Delete Doctor Success");
    }
}
