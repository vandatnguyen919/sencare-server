package org.entrepremium.sencare.features.specialization;

import org.entrepremium.sencare.features.specialization.converter.SpecializationToDtoConverter;
import org.entrepremium.sencare.features.specialization.dto.SpecializationDto;
import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/specializations")
public class SpecializationController {
    private final SpecializationService specializationService;
    private final SpecializationToDtoConverter specializationToDtoConverter;

    public SpecializationController(SpecializationService specializationService,
                                    SpecializationToDtoConverter specializationToDtoConverter) {
        this.specializationService = specializationService;
        this.specializationToDtoConverter = specializationToDtoConverter;
    }

    @GetMapping
    public Result findAllSpecializations(Pageable pageable) {
        Page<Specialization> foundSpecializations = specializationService.findAll(pageable);
        Page<SpecializationDto> specializationDtos = foundSpecializations.map(specializationToDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Specializations Success", specializationDtos);
    }

    @GetMapping("/{specId}")
    public Result findSpecializationById(@PathVariable String specId) {
        Specialization foundSpecialization = specializationService.findById(specId);
        SpecializationDto specializationDto = specializationToDtoConverter.convert(foundSpecialization);
        return new Result(true, StatusCode.SUCCESS, "Find One Specialization Success", specializationDto);
    }

    @GetMapping("/search-name")
    public Result searchSpecializationsByName(@RequestParam String name) {
        List<Specialization> foundSpecializations = specializationService.findByName(name);
        List<SpecializationDto> specializationDtos = foundSpecializations.stream()
                .map(specializationToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Search Specializations By Name Success", specializationDtos);
    }

    @PostMapping
    public Result addSpecialization(@RequestBody Specialization newSpecialization) {
        Specialization savedSpecialization = specializationService.save(newSpecialization);
        SpecializationDto savedSpecializationDto = specializationToDtoConverter.convert(savedSpecialization);
        return new Result(true, StatusCode.SUCCESS, "Add Specialization Success", savedSpecializationDto);
    }

    @PutMapping("/{specId}")
    public Result updateSpecialization(@PathVariable String specId, @RequestBody Specialization update) {
        Specialization updatedSpecialization = specializationService.update(specId, update);
        SpecializationDto updatedSpecializationDto = specializationToDtoConverter.convert(updatedSpecialization);
        return new Result(true, StatusCode.SUCCESS, "Update Specialization Success", updatedSpecializationDto);
    }

    @DeleteMapping("/{specId}")
    public Result deleteSpecialization(@PathVariable String specId) {
        specializationService.delete(specId);
        return new Result(true, StatusCode.SUCCESS, "Delete Specialization Success");
    }
}
