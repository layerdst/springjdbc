package hello.jdbc.service;

import hello.jdbc.domain.Member;

public interface MemberService {
    void accountTransfer(String formId, String toId, int money);
    Member findById(String memberId);
    void update(String memberId, int money);
}
