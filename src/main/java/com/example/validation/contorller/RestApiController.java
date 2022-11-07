package com.example.validation.contorller;


import com.example.validation.dto.Member;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api-v2")
@Validated
public class RestApiController {

    @GetMapping("/member")
    public Member get(
            @Size(min = 2)
            @RequestParam(required = false) String name,

            @NotNull
            @Min(1)
            @RequestParam(required = false) Integer age){
        Member member = new Member();
        member.setName(name);
        member.setAge(age);

        int a = 10+age;


        return member;
    }

    @PostMapping("/")
    public Member post(@Valid @RequestBody Member member){
        System.out.println(member);
        return member;
    }

//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
//
//        System.out.println("api controller");
//
//        // controller안에 직접 @ExcpetionHandler를 지정해주면 전역처리되기전에 적용한 controller에서 처리가 된다는 점
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
}
