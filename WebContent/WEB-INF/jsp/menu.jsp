<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<s:if test="#session.user == null">
				null
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath }/ssh_shop/user_toLoginPage.action">登录</a>|
				</li>
				<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="${pageContext.request.contextPath }/user_toRegisterPage.action">注册</a>|
				</li>
				</s:if>
				<s:else>
				<li>
					<s:property value="#session.user.username"/>
				</li>
				<li>
					<a href="${pageContext.request.contextPath }/order_findByUid?uid=<s:property value="#session.user.uid"/>&page=1">我的订单</a>
				</li>
				<li><a href="${pageContext.request.contextPath }/user_quit.action">[退出]</a>|
				</li>
				xianshi
				
				</s:else>
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a href="${pageContext.request.contextPath }/cart_showCart.action">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>
	
	<div class="span24">
		<ul class="mainNav">
					<li><a href="${pageContext.request.contextPath }/index.action">首页</a></li>
					<%-- 首页中一级分类展示 --%>
					<s:iterator value="#session.cList" var="c">
						<li><a href="${pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a></li>
					</s:iterator>
					
		</ul>
	</div>