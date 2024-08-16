package lk.ijse.introwithspringweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
    // default get mapping
    @GetMapping
    public String aTextWillReturn() {
        return "A Text";
    }

    // static path : This is a fixed path segment that matches exactly.
    @GetMapping("/health")
    public String healthCheck() {
        return "DemoController run perfectly";
    }

    // optional path segment: This denotes an optional segment, meaning the path can either include or omit this segment.
    @GetMapping("/path-segment?")
    public String optionalPathSegmentTest() {
        return "Optional path segment works accurately";
    }

    // fixed-length path segment: This matches a segment of a specific length, though this is less common and often frameworks use regular expressions for such cases.
    @GetMapping("/path-segment???")
    public String fixedLengthPathSegmentTest() {
        return "Fixed-length path segment works accurately";
    }

    // wildcard path segment: This matches any path following the segment, often used for catching sub-paths.
    @GetMapping("/path-segment/*")
    public String wildcardPathSegmentTest() {
        return "Wildcard path segment works accurately";
    }

    // path traversal wildcard: This matches any path recursively under the segment, capturing multiple levels of sub-paths.
    @GetMapping("/path-segment/**")
    public String pathTraversalWildcardTest() {
        return "Path traversal wildcard works accurately";
    }

    // path variable: This matches a path segment with a variable that can be extracted and used within the application.
    @GetMapping("/path-segment/{name}")
    public String pathVariable(@PathVariable("name") String name) {
        return "Path variable works accurately with : " + name;
    }

    // path variable with regex: This matches a path segment with a variable constrained by a regular expression, providing more control over the format of the variable.
    @GetMapping("/path-segment/{id:C\\d{3}}")
    public String pathVariableWithRegex(@PathVariable("id") String id) {
        return "Path variable including regex works accurately with : " + id;
    }

    // query parameters: These are parameters appended to the URL after the ?, used for passing additional data in the request.
    @GetMapping(params = "test=all")
    public String paramsTest() {
        return "All are tested";
    }
}
