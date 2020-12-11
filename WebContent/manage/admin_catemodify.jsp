<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>>
<%@ include file="admin_menu.jsp" %>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/MonkeyShop/manage/admin_index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/MonkeyShop/manage/admin_cateselect">分类管理</a><span class="crumb-step">&gt;</span><span>修改分类</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="/MonkeyShop/manage/admin_docateupdate" method="post" id="myform" name="myform" >
                	<input type="hidden" name="id" value="${cate.CATE_ID }">
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th><i class="require-red">*</i>父级分类：</th>
                                <td>
                                    <select class="common-text required" name="parentId" >
                                    	<option value="0">根分类</option>
                                    	<c:forEach var="pcate" items="${catelist }">
                                    		<c:if test="${pcate.CATE_PARENT_ID==0 }">
                                    			<c:if test="${cate.CATE_PARENT_ID==pcate.CATE_ID }">
                                    				<option value="${pcate.CATE_ID }" selected="selected">${pcate.CATE_NAME }</option>
                                    			</c:if>
                                    			<c:if test="${cate.CATE_PARENT_ID!=pcate.CATE_ID }">
                                    				<option value="${pcate.CATE_ID }">${pcate.CATE_NAME }</option>
                                    			</c:if>
                                    		</c:if>
                                    	</c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>分类名称：</th>
                                <td>
                                    <input class="common-text required" id="title" name="className" size="50" value="${cate.CATE_NAME }" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="修改" type="submit">
                                    <input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
        </div>

    </div>
    <!--/main-->
</div>
</body>
</html>