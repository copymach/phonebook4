package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {

//	필드
	
	@Autowired
	private PhoneDao phoneDao;
//	 = new PhoneDao();
	
//	생성자
//	메서드 gs
//	메서드 일반


	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("PhoneController > list () 시작 ");

//		MVC model view controll 패턴 3가지를 안섞이게 하는 방향

		// Dao에서 리스트를 가져온다
//		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList.toString());

//		컨트롤러가 DS 디스패처 서블릿에게 데이터를 보낸다 (Model - 리스트)
		model.addAttribute("personList", personList);

		// jsp 정보를 리턴한다 (view - 뷰)
		return "list";

	} // list 종료
	

	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("PhoneController > writeForm () ");

		return "writeForm";
	} // writeForm 종료

//	디스패쳐 서블릿에게 personVo를 묶어오라는 코드
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController > write() ");

		// 저장하는 로직
		phoneDao.personInsert(personVo);

//		값 잘 받는지 확인
		System.out.println("스프링이 가져온 personVo " + personVo);

//		리다이렉트 - 루트 /phone 빼먹지 말자  
//		return "redirect:/phone/list";

//		리다이렉트는 jsp 가필요없어서 뷰 리졸브를 거칠 필요가 없다 디스패처서블릿에서 바로 클라로 피드백
		return "redirect:list";
		
	} // write 종료
	
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete (@RequestParam("personId") int personId) {
		System.out.println("PhoneController > delete() ");
		
// 		저장하는 로직
//		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personDelete(personId);

//		상대경로로 리턴
		return "redirect:list";
	} // update 종료


	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @RequestParam("personId") int personId) {
		System.out.println("PhoneController > updateForm () ");
		
//		RequestParam 으로 id를 가져와서 vo에 넣자
		PersonVo personVo = phoneDao.personSelectOne(personId);
		
		model.addAttribute("personVo", personVo);
		
		return "updateForm";	
		
	} // updateForm 종료

	
//	디스패쳐 서블릿에게 personVo를 묶어오라는 코드
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController > update() ");
		
// 		저장하는 로직
		phoneDao.personUpdate(personVo);

//		값 잘 받는지 확인
		System.out.println("스프링이 가져온 personVo " + personVo);

//		리다이렉트 - 루트 /phone 빼먹지 말자  
//		return "redirect:/phone/list";

//		리다이렉트는 jsp 가필요없어서 뷰 리졸브를 거칠 필요가 없다 디스패처서블릿에서 바로 클라로 피드백
		return "redirect:list";
	} // update 종료


	
	/*
	// 쓰기 파라미터를 직접 묶어본 경우
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write2@RequestParam("name") String name,
						@RequestParam("hp") String hp,
						@RequestParam("company") String company) {
		System.out.println("PhoneController > write() ");

		// 리퀘스트에서 보낸 값 잘 받는지 확인 System.out.println(name + hp + company);

		PersonVo personVo = new PersonVo(name, hp, company);
		PhoneDao phoneDao = new PhoneDao();

		phoneDao.ContactsInput(personVo);

		return "/WEB-INF/views/...";
	}*/
						


	
	@RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
	public String test (@RequestParam(value="name") String name , 
						@RequestParam(value="age", required=false, defaultValue = "-1") int age) {
		System.out.println("PhoneController > test() ");
		
//		http://localhost:8088/phonebook3/phone/test?name=john&age=20
//		http://localhost:8088/phonebook3/phone/test?name=john
//		파라미트가 덜오면 에러 404 bed request -> @RequestParam("age") int age) 
//		
		
//		@RequestParam(value="age", required=false, defaultValue = "-1") int age 으로 없는 age 파라미터값을 -1로 변경
		
//		값 잘 받는지 확인
		System.out.println("스프링이 가져온 name age " + name + age);

//		상대경로로 리턴
		return "writeForm";
	} // test 종료
	
	
	
//	주소를 변수로 쓰기 
	@RequestMapping(value = "/{num}/view/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String view (@PathVariable("no") int no , @PathVariable("num") int num) {
		System.out.println("PhoneController > view() test ");
		
//		값 잘 받는지 확인
		System.out.println("num 스프링이 가져온 no 번 글" + no);

//		상대경로로 리턴
		return "writeForm";
	} // view 삭제 할때 숫자 하나만 넘기기도 할때 쓰기 좋다
	

//	없는 파라미터 처리 해보기
	@RequestMapping(value = "/view2", method = { RequestMethod.GET, RequestMethod.POST })
	public String view2 (@RequestParam(value="name") String name ,
						@RequestParam(value="age", required=false, defaultValue = "-1") int age) {
		System.out.println("PhoneController > view() test ");
		
//		값 잘 받는지 확인
		System.out.println("스프링이 가져온 name age " + name + age);

//		상대경로로 리턴
		return "writeForm";
	} // test 종료

	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String blog (@PathVariable(value="id") String id) {
		
		System.out.println(id+" 의 블로그 입니다.");
//		http://localhost:8088/phonebook3/phone/aaa 
//		aaa의 블로그입니다.

//		상대경로로 리턴
		return "writeForm";
	} // test 종료

	

}  // The end of this PhoneController
