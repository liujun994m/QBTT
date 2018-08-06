<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>区域管理</title>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquery.treeview.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/toolbar.css" />
<script
	src="${pageContext.request.contextPath}/script/tree/jquery.treeview.js"></script>
<script
	src="${pageContext.request.contextPath}/script/tree/jquery.cookie.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<script>

	$(document).ready(function() {
		$("#tree").treeview();
		$("li a").click(function() {
			$("li a").removeClass('active2');
			$(this).addClass('active2');
		});
		/* 导航栏，其实是回到主页面，左右都会刷新页面*/
		$(".menu").click(function() {
			document.home_left.action = "home_left.action";
			document.home_left.target = "left";
			document.home_left.submit();
			document.home_right.action = "home_right.action";
			document.home_right.target = "right";
			document.home_right.submit();

		});
	});
	function a() {
		document.device_right.action="device_right.action";
		document.device_right.target="right";
		document.device_right.submit();
		return true;
	}
</script>
<style type="text/css">
body {
	background: #A9CCF8;
}
</style>

</head>
<body onload="a()">
	<div id="container">
		<div class="menu">
			<i class="fa fa-navicon"></i>&nbsp;&nbsp;&nbsp;&nbsp;导航菜单
		</div>

 <div style="border: 1px dashed #99FF99;width:100%;height:50px;padding-bottom:20px;">
       &nbsp;&nbsp; 说明：<br>
		&nbsp;&nbsp;	<i class="fa fa-square-o"></i>--表示区域<br>
	   &nbsp;&nbsp;     <i class="fa fa-building-o"></i>--表示区域下的集中器	
		</div>
		<div class="tree">
			<ul id="tree" class="filetree">
				<s:iterator value="#areaList" id="area" status="fwq">
					<li>
						<div id="contentid">
							<s:a action="device_area?mareaId=%{id}" class="os"
								 target="right"><i class="fa  fa-square-o"></i> ${name}</s:a>
						</div> <!-- 区域的树形结构 -->
						<ul>
							<s:iterator value="children">
								<li>
									<div id="contentid">
										<s:a action="device_area?mareaId=%{id}" class="os"
											 target="right"><i class="fa  fa-square-o"></i> ${name}</s:a>
									</div> <!-- 区域下的设备 -->
									<ul>
										<s:iterator value="centers">
											<li>
												<div id="contentid">
													<s:a action="device_center?centerId=%{id}" class="os"
														 target="right"><i class="fa  fa-building-o"></i> ${name}</s:a>
												</div>

												<ul>
													<s:iterator value="collections">
														<li>
															<div id="contentid">
																<s:a action="device_collection?collectionId=%{id}"
																	class="os"  target="right"> ${name}</s:a>
															</div>
														</li>
													</s:iterator>
												</ul>
											</li>
										</s:iterator>
									</ul> <!-- 子区域的树形结构 -->
									<ul>
										<s:iterator value="children">
											<li>
												<div id="contentid">
													<s:a action="device_area?mareaId=%{id}" class="os"
														 target="right"> ${name}</s:a>
												</div>

												<ul>
													<!-- 区域下的设备 -->
													<s:iterator value="centers">
														<li>
															<div id="contentid">
																<s:a action="device_center?centerId=%{id}" class="os"
																	 target="right"> ${name}</s:a>
															</div>

															<ul>
																<s:iterator value="collections">
																	<li>
																		<div id="contentid">
																			<s:a action="device_collection?collectionId=%{id}"
																				class="os"  target="right"> ${name}</s:a>
																		</div>
																	</li>
																</s:iterator>
															</ul>
														</li>
													</s:iterator>
												</ul> <!-- 区域下的子区域 -->
												<ul>
													<s:iterator value="children">
														<li>
															<div id="contentid">
																<s:a action="device_area?mareaId=%{id}" class="os"
																	 target="right"> ${name}</s:a>
															</div>

															<ul>
																<!-- 区域下的设备 -->
																<s:iterator value="centers">
																	<li>
																		<div id="contentid">
																			<s:a action="device_center?centerId=%{id}" class="os"
																				 target="right"> ${name}</s:a>
																		</div>

																		<ul>
																			<s:iterator value="collections">
																				<li>
																					<div id="contentid">
																						<s:a action="device_collection?collectionId=%{id}"
																							class="os"  target="right"> ${name}</s:a>
																					</div>
																				</li>
																			</s:iterator>
																		</ul>
																	</li>
																</s:iterator>
															</ul>
														</li>
													</s:iterator>
												</ul>
											</li>
										</s:iterator>
									</ul>
								</li>
							</s:iterator>
						</ul> <!-- 设备的树形结构 -->

						<ul>
							<!-- 区域下的设备 -->
							<s:iterator value="centers">
								<li>
									<div id="contentid">
										<s:a action="device_center?centerId=%{id}" class="os"
											 target="right"> ${name}</s:a>
									</div>

									<ul>
										<s:iterator value="collections">
											<li>
												<div id="contentid">
													<s:a action="device_collection?collectionId=%{id}"
														class="os"  target="right"> ${name}</s:a>
												</div>
											</li>
										</s:iterator>
									</ul>
								</li>
							</s:iterator>
						</ul>
				</s:iterator>
			</ul>
		</div>
	</div>
	<s:form name="home_right"></s:form>
	<s:form name="home_left"></s:form>
	<s:form name="device_right"></s:form>
</body>
</html>

