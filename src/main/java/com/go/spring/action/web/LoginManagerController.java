package com.go.spring.action.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.go.spring.action.bean.TsMenuItemResponseBean;
import com.go.spring.action.bean.TsMenuResponseBean;
import com.go.spring.action.bean.TsUserLoginRequestBean;
import com.go.spring.action.bean.TsUserLoginResponseBean;
import com.go.spring.action.service.MenuService;
import com.go.spring.action.service.TsUserService;
import com.go.spring.util.MD5;

@Controller
@RequestMapping("/login.action")
public class LoginManagerController {
	protected static Logger logger = Logger.getLogger(LoginManagerController.class);// 鏃ュ織
	@Autowired
	public TsUserService tsUserService;
	@Autowired
	public MenuService MenuService;

	@RequestMapping(params = "method=toLoginPage")
	public String toLoginPage(HttpServletRequest request) throws IOException {
		
		return "jsp/login";
	}
	
	@RequestMapping(params = "method=toWelcomePage")
	public String toWelcomePage(HttpServletRequest request, Model model,HttpSession session) {
		if(request.getParameter("username")!=null){
		String userName = request.getParameter("username");
		String userPasswd = request.getParameter("password");
		try {
			userPasswd = MD5.EncoderByMd5(userPasswd);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		TsUserLoginRequestBean tsUserReq = new TsUserLoginRequestBean();
		tsUserReq.setUserName(userName);
		tsUserReq.setUserPasswd(userPasswd);
		
		
		TsUserLoginResponseBean tsUserRes = new TsUserLoginResponseBean();
		tsUserRes = tsUserService.getOneTsUser(tsUserReq);
		if ( tsUserRes!= null) {
			model.addAttribute("userName", userName);
			session.setAttribute("id", tsUserRes.getId());
			session.setAttribute("username", userName);
			session.setAttribute("password", userPasswd);
			session.setAttribute("nickName", tsUserRes.getNickName());
			
			List<TsMenuResponseBean> menufather = MenuService.queryFatherMenu();
			List<TsMenuItemResponseBean> menuson = MenuService.queryAllMenu();
			
			
			for(int i=0;i<menufather.size();i++){
				String menu_id=(menufather.get(i)).getMenuId();
				ArrayList<TsMenuItemResponseBean> arrayList = new ArrayList<TsMenuItemResponseBean>();
				for(int j=0;j<menuson.size();j++){
					String parent_node=(menuson.get(j)).getItemParent();
					if(parent_node!=null&&parent_node.equals(menu_id)){
						arrayList.add(menuson.get(j));
					}
				}
				(menufather.get(i)).setItem(arrayList);
			}
			session.setAttribute("menufather", menufather);
			
			return "jsp/welcome";
		} else {
			return "jsp/login";
		}
		}else{
			return "jsp/login";
		}
	}
	
	@RequestMapping(params = "method=logOut")
	public String logOut(HttpServletRequest request, Model model,HttpSession session) {
		
		session.removeAttribute("id");
		session.removeAttribute("username");
		session.removeAttribute("password");
		session.removeAttribute("nickName");
		session.removeAttribute("menufather");
		
		return "jsp/login";
	}
	@RequestMapping(params = "method=toJstreePage")
	public String toTestPage(HttpServletRequest request, Model model,HttpSession session) {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		session.setAttribute("list", list);
		return "jsp/jsTree";
	}
	
}


