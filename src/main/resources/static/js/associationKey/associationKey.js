$(function () {
    var keyManager = new KeyManager();
    keyManager.init();
});

function KeyManager() {

}
var tableObj = $("#table_list_2");
var rowId=0;
KeyManager.prototype={
    init : function () {
        this.bindToTableData();
        this.btnEvent();
    },
    btnEvent: function () {
        $("#queryBtn").click(function () {

        });
    },
    bindToTableData : function () {
        /* -----------------------------加载表数据  开始  -------------------------------- */
        $.jgrid.defaults.styleUI="Bootstrap";
        $("#table_list_2").jqGrid({
            height:434,autowidth:true, shrinkToFit:true,/*  autoScroll: false, *//*forceFit: true, */
            colNames:["关键字ID",'游戏ID',"联想关键字","生效时间","失效时间","失效状态","添加人","添加时间","更新人","更新时间","原因","操作 "],
            colModel:[{name:"id",index:"id",autowidth:true,align:"center"},
                {name:"gameId",index:"gameId",autowidth:true,align:"center"},
                {name:"keyName",index:"keyName",autowidth:true,align:"center"},
                {name:"fromDate",index:"fromDate",autowidth:true,align:"center"},
                {name:"endDate",index:"endDate",autowidth:true,align:"center"},
                {name:"status",index:"status",autowidth:true,align:"center"},
                {name:"createBy",index:"createBy",autowidth:true,align:"center"},
                {name:"createTime",index:"createTime",autowidth:true,align:"center"},
                {name:"updateBy",index:"updateBy",autowidth:true,align:"center"},
                {name:"updateTime",index:"updateTime",autowidth:true,align:"center"},
                {name:"cause",index:"cause",autowidth:true,align:"center"},
                {name:"edit",index:"edit",autowidth:true,align:"center"}
            ],
            pager:"#pager_list_2",
            viewrecords:true,hidegrid:false,
            url:"/associationKeys/list",
            datatype:'json',
            rownumbers: true,
            multiselect: true,//复选框
            rowNum : 10,
            rowList : [ 10, 15,30 ],
            grouping : false,// 是否分组,默认为false
            subGrid: true,// (1)开启子表格支持
            //子表格的id；当子表格展开的时候，在主表格中会创建一个div元素用来容纳子表格，subgrid_id就是这个div的id
            subGridRowExpanded: function (subgrid_id, row_id) {//子表格容器的id和需要展开子表格的行id
                bindSubGrid(subgrid_id, row_id);
            },
            onCellSelect: function (rowid, index, contents, event) {
                //onCell();//是一个控制按钮显示隐藏的方法
            },
            jsonReader: {
                root:"data.content",
                page:"data.pageable.pageNumber",
                total:"data.totalElements",          //   很重要 定义了 后台分页参数的名字。
                records:"pageable.pageSize",
                repeatitems:false,
                id : "id"
            },
            gridComplete: function () {         // 数据加载完成后 添加 采购按钮
                var ids = jQuery("#table_list_2").jqGrid('getDataIDs');
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    var editBtn = "<button  style='color:#f60' onclick='purchase("+ id +")' >采购</button>";
                    jQuery("#table_list_2").jqGrid('setRowData', ids[i], { edit: editBtn});
                }
            }
        });

        $("#queryBtn").trigger("click");
    }
}

function bindSubGrid(subgrid_id, collectLineId) {
    //onCell();
    var keyId = $("#table_list_2").jqGrid('getRowData', collectLineId).id;
    var status = $("#table_list_2").jqGrid('getRowData', collectLineId).StatusName;
    var subgrid_table_id;
    subgrid_table_id = subgrid_id + "_t";   // (3)根据subgrid_id定义对应的子表格的table的id
    var subgrid_pager_id;
    subgrid_pager_id = subgrid_id + "_pgr"  // (4)根据subgrid_id定义对应的子表格的pager的id
    // (5)动态添加子报表的table和pager
    $("#" + subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+subgrid_pager_id+"' class='scroll'></div>");
    $("#" + subgrid_id).html("<table id='" + subgrid_table_id + "' class='scroll'></table>");//这里是添加子表格div,id为subgrid_table_id

    $("#" + subgrid_table_id).jqGrid(
        {
            url:"/associationKeys/games/list?keyId="+keyId,
            datatype: "json",
            height: "100%",
            colNames: ['id','keyId','游戏名称','游戏ID','生效时间','失效时间','添加人','添加时间','更新人','更新时间','干预位置','失效状态','原因'],
            colModel: [
                { name: 'id', hidden: true },
                { name: 'keyId', hidden: true },
                { name: 'gameName', width: 80, align: 'left' },
                { name: 'gameId', width: 80, align: 'left',hidden:true},
                { name: 'fromDate', width: 100, align: 'left' },
                { name: 'endDate', width: 100, align: 'left'},
                { name: 'createBy', width: 90, align: 'left'},
                { name: 'createTime', width: 100, align: 'left'},
                { name: 'updateBy', width: 90, align: 'left'},
                { name: 'updateTime', width: 100, align: 'left' },
                { name: 'priority', width: 55, align: 'left' },
                { name: 'status', width: 55, align: 'left' },
                { name: 'cause', width: 150, align: 'left' }
            ],
            rowNum : 10,
            rowList : [ 10, 15,30 ],
            mtype: "post",
            viewrecord: true,
            jsonReader: {
                root:"data.content",
                page:"data.pageable.pageNumber",
                total:"data.totalElements",          //   很重要 定义了 后台分页参数的名字。
                records:"pageable.pageSize",
                repeatitems:false,
                id : "id"
            }
        });
}
var gridHelper = new GridHelper();
/**
 * 打开导入页面
 */
function openImportExcel() {
    $('#mainModal').modal('show');
}

/**
 * 上移
 * @param up
 */
function operateWithOneRowById(callback) {
    var id=jQuery('#table_list_2').jqGrid('getGridParam','selrow');

    var selected = tableObj.jqGrid('getGridParam', 'selrow');
    if (selected == null) {
        alert("请用鼠标点击选择一行后再执行操作!");
        return;
    }
    return callback(selected);
}


function up(selected) {
    if (selected == 1) return;
    else {
        gridHelper.moveRow("up", tableObj);
    }
}

function down(selected) {
    gridHelper.moveRow("down", tableObj);
}

function GridHelper() {
    
}

GridHelper.prototype={
    //移动一行
    this:moveRow = function(moveMethod, grid) {
        if (grid) tableObj = grid;
        var id;
        // if(selRow) id=selRow;
        // else id = getSelRow();
        id = this.getSelRow();
        tableObj.restoreRow(id);
        if (id == null) return;
        var targetId = this.getTargetId(id, moveMethod)
        if (targetId == -1) return;

        var temp1 = tableObj.getRowData(id);
        var temp2 = tableObj.getRowData(targetId);
        //对调行号
        var tempRn = temp1.rn;
        temp1.rn = temp2.rn;
        temp2.rn = tempRn;
        //对调数据
        tableObj.setRowData(id, temp2);
        tableObj.setRowData(targetId, temp1);
        tableObj.setSelection(targetId);
    },
    this:getTargetId = function(selId, method, grid) {
        if (grid) tableObj = grid;
        var ids = tableObj.getDataIDs();
        for (var i = 0; i < ids.length; i++) {
            if (selId == ids[i] && method == "up") {
                if (i == 0) return -1;
                else return ids[i - 1];
            }
            if (selId == ids[i] && method == "down") {
                if (i == ids.length - 1) return -1;
                else return ids[i + 1];
            }
        }
    }
};