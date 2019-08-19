<template>
    <section>
        <el-col :span="24" style="text-align: left;margin-left: 10px;">
            <el-button type="primary" @click="dialogFormVisible = true" icon="el-icon-circle-plus-outline">菜单类别添加</el-button>
        </el-col>
        <el-col :span="16">
        <el-form  :model="goodsform" label-width="100px">
            <el-form-item label="所属分类">
                <el-select style="width: 500px" v-model="goodsform.goodsItemId" placeholder="请选择">
                    <el-option
                        v-for="item in classifyItems"
                        :label="item.classifyName"
                        :value="item.classifyId">
                </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="菜品名称">
                <el-input style="width: 500px" v-model="goodsform.goodsName"></el-input>
            </el-form-item>
            <el-form-item label="菜品描述">
                <el-input style="width: 500px" v-model="goodsform.goodsDescription"></el-input>
            </el-form-item>
            <el-form-item label="菜品标签">
                <el-checkbox-group v-model="goodsform.goodsTags">
                    <el-checkbox label="热销" name="goodsTags"></el-checkbox>
                    <el-checkbox label="本店招牌" name="goodsTags"></el-checkbox>
                    <el-checkbox label="新品" name="goodsTags"></el-checkbox>
                    <el-checkbox label="海外美食" name="goodsTags"></el-checkbox>
                </el-checkbox-group>
            </el-form-item>
            <el-form-item label="菜品价格">
                <el-input style="width: 100px" v-model="goodsform.goodsPrice"></el-input>元
            </el-form-item>
            <el-form-item label="菜品图片URL">
                <el-input style="width: 500px" v-model="goodsform.goodsImage"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button style="" type="primary" @click="onSubmitMain">立即添加</el-button>
            </el-form-item>
        </el-form>
        </el-col>
        <el-col :span="8">

            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>添加菜品预览</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <span style="font-weight: bold" >{{goodsform.goodsName}}</span>
                </div>
                <img :src="goodsform.goodsImage" style="width: 100%;" class="image">
            </el-card>
        </el-col>

        <!--添加分类对话框-->
        <el-dialog title="添加分类" :visible.sync="dialogFormVisible">
            <el-form :model="classifyform">
                <el-form-item label="分类名称" label-width="80px">
                    <el-input v-model="classifyform.classifyName" autocomplete="on"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary"  @click="onSubmit" >确 定</el-button>
            </div>
        </el-dialog>

    </section>
</template>

<script>
    import  {getGoodsClassify,insertGoodsInfo,insertClassify} from '../../wechatApi/api';
    export default {
        name: "carteUpdate",
        data(){
            return{
                dialogFormVisible:false,
                goodsform: {   //商品信息表单变量
                    goodsName: '',
                    goodsPrice: '',
                    goodsDescription: '',
                    goodsTags: [],
                    goodsItemId:'',
                    goodsImage:'',
                },
                classifyform:{
                    classifyName:''  //添加分类变量
                },
                classifyItems:[],//  所有分类数组
            }
        },
        methods:{
            getAllClassify(){
                getGoodsClassify(null).then(res=>{
                    this.classifyItems=res.data.data;
                })
            },
            addGoodsInfo(){
                const that=this;
                if(this.goodsform.goodsName=='' || this.goodsform.goodsImage=='' || this.goodsform.goodsPrice==''){
                    that.$notify({
                        title: '警告',
                        message: '商品名称，价格，图片不能为空！',
                        type: 'warning'
                    });
                }
                let addInfo={goodsName:this.goodsform.goodsName,goodsPrice: this.goodsform.goodsPrice,goodsDescription:this.goodsform.goodsDescription
                ,goodsTags:this.goodsform.goodsTags.toString(),goodsItemId: this.goodsform.goodsItemId,goodsImage: this.goodsform.goodsImage};
                insertGoodsInfo(addInfo).then(res => {
                    if(res.code==200){
                        that.$notify({
                            title: '成功',
                            message: '添加成功',
                            type: 'success'
                        });
                    }
                })
            },
            addClassify(){
                const that=this;
                if(that.classifyform.classifyName==''){
                    that.$notify({
                        title: '警告',
                        message: '分类名称不能为空！',
                        type: 'warning'
                    });
                    return;
                }
                let addInfo=that.classifyform;
                insertClassify(addInfo).then(res => {
                    if(res.code==200){
                        that.$notify({
                            title: '成功',
                            message: '添加成功',
                            type: 'success'
                        });
                        that.getAllClassify();//重新获取所有分类
                    }
                })
            },
            onSubmit(){
                this.dialogFormVisible=false;
                this.addClassify();//添加分类完成

            },
            onSubmitMain(){
              console.log(this.goodsform);
              this.addGoodsInfo();
            }
        },
        mounted(){
            this.getAllClassify();
        }

    }
</script>

<style scoped>
    .el-form-item__content{
        margin:0;
        padding: 0;
    }
    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 480px;
    }
</style>