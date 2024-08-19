package lk.ijse.introwithspringweb.controller;

import lk.ijse.introwithspringweb.dto.Customer;
import lk.ijse.introwithspringweb.dto.Item;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    // query parameters with multiple values: This matches requests with multiple query parameters.
    @PostMapping(params = {"name", "quantity"})
    public String paramData(@RequestParam("name") String param1, @RequestParam("quantity") String param2) {
        return "Param data is : "+ param1 + " and " + param2;
    }

    // path variable with regex and custom header: This matches a path segment with a variable constrained by a regular expression and a custom header.
    @GetMapping(value = "/path-segment/{id:C\\d{3}}", headers = "X-number")
    public String pathVariableWithRegexAndHeader(@PathVariable("id") String id, @RequestHeader("X-number") int num) {
        return "Path variable including regex works accurately with : " + id + " and Header is : " + num;
    }

    // consumes: This specifies the media type that the endpoint can consume, allowing the server to process requests with the specified media type.
    // @PostMapping(consumes = "application/json")
    // ----- OR -----
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveJSON() {
        return "JSON data saved successfully";
    }

    // dynamic path variable with conditional response: This matches a path segment with a dynamic variable and returns a response based on the value of the variable.
    @PostMapping("/dynamic/{value:\\d{2}}")
    public ResponseEntity<String> returnDynamicData(@PathVariable("value") int incomingValue) {
       if (incomingValue % 2 == 0) {
           return ResponseEntity.ok("Even Number");
       }
       return ResponseEntity.ok("Odd Number");
    }

    // handle dynamic query parameters with Map: This matches requests with dynamic query parameters and stores them in a map.
    @PostMapping(value = "/mapParams", params = {"id", "desc"})
    public String handleMaps(@RequestParam("id") String id, @RequestParam("desc") String desc, @RequestParam Map<String, String> params) {
        System.out.println(params);
        return "Handle Map with Params : " + params;
    }

    // handle dynamic query parameters with MultiValueMap: This matches requests with dynamic query parameters and stores them in a MultiValueMap.
    @PostMapping(value = "/multiMapParams", params = {"id", "desc"})
    public String handleMultiMaps(@RequestParam("id") String id, @RequestParam("desc") String desc, @RequestParam MultiValueMap<String, String> params) {
        System.out.println(params);
        return "Handle Map with Params : " + params;
    }

    // handle dynamic query parameters with MultiValueMap and set to DTO: This matches requests with dynamic query parameters, stores them in a MultiValueMap, and sets them to a DTO.
    @PostMapping(value = "/multiMapParamsToDto", params = {"id","desc"})
    public String handleMultiMapsWithDTO(@RequestParam ("id") String id, @RequestParam ("desc")String desc, @RequestParam MultiValueMap<String,String> params, Item item){
        System.out.println(item);
        return "Handle Maps with params and set to DTO "+ params;
    }

    // convert JSON to DTO : This converts JSON data in the request body to a DTO.
    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String JSONToTO(@RequestBody Customer customer) {
        System.out.println(customer.getId());
        System.out.println(customer.getName());
        System.out.println(customer.getEmail());
        return "Customer : " + customer.getName() + " retrieved successfully";
    }
 }
