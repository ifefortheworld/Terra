<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

							<c:forEach var="distribution" items="${distributions}">
							<tr id="${distribution.no}">
								<td>${distribution.no}</td>
								<td>${distribution.orderForm.no}</td>
								<td>${distribution.sendOutTime}</td>
								<td>${distribution.goBackTime}</td>
								<td>${distribution.trackBackRegistration.payment}</td>
								<td>${distribution.track.licenseNumber}</td>
								<td>${distribution.driver.name}</td>
								<td>${distribution.orderForm.client.shortName}</td>
								<td>${distribution.orderForm.goods}</td>
								<td>${distribution.orderForm.unit}</td>
								<td>${distribution.sendQuantity}</td>
								<td>
									<c:if test="${distribution.status == 0}">未出车</c:if>
									<c:if test="${distribution.status == 1}">配送中</c:if>
									<c:if test="${distribution.status == 2}">已回车</c:if>
								</td>
							</tr>
							</c:forEach>