package org.entrepremium.sencare.features.workexperience;
import org.entrepremium.sencare.features.workexperience.converter.WorkExperienceToDtoConverter;
import org.entrepremium.sencare.features.workexperience.dto.WorkExperienceDto;
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
@RequestMapping("${api.endpoint.base-url}/work-experiences")
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;
    private final WorkExperienceToDtoConverter workExperienceToWorkExperienceDtoConverter;

    public WorkExperienceController(WorkExperienceService workExperienceService,
                                    WorkExperienceToDtoConverter workExperienceToDtoConverter) {
        this.workExperienceService = workExperienceService;
        this.workExperienceToWorkExperienceDtoConverter = workExperienceToDtoConverter;
    }

    @GetMapping
    public Result findAllWorkExperiences(Pageable pageable) {
        Page<WorkExperience> foundWorkExperiences = workExperienceService.findAll(pageable);
        Page<WorkExperienceDto> workExperienceDtos = foundWorkExperiences.map(workExperienceToWorkExperienceDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Work Experiences Success", workExperienceDtos);
    }

    @GetMapping("/{wexId}")
    public Result findWorkExperienceById(@PathVariable String wexId) {
        WorkExperience foundWorkExperience = workExperienceService.findById(wexId);
        WorkExperienceDto workExperienceDto = workExperienceToWorkExperienceDtoConverter.convert(foundWorkExperience);
        return new Result(true, StatusCode.SUCCESS, "Find One Work Experience Success", workExperienceDto);
    }

    @GetMapping("/doctor/{doctorId}")
    public Result findWorkExperiencesByDoctor(@PathVariable String doctorId) {
        List<WorkExperience> foundWorkExperiences = workExperienceService.findByDoctor(doctorId);
        List<WorkExperienceDto> workExperienceDtos = foundWorkExperiences.stream()
                .map(workExperienceToWorkExperienceDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Work Experiences By Doctor Success", workExperienceDtos);
    }

    @GetMapping("/job-title")
    public Result findWorkExperiencesByJobTitle(@RequestParam String jobTitle) {
        List<WorkExperience> foundWorkExperiences = workExperienceService.findByJobTitle(jobTitle);
        List<WorkExperienceDto> workExperienceDtos = foundWorkExperiences.stream()
                .map(workExperienceToWorkExperienceDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Work Experiences By Job Title Success", workExperienceDtos);
    }

    @GetMapping("/start-date-after")
    public Result findWorkExperiencesByStartDateAfter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        List<WorkExperience> foundWorkExperiences = workExperienceService.findByStartDateAfter(startDate);
        List<WorkExperienceDto> workExperienceDtos = foundWorkExperiences.stream()
                .map(workExperienceToWorkExperienceDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Work Experiences By Start Date Success", workExperienceDtos);
    }

    @GetMapping("/current")
    public Result findCurrentWorkExperiences() {
        List<WorkExperience> foundWorkExperiences = workExperienceService.findCurrentExperiences();
        List<WorkExperienceDto> workExperienceDtos = foundWorkExperiences.stream()
                .map(workExperienceToWorkExperienceDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Current Work Experiences Success", workExperienceDtos);
    }

    @PostMapping
    public Result addWorkExperience(@RequestBody WorkExperience newWorkExperience) {
        WorkExperience savedWorkExperience = workExperienceService.save(newWorkExperience);
        WorkExperienceDto savedWorkExperienceDto = workExperienceToWorkExperienceDtoConverter.convert(savedWorkExperience);
        return new Result(true, StatusCode.SUCCESS, "Add Work Experience Success", savedWorkExperienceDto);
    }

    @PutMapping("/{wexId}")
    public Result updateWorkExperience(@PathVariable String wexId, @RequestBody WorkExperience update) {
        WorkExperience updatedWorkExperience = workExperienceService.update(wexId, update);
        WorkExperienceDto updatedWorkExperienceDto = workExperienceToWorkExperienceDtoConverter.convert(updatedWorkExperience);
        return new Result(true, StatusCode.SUCCESS, "Update Work Experience Success", updatedWorkExperienceDto);
    }

    @DeleteMapping("/{wexId}")
    public Result deleteWorkExperience(@PathVariable String wexId) {
        workExperienceService.delete(wexId);
        return new Result(true, StatusCode.SUCCESS, "Delete Work Experience Success");
    }
}