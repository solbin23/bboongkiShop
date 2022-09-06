package com.taco.tacoshop.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;



import javax.validation.constraints.Email;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@NoArgsConstructor
@Data
public class MemberDto {


    @NotNull(message = "이메일형식으로 입력해주세요.")
    @Email
    private String email;
    @NotNull(message = "이름은 필수 입력 값입니다.")
    private String name;
    @NotNull(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 5, max = 16, message = "비밀번호는 5자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotNull(message = "주소는 필수 입력 값입니다.")
    private String address;

    @Builder
    public MemberDto( String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;

    }


}
