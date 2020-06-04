var ID_NAME_MAP = new Map();
$(function () {
    $.ajax({
        type: 'GET',
        url: "/sys/datasource/all",
        datatype: "json",
        contentType: "application/json",
        success: function (res) {
            if (res.code == 0) {
                if (res.list && res.list.length > 0) {
                    let list = res.list;

                    initDropdownMenu(list);
                } else {
                    alert("请先进行生成代码的数据库配置");
                }
            } else {
                errorTip("查询数据库配置异常");
            }
        }
    });

});

function initData() {
    $("#jqGrid").jqGrid({
        url: 'sys/generator/list',
        datatype: "json",
        colModel: [
            { label: '表名', name: 'tableName', width: 100, key: true },
            { label: 'Engine', name: 'engine', width: 70},
            { label: '表备注', name: 'tableComment', width: 100 },
            { label: '创建时间', name: 'createTime', width: 100 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50,100,200],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
    $("#jqGrid").trigger("reloadGrid");
}

function initDropdownMenu(list) {
    // 初始化
    var initObjKey = '';
    var html = '';
    for (let listKey in list) {
        let data = list[listKey];
        ID_NAME_MAP.set(data.name, data);
        if (listKey == 0) {
            initObjKey = data.name;
        }
        html += '<li><a id="' + data.name + '" href="#" onclick="vm.changeDb($(this).text())">' + data.name + '</a></li>';
    }
    $('.dropdown-menu').append(html);
    $('#' + initObjKey).click();

}

function selelctDataSource(dataSource) {
    $.ajax({
        type: 'GET',
        url: "/sys/change/current?id=" + dataSource.id,
        datatype: "json",
        contentType: "application/json",
        success: function (res) {
            console.log(res);
            if (res.code == 0) {
                initData();
            } else {
                errorTip(res.msg);
            }
        }
    });
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			tableName: null
		}
	},
	methods: {
		query: function () {
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'tableName': vm.q.tableName},
                page:1 
            }).trigger("reloadGrid");
		},
		generator: function() {
            var tableNames = getSelectedRows();
            if(tableNames == null){
                return ;
            }
            location.href = "sys/generator/code?tables=" + tableNames.join();
		},
        changeDb: function (name) {
		    $('.dropdown-menu').children('li').removeClass('active');
            $('#' + name).parent().addClass('active')
            $('.dropdown-toggle').html(name + '<span class="caret">');
		    let data = ID_NAME_MAP.get(name);
            selelctDataSource(data);
            // 首先销毁列表元素
            //vm.query();
        }
	}
});

