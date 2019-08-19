<template>


    <section>
        <!--筛选条件-->
    <el-form ref="form" :model="dateform" label-width="150px">
        <el-form-item label="请选择查询范围">
            <el-col :span="2">
                <el-date-picker type="datetime" placeholder="开始时间" v-model="dateform.startTime" style="width: 200px;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="4">
                <el-date-picker type="datetime" placeholder="结束时间" v-model="dateform.endTime" style="width: 200px;"></el-date-picker>
            </el-col>
            <el-col :span="5">
                <el-button type="primary" @click="onSubmit">查询</el-button>
            </el-col>
        </el-form-item>
    </el-form>

    <!--图标显示模块-->
    <ve-histogram :data="chartData"></ve-histogram>
    </section>
</template>

<script>
    import {  getOrderInfoBykeys } from '../../wechatApi/api';
    export default {
      name:'orderEcharts',
        data() {
            return {
                chartData: {
                    columns: [],
                    rows: []
                },
                orders:[],
                menu:['日期'],//所有菜品数组
                menuSeries:[],//各个菜品动态栏目
                menuItemTime:[],//当前销量时间
                menuItmeDetail:[],//临时变量记住当前时间订单详情
                dateform:{
                    startTime:null,
                    endTime: new Date()
                }
            }
        },

        methods: {
          //时间格式化
            dateUtils(time){
                if(time==null){
                    return '';
                }
                var date=new Date(time);
                var year=date.getFullYear();
                var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
                var day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
                var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
                var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
                var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
                // 拼接
                return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
            },


            //调用api   首先要清空之前绘图数据
            getOrderInfoByParams(){

                const that=this;
                let requestParmams={"allStatus":[0,1,2],"endTime":this.dateform.endTime,"keys":"","pageNum":"","pageSize":"","startTime":this.dateform.startTime}
                let queryInfo={
                    queryInfo:JSON.stringify(requestParmams)
                };
                console.log(queryInfo);
                getOrderInfoBykeys(queryInfo).then((res)=>{
                    const  orderInfo=JSON.parse(res.data);
                    this.orders=orderInfo.list;
                    for(let i=0;i<this.orders.length;i++) {
                        let orderDetail = JSON.parse(this.orders[i].orderDetail);//获取订单明细
                        this.menuItemTime.push(this.orders[i].orderTime);   //订单时间
                        let goodsNameString=[];  //当前时间的商品名称
                        let goodsCountString=[];//当前商品销售数量
                        for(let j=0;j<orderDetail.length;j++) {
                            goodsNameString.push(JSON.parse(orderDetail[j]).goodsName);
                            goodsCountString.push(JSON.parse(orderDetail[j]).goodsCount);
                        }
                        //将时间 物品  销售数量   三者放在数组中
                        this.menuItmeDetail.push([this.dateUtils(this.orders[i].orderTime),goodsNameString,goodsCountString]);

                    }
                    //按照指定格式生成charts数据
                        var series={};
                        this.menuItmeDetail.map((item,index)=>{
                            series={};
                            var tempObject= item[1].map(function (value,index) {
                                that.menu.push(value);
                                return value;
                            });
                            tempObject.map(function (value,index) {
                               series[value]=item[2][index];
                            });
                            series['日期']=item[0];
                            that.menuSeries.push(series)
                        });
                    this.chartData.rows=JSON.parse(JSON.stringify(that.menuSeries));
                    this.chartData.columns=JSON.parse(JSON.stringify(that.menu));
                })
            },
            onSubmit() {
                this.menu=['日期'];
                this.menuSeries.length=0;
                this.menuItmeDetail.length=0;
                this.menuItemTime.length=0;
                this.getOrderInfoByParams()
            }
        },

        mounted: function () {
            this.getOrderInfoByParams();


        },
        // updated: function () {
        //     this.drawCharts()
        // }
    }
</script>

<style scoped>
    .chart-container {
        width: 100%;
        float: left;
    }
    /*.chart div {
        height: 400px;
        float: left;
    }*/

    .el-col {
        padding: 30px 20px;
    }
</style>
