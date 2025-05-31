package org.entrepremium.sencare.features.doctorsystem.education;

import org.entrepremium.sencare.features.doctorsystem.education.converter.EducationToDtoConverter;
import org.entrepremium.sencare.features.doctorsystem.education.dto.EducationDto;
import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/educations")
public class EducationController {

    private final EducationService educationService;
    private final EducationToDtoConverter educationToDtoConverter;

    public EducationController(EducationService educationService,
                               EducationToDtoConverter educationToDtoConverter) {
        this.educationService = educationService;
        this.educationToDtoConverter = educationToDtoConverter;
    }

    @GetMapping
    public Result findAllEducations() {
        List<Education> foundEducations = educationService.findAll();
        List<EducationDto> educationDtos = foundEducations.stream()
                .map(educationToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Educations Success", educationDtos);
    }

    @GetMapping("/{eduId}")
    public Result findEducationById(@PathVariable String eduId) {
        Education foundEducation = educationService.findById(eduId);
        EducationDto educationDto = educationToDtoConverter.convert(foundEducation);
        return new Result(true, StatusCode.SUCCESS, "Find One Education Success", educationDto);
    }

    @GetMapping("/doctor/{doctorId}")
    public Result findEducationsByDoctor(@PathVariable String doctorId) {
        List<Education> foundEducations = educationService.findByDoctor(doctorId);
        List<EducationDto> educationDtos = foundEducations.stream()
                .map(educationToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Educations By Doctor Success", educationDtos);
    }

    @GetMapping("/college")
    public Result findEducationsByCollege(@RequestParam String collegeName) {
        List<Education> foundEducations = educationService.findByCollege(collegeName);
        List<EducationDto> educationDtos = foundEducations.stream()
                .map(educationToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Educations By College Success", educationDtos);
    }

    @GetMapping("/year/{year}")
    public Result findEducationsByYear(@PathVariable Integer year) {
        List<Education> foundEducations = educationService.findByYear(year);
        List<EducationDto> educationDtos = foundEducations.stream()
                .map(educationToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Educations By Year Success", educationDtos);
    }

    @PostMapping
    public Result addEducation(@RequestBody Education newEducation) {
        Education savedEducation = educationService.save(newEducation);
        EducationDto savedEducationDto = educationToDtoConverter.convert(savedEducation);
        return new Result(true, StatusCode.SUCCESS, "Add Education Success", savedEducationDto);
    }

    @PutMapping("/{eduId}")
    public Result updateEducation(@PathVariable String eduId, @RequestBody Education update) {
        Education updatedEducation = educationService.update(eduId, update);
        EducationDto updatedEducationDto = educationToDtoConverter.convert(updatedEducation);
        return new Result(true, StatusCode.SUCCESS, "Update Education Success", updatedEducationDto);
    }

    @DeleteMapping("/{eduId}")
    public Result deleteEducation(@PathVariable String eduId) {
        educationService.delete(eduId);
        return new Result(true, StatusCode.SUCCESS, "Delete Education Success");
    }
}