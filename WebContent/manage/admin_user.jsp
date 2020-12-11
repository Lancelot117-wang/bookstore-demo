<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>>
<%@ include file="admin_menu.jsp" %>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/MonkeyShop/manage/admin_index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">用户管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="/MonkeyShop/manage/admin_douserselect" method="get">
                    <table class="search-tab">
                        <tr>
                            <th width="70">关键字:</th>
                            <td><input class="common-text" placeholder="关键字" name="keywords" value="${param.keywords}" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form action="/MonkeyShop/manage/admin_douserdelete" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="admin_useradd.jsp"><i class="icon-font"></i>新增用户</a>
                        <a id="batchDel" href="javascript:deletemore('确定删除这些用户吗','myform')"><i class="icon-font"></i>批量删除</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="5%"><input class="allChoose" name="" onclick="selectall(this)" type="checkbox"></th>
                            <th>ID</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>EMAIL</th>
                            <th>手机</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach var="u" items="${userlist}">
                        <tr>
                            <td class="tc"><input name="id[]" value="${u.USER_ID}" type="checkbox"></td>
                            <td>${u.USER_ID}</td>
                            <td>${u.USER_NAME}</td>
                            <td>${u.USER_SEX=='T'?'男':'女'}</td>
                            <td>${u.USER_EMAIL}</td>
                            <td>${u.USER_MOBILE}</td>
                            
                            <td>
                                <a class="link-update" href="admin_touserupdate?id=${u.USER_ID}&cpage=${cpage}">修改</a>
                                <c:if test="${u.USER_STATUS==1}">
                                    <a class="link-del" href="javascript:Delete('确定删除用户${u.USER_NAME}吗','admin_douserdelete?id=${u.USER_ID}&cpage=${cpage}')">删除</a>
                                </c:if>
                            </td>
                        </tr>
                        </c:forEach>
                        
                        <script>
                            function Delete(mess, url) {
                            	if(confirm(mess)){
                            		location.href=url;
                            	}
                            }
                            
                            function selectall(o) {
                            	var a = document.getElementsByName('id[]');
                            	
                            	for(var i=0; i<a.length; i++){
                            		a[i].checked = o.checked;
                            	}
                            }
                            
                            function deletemore(mess, formname) {
                            	if(confirm(mess)){
                            		var form = document.getElementById(formname);
                            		form.submit();
                            	}
                            }
                        </script>
                        
                    </table>
                    <div class="list-page"> 
                                           共 ${tsum} 条记录，当前 ${cpage}/${tpage} 页
                        <c:if test="${cpage>1}">
                            <a href="/MonkeyShop/manage/admin_douserselect?cp=1${searchParams}">首页</a>
                            <a href="/MonkeyShop/manage/admin_douserselect?cp=${cpage-1}${searchParams}">上一页</a>
                        </c:if>
                        <c:if test="${cpage<tpage}">
                            <a href="/MonkeyShop/manage/admin_douserselect?cp=${cpage+1}${searchParams}">下一页</a>
                            <a href="/MonkeyShop/manage/admin_douserselect?cp=${tpage}${searchParams}">尾页</a>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>