<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

							<c:forEach var="order" items="${orders}">
								<tr id="${order.id}">
									<td>${order.no}</td>
									<td>${order.orderTime}</td>
									<td>${order.requireDeliveryTime}</td>
									<td>${order.client.shortName}</td>
									<td>${order.loadingAddress}</td>
									<td>${order.unloadingAddress}</td>
									<td>${order.goods}</td>
									<td>${order.unit}</td>
									<td>${order.quantity}</td>
									<td>${order.sendedQuantity}</td>
									<td>${order.cost}</td>
									<td>${order.settlementWay}</td>
									<td>${order.orderMaker.trueName}</td>
									<c:if test="${order.finished}">
										<td>已完成</td>
									</c:if>
									<c:if test="${!order.finished}">
										<td>未完成</td>
									</c:if>
								</tr>
							</c:forEach>