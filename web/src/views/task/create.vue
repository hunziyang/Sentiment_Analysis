<template>
    <el-row>
        <el-upload
                class="upload-demo"
                ref="upload"
                action="/api/upload/checkFile"
                limit="1"
                accept=".txt"
                :on-remove="handleRemove"
                :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
        </el-upload>
    </el-row>
</template>

<script>
    export default {
        name: "create",
        data () {
            return {
            };
        },
        methods: {
            submitUpload() {
                this.$refs.upload.submit();
            },
            handleRemove(file) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            }
        }
    }
</script>

<style scoped>

</style>
