package com.vinsguru.reactivevalidation.dto;

//import com.vinsguru.reactivevalidation.conditionalvalidation.UserIdentityValidator;
import com.vinsguru.reactivevalidation.conditionalvalidation.ValidSSN;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ToString
@ValidSSN
public class UserDto {

    private String firstName;

    @NotNull(message = "{lastname.not.null}")
    private String lastName;

    @Min(value = 10, message = "{age.min.requirement}")
    @Max(value = 50, message = "{age.max.requirement}")
    private int age;

    @Pattern(regexp = "([a-z])+@([a-z])+\\.com", message = "{email.pattern.mismatch}")
    private String email;

    @NotNull(message = "{country.not.null}")
    private String country;

    private Integer ssn;

}
