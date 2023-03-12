package hello.jdbc.controller;

import hello.jdbc.domain.Member;
import hello.jdbc.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public Member findById(String memberId){
        return memberService.findById(memberId);
    }

}
