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
                switch (search.getLevelOp()) {
                    case "1":
                        switch (search.getPassesOp()) {
                            case "1":
                                profiles = searchService.searchByPasses(search.getLevel(),Integer.valueOf(search.getPasses()));
                                break;
                            case "2":
                                profiles = searchService.searchByMinimumPasses(search.getLevel(), Integer.valueOf(search.getPasses()));
                                break;
                            default:
                                profiles = searchService.searchByEducation(search.getLevel());
                                break;
                        }
                        break;
                    case "2":
                        profiles = searchService.searchByMinimumEducation(search.getLevel());
                        break;
                }
                return ResponseEntity.ok(profiles);
            case "skill":
                switch (search.getSkillOp()) {
                    case "1":
                        profiles = searchService.searchBySkillNameAndYears(search.getSkillName(),Integer.valueOf(search.getSkillYears()));
                        break;
                    case "2":
                        profiles = searchService.searchBySkillNameAndMinimumYears(search.getSkillName(),Integer.valueOf(search.getSkillYears()));
                        break;
                    default:
                        profiles = searchService.searchBySkillName(search.getSkillName());
                        break;
                }
                return ResponseEntity.ok(profiles);
            case "experience":
                switch (search.getExpOp()) {
                    case "1":
                        profiles = searchService.searchByTitleAndYears(search.getJobTitle(),Integer.valueOf(search.getExpYears()));
                        break;
                    case "2":
                        profiles = searchService.searchByTitleAndMinimumYears(search.getJobTitle(),Integer.valueOf(search.getExpYears()));
                    default:
                        profiles = searchService.searchByTitle(search.getJobTitle());
                        break;
                }
                return ResponseEntity.ok(profiles);
            default:
                break;
        }
        return ResponseEntity.ok(profiles);
    }
}
