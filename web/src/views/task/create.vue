<template>
    <div>
        <el-input
                type="textarea"
                :rows="2"
                placeholder="请输入内容"
                v-model="text.text">
        </el-input>
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
                text:{
                    text:''
                }
            };
        },
        methods: {
            createTicket(){
                const _this = this;
                console.log("success")
                create(_this.text).then(() => {
                    this.$message({
                        message: '创建任务成功',
                        type: 'success'
                    });
                }).catch(err => {
                    this.$message.error('创建任务失败,err:'+err);
                })
            }
        }
    }
</script>

<style scoped>

</style>
