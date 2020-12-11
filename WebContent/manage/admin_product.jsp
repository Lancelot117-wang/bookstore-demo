<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>>
<%@ include file="admin_menu.jsp" %>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/MonkeyShop/manage/admin_index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">产品管理</span></div>
        </div>
        <div class="result-wrap">
            <form action="/MonkeyShop/manage/admin_douserdelete" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="/MonkeyShop/manage/admin_toproductadd"><i class="icon-font"></i>新增产品</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="60%">
                        <tr>
                            <th>ID</th>
                            <th>产品</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach var="p" items="${plist}">
                            <tr>
                                <td>${p.PRODUCT_ID}</td>
                                <td><img src="../images/product/${p.PRODUCT_FILENAME}" width="80" height="80">
                                ${p.PRODUCT_NAME}
                                </td>
                                <td>
                                    <a class="link-update" href="admin_toproductupdate?id=${p.PRODUCT_ID } ">修改</a>
                                    <a class="link-del" href="javascript:Delete('确定删除产品${p.PRODUCT_NAME}吗','admin_doproductdel?id=${p.PRODUCT_ID}')">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        
                        <script>
                            function Delete(mess, url) {
                            	if(confirm(mess)){
                            		location.href=url;
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
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>