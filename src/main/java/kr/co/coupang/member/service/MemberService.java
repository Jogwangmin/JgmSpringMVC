package kr.co.coupang.member.service;

import kr.co.coupang.member.domain.Member;

public interface MemberService {
	/**
	 * ¸â¹ö µî·Ï service
	 * @param member
	 * @return int
	 */
	public int registerMember(Member member);

	public int updateMember(Member member);

	/**
	 * È¸¿ø Å»Åð 
	 * @param memberId
	 * @return
	 */
	public int removeMember(String memberId);

	/**
	 * ¸â¹ö ·Î±×ÀÎ Service
	 * @param member
	 * @return member°´Ã¼
	 */ 
	public Member memberLoginCheck(Member member);

	/**
	 * È¸¿ø »ó¼¼ Á¶È¸ 
	 * @param memberId
	 * @return
	 */
	public Member showOneById(String memberId);

}
