package hello.jdbc.controller;

import hello.jdbc.domain.Member;
import hello.jdbc.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/user/")
    public Member findById(String memberId){
        return memberService.findById(memberId);
    }

    @PostMapping("/user/{id}/{money}")
    public void update(@PathVariable("id") String memberId,
                      @PathVariable("money") int money){
        memberService.update(memberId, money);
    }

    @PostMapping("/account")
    public void transferMoney(@RequestParam String fromMemberId,
                              @RequestParam String toMemberId,
                              @RequestParam int money){
        memberService.accountTransfer(fromMemberId, toMemberId, money);
    }

}
