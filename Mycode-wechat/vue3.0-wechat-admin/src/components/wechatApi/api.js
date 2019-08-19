import axios from 'axios';
const Qs = require('qs');

axios.defaults.baseURL = '/api';
//axios.defaults.baseURL = 'http://115.159.127.105:8089';
// 判断正式环境时启用
//if(process.env.NODE_ENV==='production'){
 //   axios.defaults.baseURL = 'http://115.159.127.105:8089/api'
//}

//获取订单信息
export const getOrderInfoBykeys = params =>{return axios.post(`/wechatAdmin/getOrderInfoBykeys`,Qs.stringify(params)).then(res=>res.data)};
//审核处理
export const batchUpdateStatus = params => { return axios.post(`/wechatAdmin/batchUpdateStatus`, Qs.stringify(params)).then(res => res.data); };
//查询商品  当前分类
export const getGoodsByItemId = params => { return axios.get(`/wechatApi/goodsInfoByItemId`, { params: params }); };
//查询所有分类
export const getGoodsClassify =params =>{return axios.get(`/wechatAdmin/getAllClassify`, { params: params }); };
//下架商品
export const delGoodsFromWehat = params => { return axios.post(`/wechatAdmin/delGoodsById`, Qs.stringify(params)).then(res => res.data); };
//添加商品
export const insertGoodsInfo = params => {return axios.post(`/wechatAdmin/addGoodsInfo`, params).then(res => res.data);};
//添加分类
export const insertClassify = params => {return axios.post(`/wechatAdmin/addGoodsClassifyInfo`, params).then(res => res.data);};
//socket
export const  getSocket = params => {return axios.get((`/admin/webSocket`, { params: params }))};