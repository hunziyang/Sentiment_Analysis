<template>
<div>
    <el-upload
            class="upload-demo"
            ref="upload"
            action="/api/upload/checkFile"
            :on-preview="handlePreview"
            :on-success="uploadSuccess"
            :auto-upload="false">
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
    </el-upload>
</div>
</template>

<script>
    // import {all} from '@/api/homeApi'
    export default {
        name: "task",
        data () {
            return {
            };
        },
        methods: {
            submitUpload() {
                this.$refs.upload.submit();
            },
            uploadSuccess(res){
                console.log(res);
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            }
        }
    }
</script>

<style scoped>

</style>
