package kr.co.coupang.member.service;

import kr.co.coupang.member.domain.Member;

public interface MemberService {
	/**
	 * ��� ��� service
	 * @param member
	 * @return int
	 */
	public int registerMember(Member member);

	public int updateMember(Member member);

	/**
	 * ȸ�� Ż�� 
	 * @param memberId
	 * @return
	 */
	public int removeMember(String memberId);

	/**
	 * ��� �α��� Service
	 * @param member
	 * @return member��ü
	 */ 
	public Member memberLoginCheck(Member member);

	/**
	 * ȸ�� �� ��ȸ 
	 * @param memberId
	 * @return
	 */
	public Member showOneById(String memberId);

}
