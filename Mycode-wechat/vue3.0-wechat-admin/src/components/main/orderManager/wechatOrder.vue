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

    <!--工具条-->
    <el-col :span="24" class="toolbar" style="text-align: right">
      <el-button type="danger" @click="batchUpdate" :disabled="this.sels.length===0">批量处理</el-button>
    </el-col>

		<!--列表-->
		<el-table :data="orders" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55" label="全选">
			</el-table-column>
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
              <el-table-column type="success" prop="orderStatus" label="订单状态" min-width="100"  >
                  <template scope="scope">
                      <span style="color: blue;font-weight:bold;" v-if="scope.row.orderStatus=== 0">等待接单</span>
                  </template>
              </el-table-column>
			<el-table-column label="操作" width="70">
				<template scope="scope">
					<el-button  type="primary" size="small" @click="handleCheck(scope.$index, scope.row)">处理</el-button>
				</template>
			</el-table-column>
		</el-table>
   <!--分页-->
    <el-col :span="24" class="toolbar" style="text-align: right">
      <el-pagination background layout="total,prev, pager, next" @current-change="handleCurrentChange" :current-page="1" :page-size="pageSize" :total="total" style="float:right;">
      </el-pagination>
    </el-col>




		<!--处理界面-->
    <el-dialog title="用户订单处理" :visible.sync="dialogFormVisible" >
      <el-form ref="form" :model="handleform" label-width="80px">
        <el-form-item label="是否接单" >
          <el-select v-model="handleform.reasonByte" placeholder="请选择" style="width: 100%" >
            <el-option label="接单" value="1"></el-option>
            <el-option label="拒单" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注/原因" >
          <el-input type="textarea" v-model="handleform.desc"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click.native="addSubmit">确 定</el-button>
      </div>
    </el-dialog>


	</section>
</template>

<script>

	import {  getOrderInfoBykeys ,batchUpdateStatus} from '../../wechatApi/api';

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
				sels: [],//列表选中列
                row_orderNum:null, //单个处理选中行编号
                dialogFormVisible: false,//编辑界面是否显示
                handleform: {
                      reasonByte: '',//原因
                      desc: ''//描述
                     }
			    }
		},
		methods: {
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
            let requestParmams={"allStatus":[0],"endTime":this.endTime,"keys":this.filters.keywords,"pageNum":this.pageNum,"pageSize":this.pageSize,"startTime":this.startTime}
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


			//显示编辑界面
          handleCheck: function (index, row) {
			  this.row_orderNum=row.orderNum;
				this.dialogFormVisible = true;
			},


			selsChange: function (sels) {
			  console.log(sels)
				this.sels = sels;
			},
			//批量审核  批量全部接单
            batchUpdate: function () {
				var orderIds = this.sels.map(item => item.orderNum).toString();
                 console.log(orderIds);
                let upInfo={
                    orderNums:orderIds,
                    status:1   //接单status
                };

				this.$confirm('确认处理所有记录吗？', '提示', {
					type: 'warning'
				}).then(() => {
                    batchUpdateStatus(upInfo).then((res)=>{
                        console.log(res)
                        if(res.data=200){
                            const h = this.$createElement;
                            this.$notify({
                                title: '提示',
                                message: h('i', { style: 'color: teal'}, '批量处理成功')
                            });
                            this.getOrderInfoByParams();
                        }
                    });
				}).catch(() => {
                  // alert("跟新异常")
				});
			},
      //提交订单处理结果
      addSubmit:function (e) {
            if(this.handleform.reasonByte==""){  //验证是否选择处理理由
                const h = this.$createElement;
                this.$notify({
                    title: '提示',
                    message: h('i', { style: 'color: teal'}, '理由不能为空')
                });
                return;
            }
          let upInfo={
              orderNums:this.row_orderNum,
              status:this.handleform.reasonByte   //接单status
          };
          batchUpdateStatus(upInfo).then((res)=>{
              console.log(res)
              if(res.data=200){
                  const h = this.$createElement;
                  this.$notify({
                      title: '提示',
                      message: h('i', { style: 'color: teal'}, '处理成功')
                  });
                  this.getOrderInfoByParams();
              }
          });
        this.dialogFormVisible = false;
        console.log(this.handleform)
      }
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
