package com.dzg.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class TestVo {
    @NotBlank
    private String msg;
    @NotNull
    @Max(value = 10,message = "id不能大于10")
    @Min(value = 0, message = "id 至少大于等于0")
    private Integer id;
    private List<String> str;
}
