<template>
    <div>
        <el-row>
            <el-upload
                    class="upload-demo"
                    ref="upload"
                    action="/api/upload/checkFile"
                    :limit="1"
                    accept=".txt"
                    :on-remove="handleRemove"
                    :on-success="uploadSuccess"
                    :auto-upload="false">
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload" :disabled="createFlag">上传到服务器</el-button>
            </el-upload>
        </el-row>
        <el-row>
            <el-button type="primary" @click="createTicket">创建任务</el-button>
        </el-row>
    </div>
</template>

<script>
    import {create} from '@/api/createApi'
    export default {
        name: "create",
        data () {
            return {
                uid:'',
                createFlag:false
            };
        },
        methods: {
            submitUpload() {
                this.$refs.upload.submit();
            },
            handleRemove(file) {
                const _this = this;
                const flag =_this.$confirm(`确定移除 ${ file.name }？`);
                if (flag){
                    _this.createFlag = false;
                }
                return flag;
            },
            uploadSuccess(res){
                const _this = this;
                _this.uid = res.data;
                _this.createFlag = true;
            },
            createTicket(){
                const _this = this;
                if (_this.createFlag){
                    create(_this.uid).then(res => {
                        this.$message({
                            message: '创建任务成功，工单号:'+res,
                            type: 'success'
                        });
                    }).catch(err => {
                        this.$message.error('创建任务失败,err:'+err);
                    })
                }
            }
        }
    }
</script>

<style scoped>

</style>
