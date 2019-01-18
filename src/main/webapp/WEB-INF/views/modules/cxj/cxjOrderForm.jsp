<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生产通知单信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cxj/cxjOrder/">生产通知单信息列表</a></li>
		<li class="active"><a href="${ctx}/cxj/cxjOrder/form?id=${cxjOrder.id}">生产通知单信息<shiro:hasPermission name="cxj:cxjOrder:edit">${not empty cxjOrder.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cxj:cxjOrder:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cxjOrder" action="${ctx}/cxj/cxjOrder/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">客户：</label>
			<div class="controls">
				<form:input path="customer" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发货时间：</label>
			<div class="controls">
				<input name="logisticsCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cxjOrder.logisticsCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发货状态：</label>
			<div class="controls">
				<form:radiobuttons path="logisticsStatus" items="${fns:getDictList('logistics_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品要求：</label>
			<div class="controls">
				<form:textarea path="requirement" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他功能：</label>
			<div class="controls">
				<form:textarea path="otherFunctions" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">通知者集合：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="notice" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
        <%--通知集合--%>
        <div class="control-group">
            <label class="control-label">通知人集合：</label>
            <div class="controls">
                <sys:treeselect id="oaNotifyRecord" name="notice" value="${cxjOrder.notice}" labelName="noticeName" labelValue="${cxjOrder.noticeName}"
                                title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <%--通知集合 end--%>

		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">订单明细表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>规格</th>
								<th>单位</th>
								<th>数量</th>
								<th>发货数量</th>
								<th>备注信息</th>
								<shiro:hasPermission name="cxj:cxjOrder:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="cxjOrderDetailedList">
						</tbody>
						<shiro:hasPermission name="cxj:cxjOrder:edit"><tfoot>
							<tr><td colspan="7"><a href="javascript:" onclick="addRow('#cxjOrderDetailedList', cxjOrderDetailedRowIdx, cxjOrderDetailedTpl);cxjOrderDetailedRowIdx = cxjOrderDetailedRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="cxjOrderDetailedTpl">//<!--
						<tr id="cxjOrderDetailedList{{idx}}">
							<td class="hide">
								<input id="cxjOrderDetailedList{{idx}}_id" name="cxjOrderDetailedList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="cxjOrderDetailedList{{idx}}_delFlag" name="cxjOrderDetailedList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="cxjOrderDetailedList{{idx}}_standard" name="cxjOrderDetailedList[{{idx}}].standard" type="text" value="{{row.standard}}" maxlength="50" class="input-small "/>
							</td>
							<td>
								<input id="cxjOrderDetailedList{{idx}}_company" name="cxjOrderDetailedList[{{idx}}].company" type="text" value="{{row.company}}" maxlength="50" class="input-small "/>
							</td>
							<td>
								<input id="cxjOrderDetailedList{{idx}}_number" name="cxjOrderDetailedList[{{idx}}].number" type="text" value="{{row.number}}" maxlength="11" class="input-small  digits"/>
							</td>
							<td>
								<input id="cxjOrderDetailedList{{idx}}_goodNumber" name="cxjOrderDetailedList[{{idx}}].goodNumber" type="text" value="{{row.goodNumber}}" maxlength="11" class="input-small  digits"/>
							</td>
							<td>
								<textarea id="cxjOrderDetailedList{{idx}}_remarks" name="cxjOrderDetailedList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="cxj:cxjOrder:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#cxjOrderDetailedList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var cxjOrderDetailedRowIdx = 0, cxjOrderDetailedTpl = $("#cxjOrderDetailedTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(cxjOrder.cxjOrderDetailedList)};
							for (var i=0; i<data.length; i++){
								addRow('#cxjOrderDetailedList', cxjOrderDetailedRowIdx, cxjOrderDetailedTpl, data[i]);
								cxjOrderDetailedRowIdx = cxjOrderDetailedRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="cxj:cxjOrder:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>