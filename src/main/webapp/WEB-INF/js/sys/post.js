$(function () {
    init();
});

function init() {
    //icheck
    $('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
    });

    //icheck值改变的事件
    $(".i-checks").on("ifChanged", function () {
        vm.post.status = $("input[name='status']:checked").val();
    });
}

var vm = new Vue({
    el: "#app",
    data: {
        showList: true,
        post: {
            status: 0
        },
        postStateList: []
    },
    methods: {
        add() {
            //控制显示
            vm.showList = false;

            vm.post['_method'] = 'POST';
            this.handleSubmit();
        },
        edit(id) {
            //控制显示
            vm.showList = false;
            //绑定user对象,根据id从数据查询信息
            $.ajax({
                url: "/sys/post/" + id,
                type: "get",
                dataType: "json",
                success: function (res) {
                    if (res.status == 200) {
                        vm.post = res.data;
                        vm.post['_method'] = 'PUT';

                        //提交处理(提交前的验证)
                        vm.handleSubmit();
                    }
                },
                error: function () {
                    $.modal.alertError("服务器正忙...")
                }
            });

        },
        queryPost() {
            var options = {};
            //options.url = "data/sys/sys/post.json";
            options.url = "/sys/post";
            options.sortOrder = "desc";
            options.sortName = "postId";
            options.id = "postId"; //主键
            options.columns = [
                {
                    checkbox: true
                },
                {
                    field: 'postId',
                    title: '岗位编号'
                },
                {
                    field: 'postCode',
                    title: '岗位编码',
                    sortable: true
                },
                {
                    field: 'postName',
                    title: '岗位名称',
                    sortable: true
                },
                {
                    field: 'postSort',
                    title: '显示顺序',
                    sortable: true
                },
                {
                    field: 'status',
                    title: '状态',
                    align: 'center',
                    formatter: function (value, row, index) {
                        if (value == '0')
                            return '<span class="badge badge-primary">正常</span>';
                        else
                            return '<span class="badge badge-danger">停用</span>';
                    }
                },
                {
                    field: 'createTime',
                    title: '创建时间',
                    sortable: true
                },
                {
                    title: '操作',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var actions = [];
                        actions.push('<a class="btn btn-success btn-xs" href="javascript:void(0)" onclick="vm.edit(\'' + row.postId + '\')"><i class="fa fa-edit"></i>编辑</a>');
                        actions.push(' <a class="btn btn-danger btn-xs" href="javascript:void(0)" onclick="$.operate.remove(\'' + row.postId + '\')"><i class="fa fa-remove"></i>删除</a>');
                        return actions.join("");
                    }
                }];

            $.table.init(options);
        },
        back() {
            vm.showEdit = false;
            vm.showList = true;
            vm.post = {
                status: 0
            }
            $("#bootstrap-table").bootstrapTable("refresh");

            //重置验证
            $('#form-post').data('bootstrapValidator').resetForm(true)
        },
        handleSubmit() {
            $("#form-post").bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    postName: {//字段名
                        validators: {
                            notEmpty: {//不能为空
                                message: '请输入岗位名称'
                            },stringLength:{
                                min:4,
                                max:8,
                                message:"字符串长度必须在4到8位"
                            }
                        }
                    },
                    postId: {//字段名
                        validators: {
                            notEmpty: {//不能为空
                                message: '请输入岗位编号'
                            }
                        }
                    },
                    postCode: {//字段名
                        validators: {
                            notEmpty: {//不能为空
                                message: '请输入岗位编码'
                            }
                        }
                    }
                },
                // remote:{//ajax验证，获取一个json数据（{'valid':true or false}）
                //     url:'',     //验证地址
                //     message:'用户已存在',     //提示信息
                //     type:'POST',        //请求方式
                //
                // },
                submitHandler: function (validator, form, submitButton) {
                    vm.saveOrUpdate();
                }
            })
            /*.on('success.form.bv', function (e) {
                vm.saveOrUpdate();
                return false;
            });*/
        },
        saveOrUpdate() {
            var url = "/sys/post";
            $.ajax({
                url: url,
                type: $.common.isEmpty(vm.post['_method']) ? "post" : vm.post['_method'],
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(vm.post),
                success: function (res) {
                    if (res.status == 200) {
                        $.modal.msgSuccess(res.msg);
                        vm.back();
                    } else {
                        $.modal.msgError(res.msg);
                    }
                },
                error: function (error) {
                    $.modal.alertError("服务器正忙，请稍后再试...");
                }
            });
        }
    },
    mounted() {
        //先查询岗位的状态
        var params =
        {
            method: "dictDataByType",
            type: "sys_normal_disable"
        };

        axios.get("/sys/dictData/byType", {params:params}).then(function (res) {
            vm.postStateList = res.data.data;
            vm.queryPost();
        }).catch(function (error) {
            $.modal.msgError('查询用户状态失败');
        });

        // this.queryPost();
    },
    updated() {
        init();
    }



});
