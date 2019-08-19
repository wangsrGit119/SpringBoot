<template>
    <section style="background-color: rgba(39,92,128,0.29)">
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="margin-left: 10px;text-align: left;margin-top:40px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model.trim="filters.keywords" placeholder="请输入查询关键字"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getInfoBykeys">查询</el-button>
                </el-form-item>
            </el-form>
        </el-col>


        <!--列表-->
        <el-table :data="orders" highlight-current-row v-loading="listLoading"  style="width: 100%;">
            <el-table-column prop="orderNum" width="200" label="订单编号">
            </el-table-column>
            <el-table-column prop="orderName" label="订单名称" width="150"  >
                <template slot-scope="scope">
                    <div style="text-align: center" v-html="getOrderNameFromDetail(scope,scope.row.orderDetail)"></div>
                </template>
            </el-table-column>
            <el-table-column prop="orderCount" label="订单数量" width="100"  >
                <template slot-scope="scope">
                    <div style="text-align: center" v-html="getGoodsCountFromDetail(scope,scope.row.orderDetail)"></div>
                </template>
            </el-table-column>
            <el-table-column prop="orderPrice" label="订单价格" width="100" >  <template slot-scope="scope">
                <div style="text-align: center" v-html="getGoodsPriceFromDetail(scope,scope.row.orderDetail)"></div>
            </template>
            </el-table-column>
            <el-table-column prop="orderTime" label="下单时间" width="150" sortable :formatter="dateFormat">
            </el-table-column>
            <el-table-column prop="receiptAddress" label="用户地址" width="150" >
            </el-table-column>
            <el-table-column prop="openId" label="用户ID" min-width="150" >
            </el-table-column>
            <el-table-column prop="nickName" label="用户名称" min-width="150" >
            </el-table-column>
            <el-table-column prop="orderStatus" label="订单状态" min-width="100" :formatter="statusFormat" >
                <template scope="scope">
                    <span style="color: blue;font-weight:bold;" v-if="scope.row.orderStatus=== 1">交易成功</span>
                    <span v-else style="color: red;font-weight:bold;">交易失败</span>
                </template>
            </el-table-column>
        </el-table>
        <!--分页-->
        <el-col :span="24" class="toolbar" style="text-align: right">
            <el-pagination background layout="total,prev, pager, next" @current-change="handleCurrentChange" :current-page="1" :page-size="pageSize" :total="total" style="float:right;">
            </el-pagination>
        </el-col>

    </section>
</template>

<script>
    import {  getOrderInfoBykeys } from '../../wechatApi/api';

    export default {
        name:'wechatOrder',
        data() {
            return {
                filters: {
                    keywords: '' //关键词
                },
                users: [],
                orders:[], //订单信息  所有
                total: 5,  //分页信息
                pageNum:0,//初始值为0
                pageSize:5, //初始每页大小
                currentPage:2,
                startTime:'', //开始时间
                endTime:'',//结束时间
                listLoading: false,//加载loading
                form: {
                    reason: '',//原因
                    desc: ''//描述
                }
            }
        },
        methods: {
            //状态显示转换
            statusFormat: function (row, column) {
                return row.orderStatus == 0 ? '等待接单' : row.orderStatus == 1 ? '已接单' : '交易失败';
            },
            //时间转换
            dateFormat:function(row,column){
                const daterc = row.orderTime;
                if(daterc!=null){
                    var date=new Date(daterc);
                    var year=date.getFullYear();
                    var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
                    var day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
                    var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
                    var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
                    var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
                    // 拼接
                    return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
                }
            },
            //换页
            handleCurrentChange(val) {
                this.listLoading = true;
                this.pageNum=val;
                this.getOrderInfoByParams();
            },
            // 根据关键词获取订单列表
            getInfoBykeys(val) {
                this.listLoading = true;
                this.getOrderInfoByParams();

            },
            //调用api
            getOrderInfoByParams(){
                this.listLoading = true;
                let requestParmams={"allStatus":[1,2],"endTime":this.endTime,"keys":this.filters.keywords,"pageNum":this.pageNum,"pageSize":this.pageSize,"startTime":this.startTime}
                let queryInfo={
                    queryInfo:JSON.stringify(requestParmams)
                };
                console.log(queryInfo);
                getOrderInfoBykeys(queryInfo).then((res)=>{
                    const  orderInfo=JSON.parse(res.data);
                    this.orders=orderInfo.list;
                    console.log(this.orders)
                    this.total=orderInfo.total;
                    this.listLoading = false;

                })

            },
            //  从订单详情中获取订单名称
            getOrderNameFromDetail(scope,value){//:function(val,column){
                let goodsName='';
                let goodsArray=JSON.parse(value);
                for(let i=0;i<goodsArray.length;i++){
                    goodsName+='<br/>'+JSON.parse(goodsArray[i]).goodsName;
                }
                return goodsName;
            },
            //订单详情中获取商品数量
            getGoodsCountFromDetail(scope,value){//:function(val,column){
                let goodsCount='';
                let goodsArray=JSON.parse(value);
                for(let i=0;i<goodsArray.length;i++){
                    goodsCount+='<br/>'+JSON.parse(goodsArray[i]).goodsCount;
                }
                return goodsCount;
            },
            //商品详情中获取商品价格
            getGoodsPriceFromDetail(scope,value){
                let goodsPrice='';
                let goodsArray=JSON.parse(value);
                for(let i=0;i<goodsArray.length;i++){
                    goodsPrice+='<br/>'+JSON.parse(goodsArray[i]).goodsPrice;
                }
                return goodsPrice;
            },
        },
        mounted() {
            this.getOrderInfoByParams();
        }
    }

</script>

<style >
    .el-dialog{
        width: 500px;
    }
</style>
