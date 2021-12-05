<template>
    <div>
        <el-row type="flex" justify="end">
            <el-button type="primary" @click="search">刷新</el-button>
        </el-row>
        <el-row>
            <el-table
                    :data="taskAll"
                    stripe
                    :border="true"
                    style="width: 100%">
                <el-table-column
                        prop="id"
                        label="ID"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="text"
                        label="检测内容"
                        width="1032">
                </el-table-column>
                <el-table-column
                        prop="createdTime"
                        :formatter="dateFormat"
                        label="任务创建时间"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="finish"
                        label="是否完成"
                        :formatter="formatBoolean"
                        width="180">
                </el-table-column>
                <el-table-column
                        label="操作"
                        width="180">
                    <template slot-scope="scope">
                        <el-tooltip class="item" effect="dark" content="查看" placement="top-start">
                            <el-button type="info" icon="el-icon-view" circle
                                       @click="seeFile(scope.row)"></el-button>
                        </el-tooltip>
                    </template>
                </el-table-column>
            </el-table>
        </el-row>
        <el-row>
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="query.pageNum"
                    :page-sizes="[20, 25, 30, 40]"
                    :page-size="query.pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </el-row>
        <el-drawer
                title="检测结果"
                :visible.sync="drawer"
                :direction="direction">
            <el-input
                    type="textarea"
                    :rows="50"
                    placeholder="正在加载中"
                    :disabled="true"
                    v-model="textarea">
            </el-input>
        </el-drawer>
    </div>
</template>

<script>
    import {search, seeFile} from '@/api/taskAllApi'

    export default {
        name: "my",
        data() {
            return {
                query: {
                    pageNum: 1,
                    pageSize: 20
                },
                taskAll: [],
                total: 0,
                drawer: false,
                direction: 'rtl',
                textarea: ''
            };
        },
        created() {
            const _this = this;
            _this.search()
        },
        methods: {
            formatBoolean: function (row, column, cellValue) {
                var ret = ''
                if (cellValue) {
                    ret = "是"
                } else {
                    ret = "否"
                }
                return ret;
            },
            dateFormat(row, column) {
                const _this = this;
                let date = row[column.property];
                if (date == undefined) {
                    return ''
                }
                return _this.moment(date).format("YYYY-MM-DD HH:mm:ss")
            },
            handleSizeChange(val) {
                const _this = this;
                _this.query.pageSize = val;
                _this.search()
            },
            handleCurrentChange(val) {
                const _this = this;
                _this.query.pageNum = val;
                _this.search()
            },
            search() {
                const _this = this;
                search(_this.query).then(res => {
                    _this.taskAll = res.list;
                    _this.total = res.total;
                }).catch(err => {
                    this.$message.error('查询失败,err:' + err);
                })
            },
            seeFile(row) {
                if (row.finish == false){
                    this.$message.error('任务还未完成');
                    return;
                }
                const _this = this;
                _this.drawer = true;
                seeFile(row.id).then(res => {
                    _this.textarea = res;
                }).catch(err => {
                    this.$message.error('查询失败,err:' + err);
                })
            }
        }
    }
</script>

<style scoped>

</style>
