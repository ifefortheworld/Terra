<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

							<c:forEach var="record" items="${inRecords}">
							<tr id="${record.id}">
								<td>${record.no}</td>
								<td>${record.date}</td>
								<td>${record.client.shortName}</td>
								<td>${record.track.licenseNumber}</td>
								<td>${record.warehouse.shortName}</td>
								<td>${record.good} ${record.quantity} ${record.unit}</td>
								<td>${record.handlingCharge}</td>
								<td>${record.storageCharge}</td>
								<td>${record.transportationCharge}</td>
								<td>${record.otherCharge}</td>
								<td>${record.handlingCharge +record.storageCharge
									+record.transportationCharge +record.otherCharge}</td>
								<%-- <td>${record.note}</td> --%>
							</tr>
							</c:forEach>