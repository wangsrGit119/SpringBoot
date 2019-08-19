<template>
    <section>
        <!--第一行-->
        <el-row>
        <el-col :span="3" style="padding-top: 10px">
            <span >菜单主要分类</span>
        </el-col>
        <el-col :span="9">
            <el-select style="width: 500px;" v-model="goodsItemId" @change="selectedData(goodsItemId)" placeholder="请选择">
                <el-option
                        v-for="item in classifyItems"
                        :key="item.classifyId"
                        :label="item.classifyName"
                        :value="item.classifyId">
                </el-option>
            </el-select>
        </el-col>
        <el-col :span="12">
        </el-col>
        </el-row>
        <!--第二行-->
        <el-row style="margin-top: 40px;">
        <el-col :span="3">
            <span>所属栏目商品展示</span>
        </el-col>
        </el-row>
        <el-row>
            <el-col :span="24" style="margin-left: 20px;margin-top: 20px" >
                <div v-for="(item) in itemGoods" :key="item.id" style="float: left;">
                    <el-card class="box-card">
                        <div slot="header" class="clearfix">
                            <span>{{item.goodsName}}</span>&nbsp;
                            <span>{{item.goodsPrice}}</span>元
                            <el-button style="float: right; padding: 3px 0" type="text" @click="delGoods(item)">下架</el-button>
                        </div>
                       <img :src="item.goodsImage" style="width: 60%">
                    </el-card>
                </div>
            </el-col>
        </el-row>


    </section>
</template>

<script>
    import {  getGoodsByItemId,getGoodsClassify,delGoodsFromWehat} from '../../wechatApi/api';
    export default {
        name: "goodsItemDel",
        data(){
            return{
                goodsItemId:'',  //selected用到的变量
                classifyItems:[], //分类数组
                itemGoods:[],//商品卡片数组
                itemGoodsIdTemp:'', //临时变量 goodsId,
            }
        },
        methods:{
            delGoods(item){
                const that=this;
                console.log(item);
                let delInfo={goodsId:item.id};
                delGoodsFromWehat(delInfo).then(res=>{
                    console.log(res);
                    if(res.code==200){
                        that.$notify({
                            title: '成功',
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getAllGoodsByItemId()
                    }
                });
            },
            selectedData(index){
                this.itemGoodsIdTemp=index;
                this.getAllGoodsByItemId();

            },
            getAllClassify(){
                getGoodsClassify(null).then(res=>{
                    this.classifyItems=res.data.data;
                })
            },
            getAllGoodsByItemId(){
                if(this.itemGoodsIdTemp == ''){
                    this.$message({
                        message: '请选择商品类别',
                        type: 'success'
                    });
                    return;
                }
               let  queryInfo={itemId:this.itemGoodsIdTemp};
                getGoodsByItemId(queryInfo).then(res=>{
                    let resultGoodsInfo=JSON.parse(res.data.data);
                    this.itemGoods=resultGoodsInfo;
                })
            }
        },
        mounted() {
            this.getAllGoodsByItemId();
            this.getAllClassify();
        }
    }
</script>

<style scoped>
    .text {
        font-size: 14px;
    }

    .item {
        padding: 18px 0;
    }

    .box-card {
        width: 480px;
    }
</style>