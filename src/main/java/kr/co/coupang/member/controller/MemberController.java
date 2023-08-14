package kr.co.coupang.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.coupang.member.domain.Member;
import kr.co.coupang.member.service.MemberService;

@Controller
@SessionAttributes({"memberId", "memberName"})
public class MemberController {
	@Autowired
	private MemberService service;
	
	// doGet - ������ �̵���
	@RequestMapping(value="/member/register.do", method=RequestMethod.GET)
	public String showRegisterForm(Model model) {
		
		return "member/register";
	}

	@RequestMapping(value="/member/mypage.do", method=RequestMethod.GET)
	public String showDetailMember(
			@RequestParam("memberId")String memberId
			, Model model) {
		try {
			Member member = service.showOneById(memberId);
			if(member != null) {
				model.addAttribute("member", member);
				return "member/mypage";
			}else {
				model.addAttribute("msg", "�����Ͱ� ��ȸ���� �ʾҽ��ϴ�.");
				return "common.errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace();	
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
		
	}

	// doPost - ������ �����
	@RequestMapping(value="/member/register.do", method=RequestMethod.POST)
	public String registerMember(
			HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam("memberId")String memberId
			, @RequestParam("memberPw")String memberPw
			, @RequestParam("memberName")String memberName
			, @RequestParam("memberAge")int memberAge
			, @RequestParam("memberGender")String memberGender
			, @RequestParam("memberEmail")String memberEmail
			, @RequestParam("memberPhone")String memberPhone
			, @RequestParam("memberAddress")String memberAddress
			, @RequestParam("memberHobby")String memberHobby
			, Model model) {
//		request.setCharacterEncoding("UTF-8");
		Member member = new Member(memberId, memberPw, memberName, memberAge, memberGender, memberEmail, memberPhone, memberAddress, memberHobby);
		try {
			int result = service.registerMember(member);
			if(result > 0) {
				// ����
//				response.sendRedirect("/index.jsp");
				return "redirect:/index.jsp";
			}else {
				// ����
				model.addAttribute("msg", "ȸ�������� �Ϸ���� �ʾҽ��ϴ�.");
//				request.setAttribute("msg", "����");
				return "common/errorPage";
//				request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace(); // �ܼ�â�� ���������� �߰���
			model.addAttribute("msg", e.getMessage()); // �ܼ�â�� �ߴ� �޽����� ���������� �߰���
			return "common/errorPage";
		}
//		String memberId = request.getParameter("memberId");
//		request.setAttribute("", "");
//		request.getRequestDispatcher("/WEB-INF/views/member/register.jsp");
//		response.sendRedirect("/index.jsp");
	}
	@RequestMapping(value="/member/modify.do", method=RequestMethod.GET)
	public String modifyViewMember() {
		return "member/modify";
	}
	
	@RequestMapping(value="/member/modify.do", method=RequestMethod.POST)
	public String modifyMember(HttpServletRequest request
			, @RequestParam("memberId") String memberId
			, @RequestParam("memberPw") String memberPw
			, @RequestParam("memberEmail") String memberEmail
			, @RequestParam("memberPhone") String memberPhone
			, @RequestParam("memberAddress") String memberAddress
			, @RequestParam("memberHobby") String memberHobby
			, RedirectAttributes redirect
			, Model model) {
		try {
			Member member = new Member(memberId, memberPw, memberEmail, memberPhone, memberAddress, memberHobby);
			int result = service.updateMember(member);
			if(result > 0) {
				redirect.addAttribute("memberId", memberId);
				return "redirect:/member/mypage.do";
			}else {
				model.addAttribute("msg", "���� ������ �Ϸ���� �ʾҽ��ϴ�");
				return "common/errorPage";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
	public String removeMember(
			@RequestParam("memberId")String memberId
			, Model model) {
		try {
			int result = service.removeMember(memberId);
			if(result > 0) {
				// ����
				return "redirect:/member/logout.do";
			}else {
				// ����
				model.addAttribute("msg", "ȸ��Ż�� �Ϸ���� �ʾҽ��ϴ�.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			model.addAttribute("msg", e.getMessage()); // �ܼ�â�� �ߴ� �޽����� ���������� �߰���
			return "common/errorPage";
		}
		
	}

	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String memberLogin(
			HttpServletRequest request
			, @RequestParam("memberId") String memberId
			, @RequestParam("memberPw") String memberPw
			, Model model) {
		// SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?
		try {
			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberPw(memberPw);
			Member mOne = service.memberLoginCheck(member);
			if(mOne != null) {
				// �����ϸ� �α��� ������ �̵�
//				response.sendRedirect("/index.jsp");
//				model.addAttribute("member", mOne);
//				HttpSession session = request.getSession();
//				session.setAttribute("memberId", mOne.getMemberId());
//				session.setAttribute("memberName", mOne.getMemberName());
				
				// @SessionAttributes�� model�� �ִ� key���� Session�� �����
				// =======================================================
				model.addAttribute("memberId", mOne.getMemberId());
				model.addAttribute("memberName", mOne.getMemberName());
				return "redirect:/index.jsp";
				// Session�� ��� ���� model�� Key���� �߰����ִ� �ڵ�
				// indext.jsp�� ${}�� ���� ���� �Ͱ� �������
				// =====================================================
			}else {
				// �����ϸ� ���и޽��� ���
				model.addAttribute("msg", "�����Ͱ� ��ȸ���� �ʾҽ��ϴ�.");
				return "common.errorPage";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public String memberLogout(
		HttpServletRequest sessionPrev
		// SessionStatus�� �������� �̳����̼�(@SessionAttributes)�� �����Ǵ� ������ �����Ų��.
		// ���� �޼ҵ�� setComplete.
		, SessionStatus session
		, Model model) {
		if(session != null) {
			session.setComplete();
			if(session.isComplete()) {
				// ���� ���� ��ȿ�� üũ
			}
			return "redirect:/index.jsp";
		}else {
			model.addAttribute("msg", "�α׾ƿ��� �Ϸ����� ���߽��ϴ�.");
			return "common/eorrorPage";
		
		}
	}
	
	
	
	
	
	
	
	
	
	
}
