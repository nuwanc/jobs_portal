package com.jobs.portal.controller;

import com.jobs.portal.dto.Search;
import com.jobs.portal.entity.profile.Profile;
import com.jobs.portal.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @PostMapping
    public ResponseEntity<List<Profile>> search(@RequestBody Search search, Authentication authentication) {
        List<Profile> profiles = Collections.emptyList();
        switch (search.getSearchField()) {
            case "education":
                if ("1".equals(search.getLevelOp())) {
                    if (!"0".equals(search.getPassesOp())) {
                        if ("1".equals(search.getPassesOp())) {
                            profiles = searchService.searchByPasses(search.getLevel(),Integer.valueOf(search.getPasses()));
                        } else {
                            profiles = searchService.searchByMinimumPasses(search.getLevel(), Integer.valueOf(search.getPasses()));
                        }
                    } else {
                        profiles = searchService.searchByEducation(search.getLevel());
                    }
                } else if ("2".equals(search.getLevelOp())) {
                    profiles = searchService.searchByMinimumEducation(search.getLevel());
                }
                return ResponseEntity.ok(profiles);
            case "skill":
                if (!"0".equals(search.getSkillOp())) {
                    if ("1".equals(search.getSkillOp())) {
                        profiles = searchService.searchBySkillNameAndYears(search.getSkillName(),Integer.valueOf(search.getSkillYears()));
                    } else {
                        profiles = searchService.searchBySkillNameAndMinimumYears(search.getSkillName(),Integer.valueOf(search.getSkillYears()));
                    }
                } else {
                    profiles = searchService.searchBySkillName(search.getSkillName());
                }
                return ResponseEntity.ok(profiles);
            case "experience":
                if (!"0".equals(search.getExpOp())) {
                    if ("1".equals(search.getExpOp())) {
                        profiles = searchService.searchByTitleAndYears(search.getJobTitle(),Integer.valueOf(search.getExpYears()));
                    } else {
                        profiles = searchService.searchByTitleAndMinimumYears(search.getJobTitle(),Integer.valueOf(search.getExpYears()));
                    }
                } else {
                    profiles = searchService.searchByTitle(search.getJobTitle());
                }
                return ResponseEntity.ok(profiles);
            default:
                break;
        }
        return ResponseEntity.ok(profiles);
    }
}
