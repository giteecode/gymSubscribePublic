







<template>
    <div class="main-content">

        <!-- 条件查询 -->
        <div v-if="showFlag">
            <el-form :inline="true" :model="searchForm" class="form-content">
                <el-row :gutter="20" class="slt" :style="{justifyContent:contents.searchBoxPosition=='1'?'flex-start':contents.searchBoxPosition=='2'?'center':'flex-end'}">
                                                                                
                                                             
                    <el-form-item :label="contents.inputTitle == 1 ? '场地名称' : ''">
                        <el-input prefix-icon="el-icon-search" v-model="searchForm.changdiName" placeholder="场地名称" clearable></el-input>
                    </el-form-item>
                                         
                    <el-form-item :label="contents.inputTitle == 1 ? '场地类型' : ''">
                        <el-select v-model="searchForm.changdiTypes" placeholder="请选择场地类型">
                            <el-option label="=-请选择-=" value=""></el-option>
                            <el-option
                                    v-for="(item,index) in changdiTypesSelectSearch"
                                    v-bind:key="index"
                                    :label="item.indexName"
                                    :value="item.codeIndex">
                                <!--lable是要展示的名称-->
                                <!--value是值-->
                            </el-option>
                        </el-select>
                    </el-form-item>
                                                                                                                                                                                                                                                                     
                    <el-form-item :label="contents.inputTitle == 1 ? '用户姓名' : ''">
                        <el-input prefix-icon="el-icon-search" v-model="searchForm.yonghuName" placeholder="用户姓名" clearable></el-input>
                    </el-form-item>
                     
                    <el-form-item :label="contents.inputTitle == 1 ? '用户手机号' : ''">
                        <el-input prefix-icon="el-icon-search" v-model="searchForm.yonghuPhone" placeholder="用户手机号" clearable></el-input>
                    </el-form-item>
                     
                    <el-form-item :label="contents.inputTitle == 1 ? '用户身份证号' : ''">
                        <el-input prefix-icon="el-icon-search" v-model="searchForm.yonghuIdNumber" placeholder="用户身份证号" clearable></el-input>
                    </el-form-item>
                                                                                

                    <el-form-item>
                        <el-button type="success" @click="search()">查询<i class="el-icon-search el-icon--right"/></el-button>
                    </el-form-item>
                </el-row>
                <el-row class="ad" :style="{justifyContent:contents.btnAdAllBoxPosition=='1'?'flex-start':contents.btnAdAllBoxPosition=='2'?'center':'flex-end'}">
                    <el-form-item>
                        <el-button
                                v-if="isAuth('changdiOrder','新增')"
                                type="success"
                                icon="el-icon-plus"
                                @click="addOrUpdateHandler()"
                        >新增</el-button>
                        &nbsp;
                        <el-button
                                v-if="isAuth('changdiOrder','删除')"
                                :disabled="dataListSelections.length <= 0"
                                type="danger"
                                icon="el-icon-delete"
                                @click="deleteHandler()"
                        >删除</el-button>
                        &nbsp;
                        <el-button
                                v-if="isAuth('changdiOrder','报表')"
                                type="success"
                                icon="el-icon-pie-chart"
                                @click="chartDialog()"
                        >报表</el-button>
                        &nbsp;
                        <a style="text-decoration:none" class="el-button el-button--success"
                           v-if="isAuth('changdiOrder','导入导出')"
                           icon="el-icon-download"
                           href="http://localhost:8080/tiyuguan/upload/changdiOrderMuBan.xls"
                        >批量导入场地预约数据模板</a>
                        &nbsp;
                        <el-upload
                                v-if="isAuth('changdiOrder','导入导出')"
                                style="display: inline-block"
                                action="tiyuguan/file/upload"
                                :on-success="changdiOrderUploadSuccess"
                                :on-error="changdiOrderUploadError"
                                :show-file-list = false>
                            <el-button
                                    v-if="isAuth('changdiOrder','导入导出')"
                                    type="success"
                                    icon="el-icon-upload2"
                            >批量导入场地预约数据</el-button>
                        </el-upload>
                        &nbsp;
                        <!-- 导出excel -->
                        <download-excel v-if="isAuth('changdiOrder','导入导出')" style="display: inline-block" class = "export-excel-wrapper" :data = "dataList" :fields = "json_fields" name = "changdiOrder.xls">
                            <!-- 导出excel -->
                            <el-button
                                    type="success"
                                    icon="el-icon-download"
                            >导出</el-button>
                        </download-excel>
                        &nbsp;
                    </el-form-item>
                </el-row>
            </el-form>
            <div class="table-content">
                <el-table class="tables" :size="contents.tableSize" :show-header="contents.tableShowHeader"
                          :header-row-style="headerRowStyle" :header-cell-style="headerCellStyle"
                          :border="contents.tableBorder"
                          :fit="contents.tableFit"
                          :stripe="contents.tableStripe"
                          :row-style="rowStyle"
                          :cell-style="cellStyle"
                          :style="{width: '100%',fontSize:contents.tableContentFontSize,color:contents.tableContentFontColor}"
                          v-if="isAuth('changdiOrder','查看')"
                          :data="dataList"
                          v-loading="dataListLoading"
                          @selection-change="selectionChangeHandler">
                    <el-table-column  v-if="contents.tableSelection"
                                      type="selection"
                                      header-align="center"
                                      align="center"
                                      width="50">
                    </el-table-column>
                    <el-table-column label="索引" v-if="contents.tableIndex" type="index" width="50" />
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="changdiUuidNumber"
                                      header-align="center"
                                      label="场地编号">
                        <template slot-scope="scope">
                            {{scope.row.changdiUuidNumber}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="changdiName"
                                      header-align="center"
                                      label="场地名称">
                        <template slot-scope="scope">
                            {{scope.row.changdiName}}
                        </template>
                    </el-table-column>
                    <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign" prop="changdiPhoto"
                               header-align="center"
                               width="200"
                               label="场地照片">
                        <template slot-scope="scope">
                            <div v-if="scope.row.changdiPhoto">
                                <img :src="scope.row.changdiPhoto" width="100" height="100">
                            </div>
                            <div v-else>无图片</div>
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="yonghuName"
                                      header-align="center"
                                      label="用户姓名">
                        <template slot-scope="scope">
                            {{scope.row.yonghuName}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="yonghuPhone"
                                      header-align="center"
                                      label="用户手机号">
                        <template slot-scope="scope">
                            {{scope.row.yonghuPhone}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="changdiOrderUuidNumber"
                                   header-align="center"
                                   label="订单号">
                        <template slot-scope="scope">
                            {{scope.row.changdiOrderUuidNumber}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="changdiOrderTruePrice"
                                   header-align="center"
                                   label="实付价格">
                        <template slot-scope="scope">
                            {{scope.row.changdiOrderTruePrice}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="changdiOrderTypes"
                                      header-align="center"
                                      label="订单类型">
                        <template slot-scope="scope">
                            {{scope.row.changdiOrderValue}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="shijianduan"
                                   header-align="center"
                                   label="预约时间段">
                        <template slot-scope="scope">
                            {{scope.row.shijianduan}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="buyTime"
                                   header-align="center"
                                   label="预约日期">
                        <template slot-scope="scope">
                            {{scope.row.buyTime}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="insertTime"
                                   header-align="center"
                                   label="订单创建时间">
                        <template slot-scope="scope">
                            {{scope.row.insertTime}}
                        </template>
                    </el-table-column>

                    <el-table-column width="300" :align="contents.tableAlign"
                                     header-align="center"
                                     label="操作">
                        <template slot-scope="scope">
                            <el-button v-if="isAuth('changdiOrder','查看')" type="success" icon="el-icon-tickets" size="mini" @click="addOrUpdateHandler(scope.row.id,'info')">详情</el-button>
                            <el-button v-if="isAuth('changdiOrder','修改')" type="primary" icon="el-icon-edit" size="mini" @click="addOrUpdateHandler(scope.row.id)">修改</el-button>
                            <el-button v-if="isAuth('changdiOrder','删除')" type="danger" icon="el-icon-delete" size="mini" @click="deleteHandler(scope.row.id)">删除</el-button>
                            <el-button v-if="isAuth('changdiOrder','订单') && scope.row.changdiOrderTypes == 1 && role == '用户'" type="primary" icon="el-icon-sold-out" size="mini" @click="refund(scope.row.id)">取消预约</el-button>




                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination
                        clsss="pages"
                        :layout="layouts"
                        @size-change="sizeChangeHandle"
                        @current-change="currentChangeHandle"
                        :current-page="pageIndex"
                        :page-sizes="[10, 20, 50, 100]"
                        :page-size="Number(contents.pageEachNum)"
                        :total="totalPage"
                        :small="contents.pageStyle"
                        class="pagination-content"
                        :background="contents.pageBtnBG"
                        :style="{textAlign:contents.pagePosition==1?'left':contents.pagePosition==2?'center':'right'}"
                ></el-pagination>
            </div>
        </div>
        <!-- 添加/修改页面  将父组件的search方法传递给子组件-->
        <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>

        <!-- 评价 -->
		<el-dialog
                title="评价"
                :visible.sync="commentbackVisible"
                width="30%">
            <span>评价内容</span>
            <el-input type="textarea" v-model="commentbackContent"></el-input>
            <span slot="footer" class="dialog-footer">
			<el-button @click="commentbackVisible = false">取 消</el-button>
			<el-button type="primary" @click="commentback()">确 定</el-button>
		  </span>
        </el-dialog>

        <el-dialog title="统计报表" :visible.sync="chartVisiable" width="800">
            <el-date-picker
                    v-model="echartsDate"
                    type="year"
                    placeholder="选择年">
            </el-date-picker>
            <el-button @click="chartDialog()">查询</el-button>
            <div id="statistic" style="width:100%;height:600px;"></div>
            <span slot="footer" class="dialog-footer">
				<el-button @click="chartVisiable = false">返回</el-button>
			</span>
        </el-dialog>

    </div>
</template>
<script>
    import AddOrUpdate from "./add-or-update";
    import styleJs from "../../../utils/style.js";
    export default {
        data() {
        return {
            searchForm: {
                key: ""
            },
            sessionTable : "",//登录账户所在表名
            role : "",//权限
    //级联表下拉框搜索条件
              changdiTypesSelectSearch : [],
    //当前表下拉框搜索条件
            form:{
                id : null,
                changdiOrderUuidNumber : null,
                changdiId : null,
                yonghuId : null,
                changdiOrderTruePrice : null,
                changdiOrderTypes : null,
                shijianduan : null,
                buyTime : null,
                insertTime : null,
                createTime : null,
            },
            dataList: [],
            pageIndex: 1,
            pageSize: 10,
            totalPage: 0,
            dataListLoading: false,
            dataListSelections: [],
            showFlag: true,
            sfshVisiable: false,
            shForm: {},
            chartVisiable: false,
            addOrUpdateFlag:false,
            contents:null,
            layouts: '',

            echartsDate: new Date(),//echarts的时间查询字段

            //导出excel
            json_fields: {
                //级联表字段
                     '场地编号': 'changdiUuidNumber',
                     '场地名称': 'changdiName',
                     '场地照片': 'changdiPhoto',
                     '场地类型': 'changdiTypes',
                     '场地原价': 'changdiOldMoney',
                     '场地现价': 'changdiNewMoney',
                     '时间段': 'shijianduan',
                     '人数': 'shijianduanRen',
                     '点击次数': 'changdiClicknum',
                     '半全场': 'banquanTypes',
                     '是否上架': 'shangxiaTypes',
                     '推荐吃饭地点': 'tuijian',
                     '用户姓名': 'yonghuName',
                     '用户手机号': 'yonghuPhone',
                     '用户身份证号': 'yonghuIdNumber',
                     '用户头像': 'yonghuPhoto',
                     '电子邮箱': 'yonghuEmail',
                     '余额': 'newMoney',
                //本表字段
                     '订单号': "changdiOrderUuidNumber",
                     '实付价格': "changdiOrderTruePrice",
                     '订单类型': "changdiOrderTypes",
                     '预约时间段': "shijianduan",
                     '预约日期': "buyTime",
                     '订单创建时间': "insertTime",
            },

        //评价
			//操作数据id
			commentbackId:null,
			//评价内容
			commentbackContent:null,
			//模态框状态
			commentbackVisible:false,            };
        },
        created() {
            this.contents = styleJs.listStyle();
            this.init();
            this.getDataList();
            this.contentStyleChange()
        },
        mounted() {
            //获取当前登录用户的信息
            this.sessionTable = this.$storage.get("sessionTable");
            this.role = this.$storage.get("role");

        },
        filters: {
            htmlfilter: function (val) {
                return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
            }
        },
        components: {
            AddOrUpdate,
        },
        computed: {
        },
        methods: {
            chartDialog() {
                let _this = this;
                let params = {
                    dateFormat :"%Y"
                    ,riqi :_this.echartsDate.getFullYear()
                    ,thisTable : {//当前表
                        tableName :"shangdian_shouyin"//当前表表名
                        ,sumColum : 'shangdian_shouyin_true_price' //求和字段
                        ,date : 'insert_time'//分组日期字段
                        // ,string : 'name,leixing'//分组字符串字段
                        // ,types : 'shangdian_shouyin_types'//分组下拉框字段
                    }
                    // ,joinTable : {//级联表（可以不存在）
                    //     tableName :"yuangong"//级联表表名
                    //     // ,date : 'insert_time'//分组日期字段
                    //     ,string : 'yuangong_name'//分组字符串字段
                    //     // ,types : 'insertTime'//分组下拉框字段
                    // }
                }
                _this.chartVisiable = true;
                _this.$nextTick(() => {
                    var statistic = this.$echarts.init(document.getElementById("statistic"), 'macarons');
                    this.$http({
                        url: "barSum",
                        method: "get",
                        params: params
                    }).then(({data}) => {
                        if(data && data.code === 0){


                            //柱状图 求和 已成功使用
                            //start
                            let series = [];//具体数据值
                            data.data.yAxis.forEach(function (item,index) {
                                let tempMap = {};
                                tempMap.name=data.data.legend[index];
                                tempMap.type='bar';
                                tempMap.data=item;
                                series.push(tempMap);

                            })

                            var option = {
                                tooltip: {
                                    trigger: 'axis',
                                    axisPointer: {
                                        type: 'cross',
                                        crossStyle: {
                                            color: '#999'
                                        }
                                    }
                                },
                                toolbox: {
                                    feature: {
                                        // dataView: { show: true, readOnly: false },  // 数据查看
                                        magicType: { show: true, type: ['line', 'bar'] },//切换图形展示方式
                                        // restore: { show: true }, // 刷新
                                        saveAsImage: { show: true }//保存
                                    }
                                },
                                legend: {
                                    data: data.data.legend//标题  可以点击导致某一列数据消失
                                },
                                xAxis: [
                                    {
                                        type: 'category',
                                        name: '月份',
                                        data: data.data.xAxis,
                                        axisPointer: {
                                            type: 'shadow'
                                        }
                                    }
                                ],
                                yAxis: [
                                    {
                                        type: 'value',//不能改
                                        name: '元',//y轴单位
                                        axisLabel: {
                                            formatter: '{value} 元' // 后缀
                                        }
                                    }
                                ],
                                series:series//具体数据
                            };
                            // 使用刚指定的配置项和数据显示图表。
                            statistic.setOption(option);
                            //根据窗口的大小变动图表
                            window.onresize = function () {
                                statistic.resize();
                            };
                            //end



                            //饼状图 原先自带的 未修改过
                            //start
                            /*let res = data.data;
                            let xAxis = [];
                            let yAxis = [];
                            let pArray = []
                            for (let i = 0; i < res.length; i++) {
                                xAxis.push(res[i].xinzi);
                                yAxis.push(res[i].total);
                                pArray.push({
                                    value: res[i].total,
                                    name: res[i].xinzi
                                })
                                var option = {};
                                option = {
                                    title: {
                                        text: '统计',
                                        left: 'center'
                                    },
                                    tooltip: {
                                        trigger: 'item',
                                        formatter: '{b} : {c} ({d}%)'
                                    },
                                    series: [{
                                        type: 'pie',
                                        radius: '55%',
                                        center: ['50%', '60%'],
                                        data: pArray,
                                        emphasis: {
                                            itemStyle: {
                                                shadowBlur: 10,
                                                shadowOffsetX: 0,
                                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                                            }
                                        }
                                    }]
                                };
                                // 使用刚指定的配置项和数据显示图表。
                                statistic.setOption(option);
                                //根据窗口的大小变动图表
                                window.onresize = function () {
                                    statistic.resize();
                                };
                            }*/

                            //end
                        }else {
                            this.$message({
                                message: "报表未查询到数据",
                                type: "success",
                                duration: 1500,
                                onClose: () => {
                                    this.search();
                                }
                            });
                        }
                    });
                });
                this.chartVisiable = !this.chartVisiable;
                this.$nextTick(() => {
                    var statistic = this.$echarts.init(document.getElementById("statistic"), 'macarons');
                    this.$http({
                        url: "group/xinzitongji/xinzi",
                        method: "get",
                    }).then(({data}) => {
                        if(data && data.code === 0){
                            let res = data.data;
                            let xAxis = [];
                            let yAxis = [];
                            let pArray = []
                            for (let i = 0; i < res.length; i++) {
                                xAxis.push(res[i].xinzi);
                                yAxis.push(res[i].total);
                                pArray.push({
                                    value: res[i].total,
                                    name: res[i].xinzi
                                })
                                var option = {};
                                option = {
                                    title: {
                                        text: '统计',
                                        left: 'center'
                                    },
                                    tooltip: {
                                        trigger: 'item',
                                        formatter: '{b} : {c} ({d}%)'
                                    },
                                    series: [{
                                        type: 'pie',
                                        radius: '55%',
                                        center: ['50%', '60%'],
                                        data: pArray,
                                        emphasis: {
                                            itemStyle: {
                                                shadowBlur: 10,
                                                shadowOffsetX: 0,
                                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                                            }
                                        }
                                    }]
                                };
                                // 使用刚指定的配置项和数据显示图表。
                                statistic.setOption(option);
                                //根据窗口的大小变动图表
                                window.onresize = function () {
                                    statistic.resize();
                                };
                            }
                        }
                    });
                // xcolumn ycolumn
                });
            },
            contentStyleChange() {
                this.contentSearchStyleChange()
                this.contentBtnAdAllStyleChange()
                this.contentSearchBtnStyleChange()
                this.contentTableBtnStyleChange()
                this.contentPageStyleChange()
            },
            contentSearchStyleChange() {
                this.$nextTick(() => {
                    document.querySelectorAll('.form-content .slt .el-input__inner').forEach(el => {
                        let textAlign = 'left'
                        if(this.contents.inputFontPosition == 2)
                            textAlign = 'center'
                            if (this.contents.inputFontPosition == 3) textAlign = 'right'
                                el.style.textAlign = textAlign
                            el.style.height = this.contents.inputHeight
                            el.style.lineHeight = this.contents.inputHeight
                            el.style.color = this.contents.inputFontColor
                            el.style.fontSize = this.contents.inputFontSize
                            el.style.borderWidth = this.contents.inputBorderWidth
                            el.style.borderStyle = this.contents.inputBorderStyle
                            el.style.borderColor = this.contents.inputBorderColor
                            el.style.borderRadius = this.contents.inputBorderRadius
                            el.style.backgroundColor = this.contents.inputBgColor
                    })
                    if (this.contents.inputTitle) {
                        document.querySelectorAll('.form-content .slt .el-form-item__label').forEach(el => {
                            el.style.color = this.contents.inputTitleColor
                            el.style.fontSize = this.contents.inputTitleSize
                            el.style.lineHeight = this.contents.inputHeight
                        })
                    }
                    setTimeout(() => {
                        document.querySelectorAll('.form-content .slt .el-input__prefix').forEach(el => {
                            el.style.color = this.contents.inputIconColor
                            el.style.lineHeight = this.contents.inputHeight
                        })
                        document.querySelectorAll('.form-content .slt .el-input__suffix').forEach(el => {
                            el.style.color = this.contents.inputIconColor
                            el.style.lineHeight = this.contents.inputHeight
                        })
                        document.querySelectorAll('.form-content .slt .el-input__icon').forEach(el => {
                            el.style.lineHeight = this.contents.inputHeight
                        })
                    }, 10 )
                })
            },
            // 搜索按钮
            contentSearchBtnStyleChange() {
                this.$nextTick(() => {
                    document.querySelectorAll('.form-content .slt .el-button--success').forEach(el => {
                        el.style.height = this.contents.searchBtnHeight
                        el.style.color = this.contents.searchBtnFontColor
                        el.style.fontSize = this.contents.searchBtnFontSize
                        el.style.borderWidth = this.contents.searchBtnBorderWidth
                        el.style.borderStyle = this.contents.searchBtnBorderStyle
                        el.style.borderColor = this.contents.searchBtnBorderColor
                        el.style.borderRadius = this.contents.searchBtnBorderRadius
                        el.style.backgroundColor = this.contents.searchBtnBgColor
                    })
                })
            },
            // 新增、批量删除
            contentBtnAdAllStyleChange() {
                this.$nextTick(() => {
                    document.querySelectorAll('.form-content .ad .el-button--success').forEach(el => {
                        el.style.height = this.contents.btnAdAllHeight
                        el.style.color = this.contents.btnAdAllAddFontColor
                        el.style.fontSize = this.contents.btnAdAllFontSize
                        el.style.borderWidth = this.contents.btnAdAllBorderWidth
                        el.style.borderStyle = this.contents.btnAdAllBorderStyle
                        el.style.borderColor = this.contents.btnAdAllBorderColor
                        el.style.borderRadius = this.contents.btnAdAllBorderRadius
                        el.style.backgroundColor = this.contents.btnAdAllAddBgColor
                    })
                    document.querySelectorAll('.form-content .ad .el-button--danger').forEach(el => {
                        el.style.height = this.contents.btnAdAllHeight
                        el.style.color = this.contents.btnAdAllDelFontColor
                        el.style.fontSize = this.contents.btnAdAllFontSize
                        el.style.borderWidth = this.contents.btnAdAllBorderWidth
                        el.style.borderStyle = this.contents.btnAdAllBorderStyle
                        el.style.borderColor = this.contents.btnAdAllBorderColor
                        el.style.borderRadius = this.contents.btnAdAllBorderRadius
                        el.style.backgroundColor = this.contents.btnAdAllDelBgColor
                    })
                    document.querySelectorAll('.form-content .ad .el-button--warning').forEach(el => {
                        el.style.height = this.contents.btnAdAllHeight
                        el.style.color = this.contents.btnAdAllWarnFontColor
                        el.style.fontSize = this.contents.btnAdAllFontSize
                        el.style.borderWidth = this.contents.btnAdAllBorderWidth
                        el.style.borderStyle = this.contents.btnAdAllBorderStyle
                        el.style.borderColor = this.contents.btnAdAllBorderColor
                        el.style.borderRadius = this.contents.btnAdAllBorderRadius
                        el.style.backgroundColor = this.contents.btnAdAllWarnBgColor
                    })
                })
            },
            // 表格
            rowStyle({row, rowIndex}) {
                if (rowIndex % 2 == 1) {
                    if (this.contents.tableStripe) {
                        return {color: this.contents.tableStripeFontColor}
                    }
                } else {
                    return ''
                }
            },
            cellStyle({row, rowIndex}) {
                if (rowIndex % 2 == 1) {
                    if (this.contents.tableStripe) {
                        return {backgroundColor: this.contents.tableStripeBgColor}
                    }
                } else {
                    return ''
                }
            },
            headerRowStyle({row, rowIndex}) {
                return {color: this.contents.tableHeaderFontColor}
            },
            headerCellStyle({row, rowIndex}) {
                return {backgroundColor: this.contents.tableHeaderBgColor}
            },
            // 表格按钮
            contentTableBtnStyleChange() {
                // this.$nextTick(()=>{
                //   setTimeout(()=>{
                //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--success').forEach(el=>{
                //       el.style.height = this.contents.tableBtnHeight
                //       el.style.color = this.contents.tableBtnDetailFontColor
                //       el.style.fontSize = this.contents.tableBtnFontSize
                //       el.style.borderWidth = this.contents.tableBtnBorderWidth
                //       el.style.borderStyle = this.contents.tableBtnBorderStyle
                //       el.style.borderColor = this.contents.tableBtnBorderColor
                //       el.style.borderRadius = this.contents.tableBtnBorderRadius
                //       el.style.backgroundColor = this.contents.tableBtnDetailBgColor
                //     })
                //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--primary').forEach(el=>{
                //       el.style.height = this.contents.tableBtnHeight
                //       el.style.color = this.contents.tableBtnEditFontColor
                //       el.style.fontSize = this.contents.tableBtnFontSize
                //       el.style.borderWidth = this.contents.tableBtnBorderWidth
                //       el.style.borderStyle = this.contents.tableBtnBorderStyle
                //       el.style.borderColor = this.contents.tableBtnBorderColor
                //       el.style.borderRadius = this.contents.tableBtnBorderRadius
                //       el.style.backgroundColor = this.contents.tableBtnEditBgColor
                //     })
                //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--danger').forEach(el=>{
                //       el.style.height = this.contents.tableBtnHeight
                //       el.style.color = this.contents.tableBtnDelFontColor
                //       el.style.fontSize = this.contents.tableBtnFontSize
                //       el.style.borderWidth = this.contents.tableBtnBorderWidth
                //       el.style.borderStyle = this.contents.tableBtnBorderStyle
                //       el.style.borderColor = this.contents.tableBtnBorderColor
                //       el.style.borderRadius = this.contents.tableBtnBorderRadius
                //       el.style.backgroundColor = this.contents.tableBtnDelBgColor
                //     })

                //   }, 50)
                // })
            },
            // 分页
            contentPageStyleChange() {
                let arr = []
                if (this.contents.pageTotal) arr.push('total')
                if (this.contents.pageSizes) arr.push('sizes')
                if (this.contents.pagePrevNext) {
                    arr.push('prev')
                    if (this.contents.pagePager) arr.push('pager')
                    arr.push('next')
                }
                if (this.contents.pageJumper) arr.push('jumper')
                this.layouts = arr.join()
                this.contents.pageEachNum = 10
            },

            init() {
            },
            search() {
                this.pageIndex = 1;
                this.getDataList();
            },
            // 获取数据列表
            getDataList() {
                this.dataListLoading = true;
                let params = {
                    page: this.pageIndex,
                    limit: this.pageSize,
                    sort: 'id',
                }

                                                             
                if (this.searchForm.changdiName!= '' && this.searchForm.changdiName!= undefined) {
                    params['changdiName'] = '%' + this.searchForm.changdiName + '%'
                }
                                         
                if (this.searchForm.changdiTypes!= '' && this.searchForm.changdiTypes!= undefined) {
                    params['changdiTypes'] = this.searchForm.changdiTypes
                }
                                                                                                                                                                                                                                                                     
                if (this.searchForm.yonghuName!= '' && this.searchForm.yonghuName!= undefined) {
                    params['yonghuName'] = '%' + this.searchForm.yonghuName + '%'
                }
                     
                if (this.searchForm.yonghuPhone!= '' && this.searchForm.yonghuPhone!= undefined) {
                    params['yonghuPhone'] = '%' + this.searchForm.yonghuPhone + '%'
                }
                     
                if (this.searchForm.yonghuIdNumber!= '' && this.searchForm.yonghuIdNumber!= undefined) {
                    params['yonghuIdNumber'] = '%' + this.searchForm.yonghuIdNumber + '%'
                }
                                                                                                                                                                
                params['changdiOrderDelete'] = 1// 逻辑删除字段 1 未删除 2 删除


                this.$http({
                    url: "changdiOrder/page",
                    method: "get",
                    params: params
                }).then(({data}) => {
                    if(data && data.code === 0){
                        this.dataList = data.data.list;
                        this.totalPage = data.data.total;
                    }else{
                        this.dataList = [];
                        this.totalPage = 0;
                    }
                    this.dataListLoading = false;
                });

                //查询级联表搜索条件所有列表
                this.$http({
                    url: "dictionary/page?dicCode=changdi_types&page=1&limit=100",
                    method: "get",
                    page: 1,
                    limit: 100,
                }).then(({data}) => {
                    if(data && data.code === 0){
                        this.changdiTypesSelectSearch = data.data.list;
                    }
                });
                //查询当前表搜索条件所有列表
            },
            //每页数
            sizeChangeHandle(val) {
                this.pageSize = val;
                this.pageIndex = 1;
                this.getDataList();
            },
            // 当前页
            currentChangeHandle(val) {
                this.pageIndex = val;
                this.getDataList();
            },
            // 多选
            selectionChangeHandler(val) {
                this.dataListSelections = val;
            },
            // 添加/修改
            addOrUpdateHandler(id, type) {
                this.showFlag = false;
                this.addOrUpdateFlag = true;
                this.crossAddOrUpdateFlag = false;
                if (type != 'info') {
                    type = 'else';
                }
                this.$nextTick(() => {
                    this.$refs.addOrUpdate.init(id, type);
                });
            },
            // 下载
            download(file) {
                window.open(" ${file} ")
            },
            // 删除
            deleteHandler(id) {
                var ids = id ? [Number(id)] : this.dataListSelections.map(item => {
                    return Number(item.id);
                });

                this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.$http({
                        url: "changdiOrder/delete",
                        method: "post",
                        data: ids
                    }).then(({data}) => {
                        if(data && data.code === 0){
                            this.$message({
                                message: "操作成功",
                                type: "success",
                                duration: 1500,
                                onClose: () => {
                                    this.search();
                                }
                            });
                        }else{
                            this.$message.error(data.msg);
                        }
                    });
                });
            },
            // 导入功能上传文件成功后调用导入方法
            changdiOrderUploadSuccess(data){
                let _this = this;
                _this.$http({
                    url: "changdiOrder/batchInsert?fileName=" + data.file,
                    method: "get"
                }).then(({data}) => {
                    if(data && data.code === 0){
                        _this.$message({
                            message: "导入场地预约数据成功",
                            type: "success",
                            duration: 1500,
                            onClose: () => {
                                _this.search();
                            }
                        });
                    }else{
                        _this.$message.error(data.msg);
                    }
                });

            },
            // 导入功能上传文件失败后调用导入方法
            changdiOrderUploadError(data){
                this.$message.error('上传失败');
            },

            //取消预约
            refund(id){
                this.$confirm(`确定要取消预约吗?`, "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.$http({
                        url: "changdiOrder/refund?id="+id,
                        method: "post",
                    }).then(({ data }) => {
                        if (data && data.code === 0) {
                            this.$message({
                                message:"取消成功",
                                type: "success",
                                duration: 1500,
                                onClose: () => {this.search();}
                            });
                        } else {
                            this.$message.error(data.msg);
                        }
                    });
                });
            },









        }
    };
</script>
<style lang="scss" scoped>
.slt {
    margin: 0 !important;
    display: flex;
  }

  .ad {
    margin: 0 !important;
    display: flex;
  }

  .pages {
    & /deep/ el-pagination__sizes{
      & /deep/ el-input__inner {
        height: 22px;
        line-height: 22px;
      }
    }
  }


  .el-button+.el-button {
    margin:0;
  }

  .tables {
	& /deep/ .el-button--success {
		height: 40px;
		color: #333;
		font-size: 14px;
		border-width: 1px;
		border-style: solid;
		border-color: #DCDFE6;
		border-radius: 6px;
		background-color: rgba(121, 244, 164, 0.54);
	}

	& /deep/ .el-button--primary {
		height: 40px;
		color: #333;
		font-size: 14px;
		border-width: 1px;
		border-style: solid;
		border-color: #DCDFE6;
		border-radius: 6px;
		background-color: rgba(169, 115, 232, 0.65);
	}

	& /deep/ .el-button--danger {
		height: 40px;
		color: #333;
		font-size: 14px;
		border-width: 1px;
		border-style: solid;
		border-color: #DCDFE6;
		border-radius: 6px;
		background-color: rgba(241, 120, 186, 1);
	}

    & /deep/ .el-button {
      margin: 4px;
    }
  }
</style>


