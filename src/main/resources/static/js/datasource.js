$(function () {
    validate();
    $("#jqGrid").jqGrid({
        url: 'sys/datasource/list',
        datatype: "json",
        colModel: [
            { label: '数据库类型', name: 'name', width: 100, key: true },
            { label: '驱动', name: 'driverClassName', width: 70},
            { label: '链接地址', name: 'url', width: 100 },
            { label: '用户名', name: 'username', width: 100 },
            { label: '密码', name: 'password', width: 100 }
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
});

function validate() {
    $('#form').bootstrapValidator({
        fields: {  /*验证规则*/
            name: {  //验证email项
                validators: {
                    notEmpty: {  //非空验证：提示消息
                        message: '对不起，邮箱地址不能为空！'
                    }
                }
            },
            driverClassName: {  //验证password项
                message:'密码无效',
                validators: {
                    notEmpty: {  //非空验证：提示消息
                        message: '密码不能为空'
                    }
                }
            },
        }
    });
}

var vm = new Vue({
    el:'#dataapp',
    data:{
        q:{
            tableName: null
        },
        form: {
            name: null,
            driverClassName: null,
            url: null,
            username: null,
            password: null,
        }
    },
    methods: {
        query: function () {
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'tableName': vm.q.tableName},
                page:1
            }).trigger("reloadGrid");
        },
        showOrCloseModal: function() {
            $('#myModal').modal('toggle');
        },
        saveBtn: function () {
            $.ajax({
                type: 'POST',
                url: "/sys/datasource/saveOrUpdate",
                data: JSON.stringify(this.form),
                datatype: "json",
                contentType: "application/json",
                success: function (res) {
                    console.log(res);
                    if (res.code == 0) {
                        vm.query();
                        sucessTip("新增成功");
                        vm.showOrCloseModal();
                    } else {
                        errorTip(res.msg);
                    }
                },
            })
        }
    }
});

